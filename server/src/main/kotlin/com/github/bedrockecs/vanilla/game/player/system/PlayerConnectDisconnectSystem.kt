package com.github.bedrockecs.vanilla.game.player.system

import com.github.bedrockecs.server.comm.game.ActionUpdateMailbox
import com.github.bedrockecs.server.comm.game.action.PlayerConnectedAction
import com.github.bedrockecs.server.comm.game.action.PlayerDisconnectedAction
import com.github.bedrockecs.server.game.chunkloading.entity.EntityChunkLoadingComponent
import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.system.System
import com.github.bedrockecs.vanilla.game.player.entity.PlayerEntityType
import com.github.bedrockecs.vanilla.game.player.entity.PlayerIdentifierComponent
import com.nukkitx.math.vector.Vector3f
import org.springframework.stereotype.Component

@Component
class PlayerConnectDisconnectSystem(
    private val db: GameDB,
    private val mailbox: ActionUpdateMailbox,
    private val mapContext: PlayerMapContext
) : System {

    private val UNIVERSAL_SPAWN = FloatBlockPosition(100.0f, 64.0f, 100.0f, 0)
    private val UNIVERSAL_ROT = Vector3f.from(1.0f, 0.0f, 0.0f)

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
                        EntityPositionComponent(UNIVERSAL_SPAWN, UNIVERSAL_ROT), // TODO: get proper spawn logic
                        EntityChunkLoadingComponent(radius = 4) // TODO: move this to chunk loading?
                    )
                )
                mapContext.onPlayerConnected(it.identifiers, eid)
            }
        mailbox.listActions()
            .filterIsInstance<PlayerDisconnectedAction>()
            .mapNotNull {
                val eid = mapContext.findPlayerByUUID(it.identifiers.playerUUID!!)
                mapContext.onPlayerDisconnected(it.identifiers)
                eid
            }
            .forEach { db.entities.destroy(it) }
    }
}
