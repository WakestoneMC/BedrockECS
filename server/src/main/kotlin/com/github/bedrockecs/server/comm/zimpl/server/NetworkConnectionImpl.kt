package com.github.bedrockecs.server.comm.zimpl.server

import com.github.bedrockecs.server.comm.server.ConnectionClosedException
import com.github.bedrockecs.server.comm.server.ConnectionIdentifiers
import com.github.bedrockecs.server.comm.server.ConnectionState
import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.comm.zimpl.handler.GameHandler
import com.github.bedrockecs.server.comm.zimpl.handler.LoginHandler
import com.github.bedrockecs.server.comm.zimpl.handler.PackHandler
import com.github.bedrockecs.server.comm.zimpl.log
import com.github.bedrockecs.server.comm.zimpl.withConnection
import com.github.bedrockecs.server.comm.zimpl.withConnectionDebugLog
import com.nukkitx.network.util.DisconnectReason
import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.BedrockServerSession
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import java.util.concurrent.CompletableFuture

/**
 * implements [NetworkConnection]
 * responsibilities:
 * * acts as the coordination & logging point between low-level callback and high-level imperative API
 * * deals with its own state transition
 */
class NetworkConnectionImpl(
    val session: BedrockServerSession
) : NetworkConnection {

    // constants //

    companion object {
        const val PACKET_CHANNEL_SIZE = 65536
    }

    // state //

    @Volatile
    override var state: ConnectionState = ConnectionState.LOGIN
        private set

    @Volatile
    override var identifiers: ConnectionIdentifiers = ConnectionIdentifiers(
        null,
        null,
        null,
        session.address,
        session.realAddress
    )
        private set

    private val packetChannels: Channel<BedrockPacket> = Channel(PACKET_CHANNEL_SIZE)

    // actions //

    override fun sendPacket(packet: BedrockPacket, latency: NetworkConnection.Latency) {
        if (state == ConnectionState.DISCONNECTED) {
            throw ConnectionClosedException()
        }
        withConnectionDebugLog(this) {
            log.debug("sending packet to client [type={}, latency={}]", packet::class.java.simpleName, latency)
            log.trace("content: [content={}]", packet)
        }
        when (latency) {
            NetworkConnection.Latency.NORMAL -> session.sendPacket(packet)
            NetworkConnection.Latency.IMMEDIATELY -> session.sendPacketImmediately(packet)
        }
    }

    override suspend fun receivePacket(): BedrockPacket {
        if (state == ConnectionState.DISCONNECTED) {
            throw ConnectionClosedException()
        }
        try {
            val packet = packetChannels.receive()
            withConnectionDebugLog(this) {
                log.trace("polled packets through receivePacket() [packet={}]", packet::class.java.simpleName)
            }
            return packet
        } catch (ex: ClosedReceiveChannelException) {
            throw ConnectionClosedException()
        }
    }

    override fun disconnect(reason: String?, hideReason: Boolean): CompletableFuture<Void> {
        withConnectionDebugLog(this) {
            log.debug("disconnected from server [reason={}, hideReason={}]", reason, hideReason)
        }
        return if (state != ConnectionState.DISCONNECTED) {
            this.state = ConnectionState.DISCONNECTED
            this.packetChannels.close()
            session.disconnectFuture(reason, hideReason)
        } else {
            CompletableFuture.completedFuture(null)
        }
    }

    // staged main for state transition //

    /**
     * template main that deals with state transitions
     */
    suspend fun main(loginHandler: LoginHandler, packHandler: PackHandler, gameHandler: GameHandler) {
        // note: we update id, then state, so seeing the new states guarantees seeing the new ID
        try {
            val id = loginHandler.handle(this) ?: return
            if (this.state == ConnectionState.DISCONNECTED) {
                return
            }
            this.identifiers = id
            this.state = ConnectionState.SETUP

            packHandler.handle(this)
            if (this.state == ConnectionState.DISCONNECTED) {
                return
            }
            this.state = ConnectionState.ACTIVE

            gameHandler.handle(this)
        } catch (ex: ConnectionClosedException) {
            // no-op, as the channel is closed already
        } catch (ex: Throwable) {
            log.error("error running main()", ex)
        } finally {
            if (state != ConnectionState.DISCONNECTED) {
                withConnection(this) {
                    log.warn("main() exited without closing connection, closing now")
                }
                disconnect()
            }
        }
    }

    // listeners //

    fun onPacket(packet: BedrockPacket) {
        withConnectionDebugLog(this) {
            if (log.isDebugEnabled) {
                log.debug("received packet from client [type={}]", packet::class.java.simpleName)
                log.trace("content: [content={}]", packet)
            }
        }
        withConnection(this) {
            val result = packetChannels.trySend(packet)
            if (result.isClosed) {
                log.debug("discarding packet due to closed channel")
            } else if (result.isFailure) {
                log.debug(
                    "discarding packet due to failed channel, defensively disconnecting",
                    result.exceptionOrNull()
                )
                log.warn("disconnecting due to overloading server with packets [maxSize={}]", PACKET_CHANNEL_SIZE)
                disconnect("Server Network Overload", hideReason = false)
            }
        }
    }

    fun onProtocolDisconnect(reason: DisconnectReason) {
        withConnectionDebugLog(this) {
            log.debug("disconnected from client [reason={}]", reason)
        }
        this.state = ConnectionState.DISCONNECTED
        this.packetChannels.close()
    }
}
