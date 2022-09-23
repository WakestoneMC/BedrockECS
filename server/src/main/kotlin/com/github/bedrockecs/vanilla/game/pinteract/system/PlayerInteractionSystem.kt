package com.github.bedrockecs.vanilla.game.pinteract.system

import com.github.bedrockecs.server.comm.game.ActionUpdateMailbox
import com.github.bedrockecs.server.comm.game.action.BlockFace
import com.github.bedrockecs.server.comm.game.action.PlayerBreakBlockAction
import com.github.bedrockecs.server.comm.game.action.PlayerUseItemAction
import com.github.bedrockecs.server.game.data.BlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.system.CommonTickOrders
import com.github.bedrockecs.server.game.system.System
import com.github.bedrockecs.vanilla.data.world.AirBlockType
import com.github.bedrockecs.vanilla.data.world.DirtBlockType
import com.github.bedrockecs.vanilla.game.log
import com.github.bedrockecs.vanilla.game.player.system.PlayerMapContext
import org.springframework.stereotype.Component

@Component
class PlayerInteractionSystem(
    private val db: GameDB,
    private val mapContext: PlayerMapContext,
    private val mailbox: ActionUpdateMailbox
) : System {

    override val tickOrder: Int = CommonTickOrders.PLAYER_INTERACTION

    override fun tick() {
        mailbox.listActions()
            .filterIsInstance<PlayerBreakBlockAction>()
            .forEach { action ->
                val eid = mapContext.findPlayerByUUID(action.player)!!
                val pos = db.entities.read(eid, EntityPositionComponent::class.java)!!
                val dim = pos.pos.dim

                db.world.placeBlock(
                    BlockPosition(action.block.x, action.block.y, action.block.z, dim).toLayered(),
                    AirBlockType.primary,
                    emptyMap()
                )
            }
        mailbox.listActions()
            .filterIsInstance<PlayerUseItemAction>()
            .forEach { action ->
                val eid = mapContext.findPlayerByUUID(action.player)!!
                val pos = db.entities.read(eid, EntityPositionComponent::class.java)!!
                val dim = pos.pos.dim

                val targetPos = when (action.face) {
                    BlockFace.BOTTOM -> BlockPosition(action.block.x, action.block.y - 1, action.block.z, dim)
                    BlockFace.TOP -> BlockPosition(action.block.x, action.block.y + 1, action.block.z, dim)
                    BlockFace.NORTH -> BlockPosition(action.block.x, action.block.y, action.block.z - 1, dim)
                    BlockFace.SOUTH -> BlockPosition(action.block.x, action.block.y, action.block.z + 1, dim)
                    BlockFace.EAST -> BlockPosition(action.block.x, action.block.y, action.block.z, dim)
                    BlockFace.WEST -> BlockPosition(action.block.x, action.block.y, action.block.z, dim)
                }

                log.info("placing block {}", action)
                db.world.placeBlock(
                    targetPos.toLayered(),
                    DirtBlockType.primary,
                    emptyMap()
                )
            }
    }
}
