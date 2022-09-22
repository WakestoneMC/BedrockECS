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
    data class CreatedPlayerEntity(
        val eid: EntityID,
        val pos: EntityPositionComponent
    )

    private val waitingForPlayerEntity = ConcurrentHashMap<UUID, CompletableFuture<CreatedPlayerEntity>>()

    private val connections = ConcurrentHashMap<UUID, NetworkConnection>()

    // WorldHandler side //

    suspend fun handleConnection(connection: NetworkConnection, func: suspend (CreatedPlayerEntity) -> Unit) {
        val job = CompletableFuture<CreatedPlayerEntity>()
        val uuid = connection.identifiers.playerUUID!!

        val spawned: CreatedPlayerEntity
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

    fun notifyPlayerEntityCreated(resolved: Map<UUID, CreatedPlayerEntity>) {
        resolved.forEach {
            waitingForPlayerEntity[it.key]!!.complete(it.value)
        }
    }
}
