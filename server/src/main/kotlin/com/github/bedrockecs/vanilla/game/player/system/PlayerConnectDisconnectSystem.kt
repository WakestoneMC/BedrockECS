package com.github.bedrockecs.vanilla.game.player.system

import com.github.bedrockecs.server.comm.game.ActionUpdateMailbox
import com.github.bedrockecs.server.comm.game.action.PlayerConnectedAction
import com.github.bedrockecs.server.comm.game.action.PlayerDisconnectedAction
import com.github.bedrockecs.server.game.chunkloading.entity.EntityChunkLoadingComponent
import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.db.invitem.InvRef
import com.github.bedrockecs.server.game.db.invitem.InvSlotRef
import com.github.bedrockecs.server.game.db.invitem.data.BlockItemType
import com.github.bedrockecs.server.game.db.invitem.data.InventoryMetadata
import com.github.bedrockecs.server.game.db.invitem.data.ItemCountComponent
import com.github.bedrockecs.server.game.system.CommonTickOrders
import com.github.bedrockecs.server.game.system.System
import com.github.bedrockecs.vanilla.data.invitem.StickItemType
import com.github.bedrockecs.vanilla.data.world.DiamondBlockType
import com.github.bedrockecs.vanilla.data.world.DirtBlockType
import com.github.bedrockecs.vanilla.game.pinteract.entity.PlayerHotBarComponent
import com.github.bedrockecs.vanilla.game.player.entity.PlayerEntityType
import com.github.bedrockecs.vanilla.game.player.entity.PlayerIdentifierComponent
import com.github.bedrockecs.vanilla.game.player.invitem.PlayerInvItemConstants.ARMOR_INVENTORY_NAME
import com.github.bedrockecs.vanilla.game.player.invitem.PlayerInvItemConstants.ARMOR_INVENTORY_SIZE
import com.github.bedrockecs.vanilla.game.player.invitem.PlayerInvItemConstants.INVENTORY_NAME
import com.github.bedrockecs.vanilla.game.player.invitem.PlayerInvItemConstants.INVENTORY_SIZE
import com.github.bedrockecs.vanilla.game.player.invitem.PlayerInvItemConstants.OFFHAND_INVENTORY_NAME
import com.github.bedrockecs.vanilla.game.player.invitem.PlayerInvItemConstants.OFFHAND_INVENTORY_SIZE
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
        get() = CommonTickOrders.PLAYER_INTERACTION

    override fun tick() {
        mailbox.listActions()
            .filterIsInstance<PlayerConnectedAction>()
            .forEach(this::handleConnectedAction)

        mailbox.listActions()
            .filterIsInstance<PlayerDisconnectedAction>()
            .forEach(this::handleDisconnectedAction)
    }

    private fun handleConnectedAction(it: PlayerConnectedAction) {
        val eid = db.entities.create(
            PlayerEntityType.TYPE,
            mutableSetOf(
                PlayerIdentifierComponent(
                    displayName = it.identifiers.displayName!!,
                    uuid = it.identifiers.playerUUID!!
                ),
                EntityPositionComponent(UNIVERSAL_SPAWN, UNIVERSAL_ROT), // TODO: get proper spawn logic
                EntityChunkLoadingComponent(radius = 4), // TODO: move this to chunk loading?
                PlayerHotBarComponent(selectedSlot = 0) // TODO: move somewhere else?
            )
        )
        db.invitems.create(InventoryMetadata(eid, INVENTORY_NAME, INVENTORY_SIZE))
        db.invitems.place(
            InvSlotRef(eid, INVENTORY_NAME, 0),
            StickItemType(),
            mapOf(ItemCountComponent::class.java to ItemCountComponent(1))
        )
        db.invitems.place(
            InvSlotRef(eid, INVENTORY_NAME, 1),
            BlockItemType(DirtBlockType.primary),
            mapOf(ItemCountComponent::class.java to ItemCountComponent(1))
        )
        db.invitems.place(
            InvSlotRef(eid, INVENTORY_NAME, 2),
            BlockItemType(DiamondBlockType.primary),
            mapOf(ItemCountComponent::class.java to ItemCountComponent(10))
        )
        db.invitems.create(InventoryMetadata(eid, ARMOR_INVENTORY_NAME, ARMOR_INVENTORY_SIZE))
        db.invitems.create(InventoryMetadata(eid, OFFHAND_INVENTORY_NAME, OFFHAND_INVENTORY_SIZE))

        mapContext.onPlayerConnected(it.identifiers, eid)
    }

    private fun handleDisconnectedAction(it: PlayerDisconnectedAction) {
        val eid = mapContext.findPlayerByUUID(it.identifiers.playerUUID!!)
        mapContext.onPlayerDisconnected(it.identifiers)
        if (eid != null) {
            db.invitems.destroy(InvRef(eid, OFFHAND_INVENTORY_NAME))
            db.invitems.destroy(InvRef(eid, ARMOR_INVENTORY_NAME))
            db.invitems.destroy(InvRef(eid, INVENTORY_NAME))
            db.entities.destroy(eid)
        }
    }
}
