package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.nukkitx.math.vector.Vector3i
import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.packet.NetworkChunkPublisherUpdatePacket
import kotlinx.coroutines.future.await
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.ceil

/**
 * in charge of sending world to client
 */
@Component
class GameWorldExchange {

    private val waitingForInitialChunkSent = ConcurrentHashMap<UUID, CompletableFuture<Void>>()

    private val sessions = ConcurrentHashMap<UUID, Session>()

    data class Session(
        val connection: NetworkConnection,
        var aoiBlockRadius: Int,
        var sentChunks: MutableSet<ChunkPosition>,
        var lastPosition: FloatBlockPosition? = null
    )

    // WorldHandler side //

    suspend fun onConnection(connection: NetworkConnection) {
        val uuid = connection.identifiers.playerUUID!!
        sessions.put(uuid, Session(connection, 64, mutableSetOf(), null))
        val worldSentFuture = CompletableFuture<Void>()
        waitingForInitialChunkSent[uuid] = worldSentFuture
        worldSentFuture.await()
    }

    suspend fun onDisconnected(connection: NetworkConnection) {
        val uuid = connection.identifiers.playerUUID!!
        sessions.remove(uuid)
    }

    suspend fun onPacket(connection: NetworkConnection, packet: BedrockPacket): ProcessResult {
        return ProcessResult.CONTINUE
    }

    // GameServer side //

    fun handlePlayerPositionUpdate(playerPositions: MutableMap<UUID, FloatBlockPosition>) {
        sessions.map { (uuid, session) ->
            val pos = playerPositions[uuid]!!
            if (session.lastPosition != pos) {
                session.lastPosition = pos
                session.connection.sendPacket(
                    NetworkChunkPublisherUpdatePacket().apply {
                        position = Vector3i.from(pos.x.toInt(), pos.y.toInt(), pos.z.toInt())
                        radius = 64
                    }
                )
            }
        }
    }

    fun handleWorldUpdate(db: GameDB, changedChunks: Set<ChunkPosition>) {
        sessions.map { (uuid, session) ->
            val position = session.lastPosition!!.toChunk()
            val chunkDelta = ceil(session.aoiBlockRadius / SUBCHUNK_SIZE.toFloat()).toInt()

            val chunks = mutableSetOf<ChunkPosition>()
            for (x in -chunkDelta..chunkDelta) {
                for (z in -chunkDelta..chunkDelta) {
                    val chunk = ChunkPosition(position.chunkX + x, position.chunkZ + z, position.dim)
                    chunks.add(chunk)
                }
            }

            val toSendChunks = (chunks - session.sentChunks.toSet())

            if (toSendChunks.isEmpty()) {
                waitingForInitialChunkSent[session.connection.identifiers.playerUUID!!]?.complete(null)
            } else {
                toSendChunks.forEach { chunk ->
                    session.sentChunks.add(chunk)
                    val connection = session.connection
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
