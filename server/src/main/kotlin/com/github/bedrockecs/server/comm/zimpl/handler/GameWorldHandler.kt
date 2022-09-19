package com.github.bedrockecs.server.comm.zimpl.handler

import com.github.bedrockecs.server.comm.game.action.PlayerConnectedAction
import com.github.bedrockecs.server.comm.game.action.PlayerDisconnectedAction
import com.github.bedrockecs.server.comm.game.action.PlayerMoveAction
import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.comm.zimpl.exchange.GameActionUpdateExchange
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.nukkitx.math.vector.Vector3i
import com.nukkitx.protocol.bedrock.packet.MovePlayerPacket
import com.nukkitx.protocol.bedrock.packet.NetworkChunkPublisherUpdatePacket
import com.nukkitx.protocol.bedrock.packet.PlayStatusPacket
import kotlinx.coroutines.Job
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.ceil

@Component
class GameWorldHandler(
    private val exchange: GameActionUpdateExchange
) {

    data class SpawnedPlayer(
        val eid: EntityID,
        val pos: EntityPositionComponent
    )

    private val pendingPlayer = ConcurrentHashMap<UUID, CompletableFuture<SpawnedPlayer>>()

    private val sessions = ConcurrentHashMap<UUID, Session>()

    data class Session(
        val networkConnection: NetworkConnection,
        val runningCoroutine: Job,
        var aoiBlockRadius: Int,
        var sentChunks: MutableSet<ChunkPosition>,
        var lastPosition: FloatBlockPosition? = null
    )

    // WorldHandler side //

    suspend fun waitInPendingPlayer(connection: NetworkConnection): SpawnedPlayer {
        val job = CompletableFuture<SpawnedPlayer>()
        val uuid = connection.identifiers.playerUUID!!
        try {
            pendingPlayer[uuid] = job
            exchange.addAction(PlayerConnectedAction(connection.identifiers))
            return job.await()
        } finally {
            pendingPlayer.remove(uuid)
        }
    }

    suspend fun serveGame(
        connection: NetworkConnection,
        spawnedPlayer: SpawnedPlayer
    ) {
        appearInSessions(connection) {
            // game state //
            // PlayerListPacket TODO: send player list
            // SetTimePacket TODO: send time
            // PlayerFogPacket TODO: specify player fog

            // player state //
            // InventoryContentPacket
            // InventoryContentPacket
            // InventoryContentPacket
            // InventoryContentPacket
            // PlayerHotbarPacket

            // UpdateAttributesPacket
            // UpdateAttributesPacket
            // SetEntityDataPacket
            // SetEntityDataPacket
            // SetHealthPacket

            // execute respawn //
            // RespawnPacket
            // RespawnPacket
            // RespawnPacket

            // world state //
            // NetworkChunkPublisherUpdatePacket, TODO: sends location of player & radius=64, range updated by chunk radius updated packet
            // TODO: update sequence REQUEST_CHUNK_RADIUS -> CHUNK_RADIUS_UPDATED NETWORK_CHUNK_PUBLISHER_UPDATE are updated as follow-up

            // LEVEL_CHUNK for initial chunk content TODO: impl this out

            // BLOCK_UPDATE for block updates, UPDATE_SUBCHUNK_BLOCKS for batched update? TODO: figure this out

            // TickSyncPacket

            // done //
            connection.sendPacket(PlayStatusPacket().apply { status = PlayStatusPacket.Status.PLAYER_SPAWN })

            while (true) {
                val packet = connection.receivePacket()
                when (packet) {
                    is MovePlayerPacket -> handleMovePlayer(connection, packet)
                }
            }
        }
    }

    private suspend fun handleMovePlayer(connection: NetworkConnection, packet: MovePlayerPacket) {
        exchange.addAction(
            PlayerMoveAction(
                playerUUID = connection.identifiers.playerUUID!!,
                packet.position,
                packet.rotation
            )
        )
    }

    private suspend fun appearInSessions(
        connection: NetworkConnection,
        aoiBlockRadius: Int = 64,
        func: suspend () -> Unit
    ) {
        val uuid = connection.identifiers.playerUUID!!
        runBlocking {
            val job = launch { func() }
            sessions.put(uuid, Session(connection, job, aoiBlockRadius, mutableSetOf(), null))
            try {
                job.join()
            } finally {
                exchange.addAction(PlayerDisconnectedAction(connection.identifiers))
                sessions.remove(uuid)
            }
        }
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

        sessions.map {
            val position = playerPositions[it.key]!!.toChunk()
            val chunkDelta = ceil(it.value.aoiBlockRadius / 16.0).toInt()
            for (x in -chunkDelta..chunkDelta) {
                for (z in -chunkDelta..chunkDelta) {
                    val chunk = ChunkPosition(position.x + x, position.z + z, position.dim)
                    if (!it.value.sentChunks.contains(chunk)) {
                        it.value.sentChunks.add(chunk)
                        val connection = it.value.networkConnection
                        val position = chunk
                        if (!db.isLoaded(position)) {
                            db.loadChunk(position)
                        }
                        val serial = db.world.serialize(position)
                        val packet = GameChunkSerializer.serializeChunk(position, serial)
                        connection.sendPacket(packet)
                    }
                }
            }
        }
    }

    fun resolvePendingPlayers(resolved: Map<UUID, SpawnedPlayer>) {
        resolved.forEach {
            pendingPlayer[it.key]!!.complete(it.value)
        }
    }
}
