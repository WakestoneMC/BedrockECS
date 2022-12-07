package com.github.bedrockecs.comm.zimpl.server

import com.github.bedrockecs.comm.config.NetworkConfig
import com.github.bedrockecs.comm.server.NetworkConnection
import com.github.bedrockecs.comm.server.NetworkServer
import com.github.bedrockecs.comm.server.policy.ExistingConnectionPolicy
import com.github.bedrockecs.comm.server.policy.NewConnectionPolicy
import com.github.bedrockecs.comm.zimpl.handler.ConnectionRequestHandler
import com.github.bedrockecs.comm.zimpl.handler.GameHandler
import com.github.bedrockecs.comm.zimpl.handler.LoginHandler
import com.github.bedrockecs.comm.zimpl.handler.PackHandler
import com.github.bedrockecs.comm.zimpl.handler.PingHandler
import com.github.bedrockecs.comm.zimpl.log
import com.github.bedrockecs.comm.zimpl.withSourceDebugLog
import com.nukkitx.network.util.DisconnectReason
import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.BedrockPacketCodec
import com.nukkitx.protocol.bedrock.BedrockPong
import com.nukkitx.protocol.bedrock.BedrockServer
import com.nukkitx.protocol.bedrock.BedrockServerEventHandler
import com.nukkitx.protocol.bedrock.BedrockServerSession
import com.nukkitx.protocol.bedrock.BedrockSession
import com.nukkitx.protocol.bedrock.handler.BatchHandler
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.EventLoopGroup
import io.netty.channel.socket.DatagramPacket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.springframework.stereotype.Component
import java.lang.System.identityHashCode
import java.net.InetSocketAddress
import java.util.Base64
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import java.util.function.Consumer

/**
 * implements [NetworkServer]
 */
