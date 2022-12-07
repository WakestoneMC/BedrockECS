package com.github.bedrockecs.comm.zimpl.serial

import com.github.bedrockecs.server.game.db.invitem.InvItemStore
import com.github.bedrockecs.server.game.db.invitem.InvRef
import com.github.bedrockecs.server.game.db.invitem.InvSlotRef
import com.nukkitx.protocol.bedrock.data.inventory.ItemData
import org.springframework.stereotype.Component

@Component
class InventoryNetworkSerializer(
    private val serializer: DispatchingItemNetworkSerializer
) {
    companion object {
        val PLAYER_INVENTORY_CONTAINER_ID = 0

        val PLAYER_ARMOR_CONTAINER_ID = 120

        val PLAYER_OFFHAND_CONTAINER_ID = 119
    }

    // serialization //

    fun serializeInventory(db: InvItemStore, invRef: InvRef): Array<ItemData> {
        val metadata = db.readMetadata(invRef)
        return (0 until metadata.size)
            .map { slot ->
                val slotRef = InvSlotRef(invRef.owner, invRef.name, slot)
                if (db.isEmpty(slotRef)) {
                    ItemData.AIR
                } else {
                    serializer.serialize(slotRef, db.list(slotRef))
                }
            }
            .toTypedArray()
    }
}
