package com.github.bedrockecs.vanilla.game.pinteract.system

import com.github.bedrockecs.server.comm.game.ActionUpdateMailbox
import com.github.bedrockecs.server.comm.game.action.BlockFace
import com.github.bedrockecs.server.comm.game.action.PlayerBreakBlockAction
import com.github.bedrockecs.server.comm.game.action.PlayerHotBarSelectSlotAction
import com.github.bedrockecs.server.comm.game.action.PlayerUseItemAction
import com.github.bedrockecs.server.game.data.BlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.db.invitem.InvSlotRef
import com.github.bedrockecs.server.game.db.invitem.data.BlockItemType
import com.github.bedrockecs.server.game.db.invitem.data.ItemTypeComponent
import com.github.bedrockecs.server.game.system.CommonTickOrders
import com.github.bedrockecs.server.game.system.System
import com.github.bedrockecs.vanilla.data.world.AirBlockType
import com.github.bedrockecs.vanilla.game.pinteract.entity.PlayerHotBarComponent
import com.github.bedrockecs.vanilla.game.player.invitem.PlayerInvItemConstants.INVENTORY_NAME
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
            .forEach { action -> handleBreakBlock(action) }
        mailbox.listActions()
            .filterIsInstance<PlayerUseItemAction>()
            .forEach { action -> handleUseItem(action) }
        mailbox.listActions()
            .filterIsInstance<PlayerHotBarSelectSlotAction>()
            .forEach { action -> handleSwapHotBar(action) }
    }

    private fun handleSwapHotBar(action: PlayerHotBarSelectSlotAction) {
        val eid = mapContext.findPlayerByUUID(action.player)!!
        db.entities.mutate(eid, PlayerHotBarComponent::class.java) {
            if (it != null) {
                it.selectedSlot = action.selectedSlot
            }
            it
        }
    }

    private fun handleBreakBlock(action: PlayerBreakBlockAction) {
        val eid = mapContext.findPlayerByUUID(action.player)!!
        val pos = db.entities.read(eid, EntityPositionComponent::class.java)!!
        val dim = pos.pos.dim

        db.world.placeBlock(
            BlockPosition(action.block.x, action.block.y, action.block.z, dim).toLayered(),
            AirBlockType.primary,
            emptyMap()
        )
    }

    private fun handleUseItem(action: PlayerUseItemAction) {
        val eid = mapContext.findPlayerByUUID(action.player)!!
        val pos = db.entities.read(eid, EntityPositionComponent::class.java)!!
        val dim = pos.pos.dim

        val slot = db.entities.read(eid, PlayerHotBarComponent::class.java)!!.selectedSlot
        val itemType = db.invitems.read(InvSlotRef(eid, INVENTORY_NAME, slot), ItemTypeComponent::class.java)
        if (itemType is BlockItemType) {
            val targetPos = when (action.face) {
                BlockFace.BOTTOM -> BlockPosition(action.block.x, action.block.y - 1, action.block.z, dim)
                BlockFace.TOP -> BlockPosition(action.block.x, action.block.y + 1, action.block.z, dim)
                BlockFace.NORTH -> BlockPosition(action.block.x, action.block.y, action.block.z - 1, dim)
                BlockFace.SOUTH -> BlockPosition(action.block.x, action.block.y, action.block.z + 1, dim)
                BlockFace.EAST -> BlockPosition(action.block.x, action.block.y, action.block.z, dim)
                BlockFace.WEST -> BlockPosition(action.block.x, action.block.y, action.block.z, dim)
            }

            val block = itemType.block

            db.world.placeBlock(
                targetPos.toLayered(),
                block,
                emptyMap()
            )
        }
    }
}
