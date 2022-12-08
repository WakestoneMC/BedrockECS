package com.github.bedrockecs.vanilla.game.player.system

import com.github.bedrockecs.game.io.ActionUpdateMailbox
import com.github.bedrockecs.game.io.action.PlayerMoveAction
import com.github.bedrockecs.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.game.system.ECSSystem
import com.github.bedrockecs.game.zimpl.db.entity.EntityDBInternal
import org.springframework.stereotype.Component

@Component
class PlayerMovementSystem(
    private val edb: EntityDBInternal,
    private val mailbox: ActionUpdateMailbox
) : ECSSystem {

    override val tickOrder: Int
        get() = 0

    override fun tick() {
        mailbox.listActions()
            .filterIsInstance<PlayerMoveAction>()
            .forEach { action ->
                val eid = edb.findEntityByPlayerUUID(action.playerUUID)
                if (eid != null) {
                    edb.mutate(eid, EntityPositionComponent::class.java) {
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
