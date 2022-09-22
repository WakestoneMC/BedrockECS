package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.game.action.PlayerConnectedAction
import com.github.bedrockecs.server.comm.game.action.PlayerDisconnectedAction
import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import kotlinx.coroutines.future.await
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

/**
 * in charge of exposing player connect/disconnect to game, also keeps the mapping of player connections
 */
@Component
class PlayerConnectionExchange(
    private val mailbox: CommActionUpdateMailbox
) {
    data class StartGamePacketData(
        val eid: EntityID,
        val pos: EntityPositionComponent,
        val currentTick: Long
    )

    private val waitingForPlayerEntity = ConcurrentHashMap<UUID, CompletableFuture<StartGamePacketData>>()

    private val connections = ConcurrentHashMap<UUID, NetworkConnection>()

    // WorldHandler side //

    suspend fun handleConnection(connection: NetworkConnection, func: suspend (StartGamePacketData) -> Unit) {
        val job = CompletableFuture<StartGamePacketData>()
        val uuid = connection.identifiers.playerUUID!!

        val spawned: StartGamePacketData
        try {
            waitingForPlayerEntity[uuid] = job
            mailbox.addAction(PlayerConnectedAction(connection.identifiers))
            spawned = job.await()
        } finally {
            waitingForPlayerEntity.remove(uuid)
        }

        connections[uuid] = connection
        try {
            func(spawned)
        } finally {
            connections.remove(uuid)
            mailbox.addAction(PlayerDisconnectedAction(connection.identifiers))
        }
    }

    fun connectionForUUID(uuid: UUID): NetworkConnection? {
        return connections[uuid]
    }

    // GameServer side //

    fun notifyPlayerEntityCreated(resolved: Map<UUID, StartGamePacketData>) {
        resolved.forEach {
            waitingForPlayerEntity[it.key]!!.complete(it.value)
        }
    }
}
