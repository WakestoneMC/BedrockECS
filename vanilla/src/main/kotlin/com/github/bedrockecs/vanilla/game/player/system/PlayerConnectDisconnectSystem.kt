package com.github.bedrockecs.vanilla.game.player.system

import com.github.bedrockecs.comm.game.ActionUpdateMailbox
import com.github.bedrockecs.comm.game.action.PlayerConnectedAction
import com.github.bedrockecs.comm.game.action.PlayerDisconnectedAction
import com.github.bedrockecs.game.chunkloading.entity.EntityChunkLoadingComponent
import com.github.bedrockecs.game.data.FloatBlockPosition
import com.github.bedrockecs.game.db.entity.data.CommonEntityTypes
import com.github.bedrockecs.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.game.db.entity.data.PlayerIdentifiersComponent
import com.github.bedrockecs.game.db.invitem.InvItemDB
import com.github.bedrockecs.game.db.invitem.InvRef
import com.github.bedrockecs.game.db.invitem.InvSlotRef
import com.github.bedrockecs.game.db.invitem.data.BlockItemType
import com.github.bedrockecs.game.db.invitem.data.CommonInventoryConstants.ARMOR_INVENTORY_NAME
import com.github.bedrockecs.game.db.invitem.data.CommonInventoryConstants.ARMOR_INVENTORY_SIZE
import com.github.bedrockecs.game.db.invitem.data.CommonInventoryConstants.BASE_INVENTORY_NAME
import com.github.bedrockecs.game.db.invitem.data.CommonInventoryConstants.BASE_INVENTORY_SIZE
import com.github.bedrockecs.game.db.invitem.data.CommonInventoryConstants.OFFHAND_INVENTORY_NAME
import com.github.bedrockecs.game.db.invitem.data.CommonInventoryConstants.OFFHAND_INVENTORY_SIZE
import com.github.bedrockecs.game.db.invitem.data.InventoryMetadata
import com.github.bedrockecs.game.db.invitem.data.ItemCountComponent
import com.github.bedrockecs.game.system.CommonTickOrders
import com.github.bedrockecs.game.system.ECSSystem
import com.github.bedrockecs.game.zimpl.db.entity.EntityDBInternal
import com.github.bedrockecs.vanilla.data.blocks.DiamondBlockBlockType
import com.github.bedrockecs.vanilla.data.blocks.DirtBlockType
import com.github.bedrockecs.vanilla.data.items.StickItemType
import com.github.bedrockecs.vanilla.game.pinteract.entity.PlayerHotBarComponent
import com.nukkitx.math.vector.Vector3f
import org.springframework.stereotype.Component

@Component
class PlayerConnectDisconnectSystem(
    private val edb: EntityDBInternal,
    private val idb: InvItemDB,
    private val mailbox: ActionUpdateMailbox
) : ECSSystem {

    private val UNIVERSAL_SPAWN = FloatBlockPosition(100.0f, 64.0f, 100.0f)
    private val UNIVERSAL_ROT = Vector3f.from(1.0f, 0.0f, 0.0f)

    override val tickOrder: Int
        get() = CommonTickOrders.INTERACTION

    override fun tick() {
        mailbox.listActions()
            .filterIsInstance<PlayerConnectedAction>()
            .forEach(this::handleConnectedAction)

        mailbox.listActions()
            .filterIsInstance<PlayerDisconnectedAction>()
            .forEach(this::handleDisconnectedAction)
    }

    private fun handleConnectedAction(it: PlayerConnectedAction) {
        val eid = edb.create(
            CommonEntityTypes.PLAYER,
            mutableSetOf(
                PlayerIdentifiersComponent(
                    name = it.identifiers.displayName!!,
                    uuid = it.identifiers.playerUUID!!
                ),
                EntityPositionComponent(UNIVERSAL_SPAWN, UNIVERSAL_ROT), // TODO: get proper spawn logic
                EntityChunkLoadingComponent(radius = 4), // TODO: move this to chunk loading?
                PlayerHotBarComponent(selectedSlot = 0) // TODO: move somewhere else?
            )
        )
        idb.create(InventoryMetadata(eid, BASE_INVENTORY_NAME, BASE_INVENTORY_SIZE))
        idb.place(
            InvSlotRef(eid, BASE_INVENTORY_NAME, 0),
            StickItemType(),
            mapOf(ItemCountComponent::class.java to ItemCountComponent(1))
        )
        idb.place(
            InvSlotRef(eid, BASE_INVENTORY_NAME, 1),
            BlockItemType(DirtBlockType.primary),
            mapOf(ItemCountComponent::class.java to ItemCountComponent(1))
        )
        idb.place(
            InvSlotRef(eid, BASE_INVENTORY_NAME, 2),
            BlockItemType(DiamondBlockBlockType.primary),
            mapOf(ItemCountComponent::class.java to ItemCountComponent(10))
        )
        idb.create(InventoryMetadata(eid, ARMOR_INVENTORY_NAME, ARMOR_INVENTORY_SIZE))
        idb.create(InventoryMetadata(eid, OFFHAND_INVENTORY_NAME, OFFHAND_INVENTORY_SIZE))
    }

    private fun handleDisconnectedAction(it: PlayerDisconnectedAction) {
        val eid = edb.findEntityByPlayerUUID(it.identifiers.playerUUID!!)
        if (eid != null) {
            idb.destroy(InvRef(eid, OFFHAND_INVENTORY_NAME))
            idb.destroy(InvRef(eid, ARMOR_INVENTORY_NAME))
            idb.destroy(InvRef(eid, BASE_INVENTORY_NAME))
            edb.destroy(eid)
        }
    }
}