@Component
class NetworkServerImpl(
    // config & codec
    private val config: NetworkConfig,
    private val codec: BedrockPacketCodec,
    // handlers
    private val pingHandler: PingHandler,
    private val connectionRequestHandler: ConnectionRequestHandler,
    private val loginHandler: LoginHandler,
    private val packHandler: PackHandler,
    private val gameHandler: GameHandler,
    // threading infra
    private val workerEventLoopGroup: EventLoopGroup,
    private val bossEventLoopGroup: EventLoopGroup
) : AutoCloseable, NetworkServer {

    enum class State {
        INIT, RUNNING, CLOSED
    }

    // running state //
    @Volatile // so this is safe to access from different threads
    private var state: State = State.INIT
    private var bedrockServer: BedrockServer? = null
    private val manager: ConnectionManager = ConnectionManager(
        NewConnectionPolicy.Reject("Server is still starting!")
    )
    private val scope: CoroutineScope = CoroutineScope(workerEventLoopGroup.asCoroutineDispatcher())

    // lifecycles //

    fun start() {
        when (state) {
            // actually do the startup
            State.INIT -> Unit
            // idempotent
            State.RUNNING -> return
            State.CLOSED -> throw IllegalStateException("cannot start a closed server!")
        }

        log.info("starting network server")
        val bedrockServer = BedrockServer(
            config.bindAddress,
            Runtime.getRuntime().availableProcessors(),
            bossEventLoopGroup,
            workerEventLoopGroup,
            false
        )
        bedrockServer.setHandler(DispatchingServerListener())
        bedrockServer.bind().join()
        log.info("network server started, listening on ${config.bindAddress}")

        // atomic assignment, state won't be updated until initialization completes
        state = State.RUNNING
        this.bedrockServer = bedrockServer
    }

    override fun close() {
        when (state) {
            State.INIT -> throw IllegalStateException("cannot stop a not started server!")
            // actually do the shutdown
            State.RUNNING -> Unit
            // idempotent
            State.CLOSED -> return
        }

        log.info("closing network server")
        try {
            disconnectAllPlayers()
            closeServer()
            cancelCoroutines()
        } catch (e: Exception) {
            log.error("Unable to shutdown networking gracefully", e)
        } finally {
            state = State.CLOSED
            log.info("network server closed")
        }
    }

    private fun disconnectAllPlayers() {
        try {
            manager.applyPolicy(
                NewConnectionPolicy.Reject("Server Closed", false),
                ExistingConnectionPolicy.Disconnect("Server Closing", false)
            ).get(500, TimeUnit.MILLISECONDS)
        } catch (e: InterruptedException) {
            // no-op, just move on
        } catch (_: TimeoutException) {
            // no-op, just move on
        } catch (e: ExecutionException) {
            log.error("Error while disconnecting all players", e)
        }
    }

    private fun closeServer() {
        try {
            bedrockServer?.close()
        } catch (e: Exception) {
            log.error("Error while shutting down bedrock server", e)
        } finally {
            bedrockServer = null
        }
    }

    private fun cancelCoroutines() {
        scope.cancel("server stopped")
    }

    // connection actions //

    override fun applyConnectionPolicy(
        newConn: NewConnectionPolicy,
        existingConn: ExistingConnectionPolicy
    ): CompletableFuture<Void> {
        return manager.applyPolicy(newConn, existingConn)
    }

    // connection queries //

    override fun listActiveConnections(): Collection<NetworkConnection> {
        return manager.listActive()
    }

    override fun findActiveConnectionByPlayerUUID(uuid: UUID): NetworkConnection? {
        return manager.findActiveByUUID(uuid)
    }

    // private: listening logic //

    /**
     * listener class for ping/pong & new connection
     */
    inner class DispatchingServerListener : BedrockServerEventHandler {
        override fun onQuery(address: InetSocketAddress): BedrockPong {
            val ret = pingHandler.handle(address)
            withSourceDebugLog(address) {
                log.debug("received ping, responded with pong")
                log.trace("packet content [packet={}]", ret)
            }
            return ret
        }

        override fun onConnectionRequest(address: InetSocketAddress, realAddress: InetSocketAddress): Boolean {
            val ret = connectionRequestHandler.handle(address, realAddress)
            withSourceDebugLog(address, realAddress) {
                log.debug("received raknet connection request [decision={}]", ret)
            }
            return ret
        }

        override fun onSessionCreation(session: BedrockServerSession) {
            val connection = NetworkConnectionImpl(session)
            val listener = PlayerListener(connection)
            session.batchHandler = listener
            session.addDisconnectHandler(listener)

            scope.launch {
                // try to join the manager
                val success = manager.tryJoin(connection)
                if (!success) {
                    connection.disconnect("server closed", hideReason = false)
                    return@launch
                }
                // leave the manager when done
                try {
                    connection.main(loginHandler, packHandler, gameHandler)
                } finally {
                    manager.leave(connection)
                }
            }

            withSourceDebugLog(session.address, session.realAddress) {
                log.debug(
                    "started bedrock server session [session={}]",
                    Integer.toHexString(identityHashCode(session))
                )
            }
        }

        override fun onUnhandledDatagram(ctx: ChannelHandlerContext, packet: DatagramPacket) {
            withSourceDebugLog(ctx.channel().remoteAddress()) {
                val readable = packet.content().readableBytes()
                log.debug("received unhandled datagram [len={}]", readable)
                if (log.isTraceEnabled) {
                    val bytes = packet.content().copy().array()
                    val encoded = Base64.getEncoder().encodeToString(bytes)
                    log.trace("datagram content [content={}]", encoded)
                }
            }
        }
    }

    /**
     * listener class for listening to player-specific packets
     */
    inner class PlayerListener(
        private val connection: NetworkConnectionImpl
    ) : BatchHandler, Consumer<DisconnectReason> {
        override fun handle(session: BedrockSession, compressed: ByteBuf, packets: Collection<BedrockPacket>) {
            for (packet in packets) {
                connection.onPacket(packet)
            }
        }

        override fun accept(disconnectReason: DisconnectReason) {
            connection.onProtocolDisconnect(disconnectReason)
        }
    }
}
