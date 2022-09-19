package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.game.action.PlayerMoveAction
import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.nukkitx.math.vector.Vector3i
import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.packet.MovePlayerPacket
import com.nukkitx.protocol.bedrock.packet.NetworkChunkPublisherUpdatePacket
import kotlinx.coroutines.future.await
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.ceil

@Component
class GameWorldExchange(
    private val actionUpdateExchange: ActionUpdateExchange
) {

    private val pendingAOIAllSentPlayers = ConcurrentHashMap<UUID, CompletableFuture<Void>>()

    private val sessions = ConcurrentHashMap<UUID, Session>()

    data class Session(
        val networkConnection: NetworkConnection,
        var aoiBlockRadius: Int,
        var sentChunks: MutableSet<ChunkPosition>,
        var lastPosition: FloatBlockPosition? = null
    )

    // WorldHandler side //

    suspend fun onConnection(connection: NetworkConnection) {
        val uuid = connection.identifiers.playerUUID!!
        sessions.put(uuid, Session(connection, 64, mutableSetOf(), null))
        val aoiAllSentFuture = CompletableFuture<Void>()
        pendingAOIAllSentPlayers[uuid] = aoiAllSentFuture
        aoiAllSentFuture.await()
    }

    suspend fun onDisconnected(connection: NetworkConnection) {
        val uuid = connection.identifiers.playerUUID!!
        sessions.remove(uuid)
    }

    suspend fun onPacket(connection: NetworkConnection, packet: BedrockPacket): ProcessResult {
        return when (packet) {
            is MovePlayerPacket -> {
                handleMovePlayer(connection, packet)
                ProcessResult.CONSUME
            }
            else -> {
                ProcessResult.CONTINUE
            }
        }
    }

    private suspend fun handleMovePlayer(connection: NetworkConnection, packet: MovePlayerPacket) {
        actionUpdateExchange.addAction(
            PlayerMoveAction(
                playerUUID = connection.identifiers.playerUUID!!,
                packet.position,
                packet.rotation
            )
        )
    }

    // GameServer side //

    fun checkWorldUpdate(
        db: GameDB,
        changedChunks: Set<ChunkPosition>,
        playerPositions: MutableMap<UUID, FloatBlockPosition>
    ) {
        sessions.map {
            val pos = playerPositions[it.key]!!
            if (it.value.lastPosition != pos) {
                it.value.lastPosition = pos
                it.value.networkConnection.sendPacket(
                    NetworkChunkPublisherUpdatePacket().apply {
                        position = Vector3i.from(pos.x.toInt(), pos.y.toInt(), pos.z.toInt())
                        radius = 64
                    }
                )
            }
        }

        sessions.map { (uuid, session) ->
            val position = playerPositions[uuid]!!.toChunk()
            val chunkDelta = ceil(session.aoiBlockRadius / 16.0).toInt()

            val chunks = mutableSetOf<ChunkPosition>()
            for (x in -chunkDelta..chunkDelta) {
                for (z in -chunkDelta..chunkDelta) {
                    val chunk = ChunkPosition(position.x + x, position.z + z, position.dim)
                    chunks.add(chunk)
                }
            }

            val toSendChunks = (chunks - session.sentChunks.toSet())

            if (toSendChunks.isEmpty()) {
                pendingAOIAllSentPlayers[session.networkConnection.identifiers.playerUUID!!]?.complete(null)
            } else {
                toSendChunks.forEach { chunk ->
                    session.sentChunks.add(chunk)
                    val connection = session.networkConnection
                    if (!db.isLoaded(chunk)) {
                        db.loadChunk(chunk)
                    }
                    val serial = db.world.serialize(chunk)
                    val packet = GameChunkSerializer.serializeChunk(chunk, serial)
                    connection.sendPacket(packet)
                }
            }
        }
    }
}
