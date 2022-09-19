package com.github.bedrockecs.vanilla.player.system

import com.github.bedrockecs.server.comm.game.ActionUpdateMailbox
import com.github.bedrockecs.server.comm.game.action.PlayerConnectedAction
import com.github.bedrockecs.server.comm.game.action.PlayerDisconnectedAction
import com.github.bedrockecs.server.game.chunkloading.entity.EntityChunkLoadingComponent
import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.system.System
import com.github.bedrockecs.vanilla.player.entity.PlayerEntityType
import com.github.bedrockecs.vanilla.player.entity.PlayerIdentifierComponent
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PlayerConnectDisconnectSystem(
    private val db: GameDB,
    private val mailbox: ActionUpdateMailbox
) : System {

    private val UNIVERSAL_SPAWN = FloatBlockPosition(100.0f, 64.0f, 100.0f, 0)

    private val players = mutableMapOf<UUID, EntityID>()

    override val tickOrder: Int
        get() = Int.MIN_VALUE + 5_000

    override fun tick() {
        mailbox.listActions()
            .filterIsInstance<PlayerConnectedAction>()
            .forEach {
                val eid = db.entities.create(
                    PlayerEntityType.TYPE,
                    mutableSetOf(
                        PlayerIdentifierComponent(
                            displayName = it.identifiers.displayName!!,
                            uuid = it.identifiers.playerUUID!!
                        ),
                        EntityPositionComponent(UNIVERSAL_SPAWN), // TODO: get proper spawn logic
                        EntityChunkLoadingComponent(radius = 4) // TODO: move this to chunk loading?
                    )
                )
                players[it.identifiers.playerUUID] = eid
            }
        mailbox.listActions()
            .filterIsInstance<PlayerDisconnectedAction>()
            .mapNotNull { players[it.identifiers.playerUUID!!] }
            .forEach { db.entities.destroy(it) }
    }
}
