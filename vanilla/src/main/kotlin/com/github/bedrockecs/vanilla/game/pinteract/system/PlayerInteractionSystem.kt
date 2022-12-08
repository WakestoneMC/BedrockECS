package com.github.bedrockecs.vanilla.game.pinteract.system

import com.github.bedrockecs.game.io.ActionUpdateMailbox
import com.github.bedrockecs.game.io.action.BlockFace
import com.github.bedrockecs.game.io.action.PlayerBreakBlockAction
import com.github.bedrockecs.game.io.action.PlayerHotBarSelectSlotAction
import com.github.bedrockecs.game.io.action.PlayerUseItemAction
import com.github.bedrockecs.game.data.BlockPosition
import com.github.bedrockecs.game.db.invitem.InvItemDB
import com.github.bedrockecs.game.db.invitem.InvSlotRef
import com.github.bedrockecs.game.db.invitem.data.BlockItemType
import com.github.bedrockecs.game.db.invitem.data.CommonInventoryConstants.BASE_INVENTORY_NAME
import com.github.bedrockecs.game.db.invitem.data.ItemTypeComponent
import com.github.bedrockecs.game.db.world.WorldDB
import com.github.bedrockecs.game.system.CommonTickOrders
import com.github.bedrockecs.game.system.ECSSystem
import com.github.bedrockecs.game.zimpl.db.entity.EntityDBInternal
import com.github.bedrockecs.vanilla.data.blocks.AirBlockType
import com.github.bedrockecs.vanilla.game.pinteract.entity.PlayerHotBarComponent
import org.springframework.stereotype.Component

@Component
class PlayerInteractionSystem(
    private val idb: InvItemDB,
    private val wdb: WorldDB,
    private val edb: EntityDBInternal,
    private val mailbox: ActionUpdateMailbox
) : ECSSystem {

    override val tickOrder: Int = CommonTickOrders.INTERACTION

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
        val eid = edb.findEntityByPlayerUUID(action.player)!!
        edb.mutate(eid, PlayerHotBarComponent::class.java) {
            if (it != null) {
                it.selectedSlot = action.selectedSlot
            }
            it
        }
    }

    private fun handleBreakBlock(action: PlayerBreakBlockAction) {
        val eid = edb.findEntityByPlayerUUID(action.player)!!

        wdb.placeBlock(
            BlockPosition(action.block.x, action.block.y, action.block.z).toLayered(),
            AirBlockType.primary,
            emptyMap()
        )
    }

    private fun handleUseItem(action: PlayerUseItemAction) {
        val eid = edb.findEntityByPlayerUUID(action.player)!!

        val slot = edb.read(eid, PlayerHotBarComponent::class.java)!!.selectedSlot
        val itemType = idb.read(InvSlotRef(eid, BASE_INVENTORY_NAME, slot), ItemTypeComponent::class.java)
        if (itemType is BlockItemType) {
            val targetPos = when (action.face) {
                BlockFace.BOTTOM -> BlockPosition(action.block.x, action.block.y - 1, action.block.z)
                BlockFace.TOP -> BlockPosition(action.block.x, action.block.y + 1, action.block.z)
                BlockFace.NORTH -> BlockPosition(action.block.x, action.block.y, action.block.z - 1)
                BlockFace.SOUTH -> BlockPosition(action.block.x, action.block.y, action.block.z + 1)
                BlockFace.EAST -> BlockPosition(action.block.x, action.block.y, action.block.z)
                BlockFace.WEST -> BlockPosition(action.block.x, action.block.y, action.block.z)
            }

            val block = itemType.block

            wdb.placeBlock(
                targetPos.toLayered(),
                block,
                emptyMap()
            )
        }
    }
}
