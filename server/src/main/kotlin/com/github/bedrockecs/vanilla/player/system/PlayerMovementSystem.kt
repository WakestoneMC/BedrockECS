package com.github.bedrockecs.vanilla.player.system

import com.github.bedrockecs.server.comm.game.ActionUpdateMailbox
import com.github.bedrockecs.server.comm.game.action.PlayerMoveAction
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.system.System
import org.springframework.stereotype.Component

@Component
class PlayerMovementSystem(
    private val db: GameDB,
    private val mailbox: ActionUpdateMailbox,
    private val mapContext: PlayerMapContext
) : System {

    override val tickOrder: Int
        get() = 0

    override fun tick() {
        mailbox.listActions()
            .filterIsInstance<PlayerMoveAction>()
            .forEach { action ->
                val eid = mapContext.findPlayerByUUID(action.playerUUID)
                if (eid != null) {
                    db.entities.mutate(eid, EntityPositionComponent::class.java) {
                        val comp = it
                        if (comp != null) {
                            comp.pos = comp.pos.copy(x = action.position.x, y = action.position.y, z = action.position.z)
                            comp.direction = action.rotation
                        }
                        it
                    }
                }
            }
    }
}
