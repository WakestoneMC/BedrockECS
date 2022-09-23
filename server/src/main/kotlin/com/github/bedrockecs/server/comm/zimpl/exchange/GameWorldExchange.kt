package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.comm.zimpl.serial.GameChunkSerializer
import com.github.bedrockecs.server.comm.zimpl.serial.InventoryNetworkSerializer
import com.github.bedrockecs.server.comm.zimpl.serial.InventoryNetworkSerializer.Companion.PLAYER_ARMOR_CONTAINER_ID
import com.github.bedrockecs.server.comm.zimpl.serial.InventoryNetworkSerializer.Companion.PLAYER_INVENTORY_CONTAINER_ID
import com.github.bedrockecs.server.comm.zimpl.serial.InventoryNetworkSerializer.Companion.PLAYER_OFFHAND_CONTAINER_ID
import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.invitem.InvRef
import com.github.bedrockecs.vanilla.game.player.invitem.PlayerInvItemConstants.ARMOR_INVENTORY_NAME
import com.github.bedrockecs.vanilla.game.player.invitem.PlayerInvItemConstants.INVENTORY_NAME
import com.github.bedrockecs.vanilla.game.player.invitem.PlayerInvItemConstants.OFFHAND_INVENTORY_NAME
import com.github.bedrockecs.vanilla.game.player.system.PlayerMapContext
import com.nukkitx.math.vector.Vector3i
import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.packet.ChunkRadiusUpdatedPacket
import com.nukkitx.protocol.bedrock.packet.InventoryContentPacket
import com.nukkitx.protocol.bedrock.packet.NetworkChunkPublisherUpdatePacket
import com.nukkitx.protocol.bedrock.packet.PlayerHotbarPacket
import com.nukkitx.protocol.bedrock.packet.RequestChunkRadiusPacket
import com.nukkitx.protocol.bedrock.packet.TickSyncPacket
import kotlinx.coroutines.future.await
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.ceil
import kotlin.math.min

/**
 * in charge of sending world state(chunks/inventory/entities/players) to client
 */
@Component
class GameWorldExchange(
    private val itemSerializer: InventoryNetworkSerializer
) {
    companion object {
        private const val DEFAULT_AOI_BLOCK_RADIUS = 64

        private const val MAX_AOI_BLOCK_RADIUS = 16 * SUBCHUNK_SIZE
    }

    private val waitingForInitialInvSent = ConcurrentHashMap<UUID, CompletableFuture<Void>>()

    private val waitingForInitialChunkSent = ConcurrentHashMap<UUID, CompletableFuture<Void>>()

    private val sessions = ConcurrentHashMap<UUID, Session>()

    private var currentTick: Long = 0

    data class Session(
        val connection: NetworkConnection,
        var aoiBlockRadius: Int = DEFAULT_AOI_BLOCK_RADIUS,
        var lastPosition: FloatBlockPosition? = null,
        var sentChunks: MutableSet<ChunkPosition> = mutableSetOf(),
        var sentInventories: Boolean = false
    )

    // WorldHandler side //

    suspend fun onConnection(connection: NetworkConnection) {
        val uuid = connection.identifiers.playerUUID!!
        sessions.put(uuid, Session(connection))
        val inventorySentFuture = CompletableFuture<Void>()
        waitingForInitialInvSent[uuid] = inventorySentFuture
        val worldSentFuture = CompletableFuture<Void>()
        waitingForInitialChunkSent[uuid] = worldSentFuture
        try {
            inventorySentFuture.await()
            worldSentFuture.await()
        } finally {
            waitingForInitialChunkSent.remove(uuid)
            waitingForInitialInvSent.remove(uuid)
        }
    }

    suspend fun onDisconnected(connection: NetworkConnection) {
        val uuid = connection.identifiers.playerUUID!!
        sessions.remove(uuid)
    }

    suspend fun onPacket(connection: NetworkConnection, packet: BedrockPacket): ProcessResult {
        return when (packet) {
            is RequestChunkRadiusPacket -> {
                onPacket(connection, packet)
                ProcessResult.CONSUME
            }
            is TickSyncPacket -> {
                onPacket(connection, packet)
                ProcessResult.CONSUME
            }
            else -> ProcessResult.CONTINUE
        }
    }

    private fun onPacket(connection: NetworkConnection, packet: RequestChunkRadiusPacket) {
        val session = sessions[connection.identifiers.playerUUID]
        if (session != null) {
            session.aoiBlockRadius = min(packet.radius * SUBCHUNK_SIZE, MAX_AOI_BLOCK_RADIUS)
            val resp = ChunkRadiusUpdatedPacket()
            resp.radius = session.aoiBlockRadius / SUBCHUNK_SIZE
            connection.sendPacket(resp, NetworkConnection.Latency.IMMEDIATELY)
        }
    }

    private fun onPacket(connection: NetworkConnection, packet: TickSyncPacket) {
        val req = packet.requestTimestamp
        val ret = TickSyncPacket()
        ret.requestTimestamp = req
        ret.responseTimestamp = currentTick
        connection.sendPacket(ret, NetworkConnection.Latency.IMMEDIATELY)
    }

    // GameServer side //

    fun handleTickUpdate(currentTick: Long) {
        this.currentTick = currentTick
    }

    fun handleInventoryUpdate(db: GameDB, mapContext: PlayerMapContext, changedInventories: Set<InvRef>) {
        sessions.map { (uuid, session) ->
            if (!session.sentInventories) {
                sendInitialInventory(db, mapContext, uuid, session)
                waitingForInitialInvSent[uuid]?.complete(null)
                session.sentInventories = true
            }
        }
    }

    private fun sendInitialInventory(
        db: GameDB,
        mapContext: PlayerMapContext,
        uuid: UUID,
        session: Session
    ) {
        val eid = mapContext.findPlayerByUUID(uuid)!!

        listOf(
            PLAYER_INVENTORY_CONTAINER_ID to INVENTORY_NAME,
            PLAYER_OFFHAND_CONTAINER_ID to OFFHAND_INVENTORY_NAME,
            PLAYER_ARMOR_CONTAINER_ID to ARMOR_INVENTORY_NAME
        ).forEach { (cid, name) ->
            val serial = itemSerializer.serializeInventory(db.invitems, InvRef(eid, name))
            session.connection.sendPacket(
                InventoryContentPacket().apply {
                    containerId = cid
                    contents = serial.toList()
                },
                NetworkConnection.Latency.IMMEDIATELY
            )
        }

        session.connection.sendPacket(
            PlayerHotbarPacket().apply {
                selectedHotbarSlot = 0
                containerId = PLAYER_INVENTORY_CONTAINER_ID
                isSelectHotbarSlot = true
            },
            NetworkConnection.Latency.IMMEDIATELY
        )
    }

    fun handlePlayerPositionUpdate(playerPositions: Map<UUID, FloatBlockPosition>) {
        sessions.map { (uuid, session) ->
            val pos = playerPositions[uuid]!!
            if (session.lastPosition != pos) {
                session.lastPosition = pos
                session.connection.sendPacket(
                    NetworkChunkPublisherUpdatePacket().apply {
                        position = Vector3i.from(pos.x.toInt(), pos.y.toInt(), pos.z.toInt())
                        radius = session.aoiBlockRadius
                    }
                )
            }
        }
    }

    fun handleWorldUpdate(db: GameDB, changedChunks: Set<ChunkPosition>) {
        sessions.map { (uuid, session) ->
            val aoiChunks = computeAoiChunks(session)

            val toSendChunks = (aoiChunks - session.sentChunks.toSet())

            if (toSendChunks.isEmpty()) {
                waitingForInitialChunkSent[session.connection.identifiers.playerUUID!!]?.complete(null)
            } else {
                toSendChunks.forEach { chunk ->
                    session.sentChunks.add(chunk)
                    val connection = session.connection
                    if (!db.isLoaded(chunk)) {
                        db.loadChunk(chunk) // TODO: comm shouldn't in charge of loading chunks!
                    }
                    val serial = db.world.serialize(chunk)
                    val packet = GameChunkSerializer.serializeChunk(chunk, serial)
                    connection.sendPacket(packet)
                }
            }
        }
    }

    private fun computeAoiChunks(session: Session): MutableSet<ChunkPosition> {
        val position = session.lastPosition!!.toChunk()
        val chunkDelta = ceil(session.aoiBlockRadius / SUBCHUNK_SIZE.toFloat()).toInt()
        val aoiChunks = mutableSetOf<ChunkPosition>()
        for (x in -chunkDelta..chunkDelta) {
            for (z in -chunkDelta..chunkDelta) {
                val chunk = ChunkPosition(position.chunkX + x, position.chunkZ + z, position.dim)
                aoiChunks.add(chunk)
            }
        }
        return aoiChunks
    }
}
