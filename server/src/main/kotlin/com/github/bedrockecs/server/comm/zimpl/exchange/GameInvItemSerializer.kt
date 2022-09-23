package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.invitem.InvItemStore
import com.github.bedrockecs.server.game.db.invitem.InvRef
import com.github.bedrockecs.server.game.db.invitem.InvSlotRef
import com.github.bedrockecs.server.game.db.invitem.data.BlockItemType
import com.github.bedrockecs.server.game.db.invitem.data.ItemComponent
import com.github.bedrockecs.server.game.db.invitem.data.ItemCountComponent
import com.github.bedrockecs.server.game.db.invitem.data.ItemTypeComponent
import com.github.bedrockecs.vanilla.data.invitem.VanillaItemType
import com.github.bedrockecs.vanilla.data.world.VanillaBlockType
import com.nukkitx.protocol.bedrock.data.inventory.ItemData
import kotlin.reflect.full.companionObjectInstance

object GameInvItemSerializer {

    // constants //

    val PLAYER_INVENTORY_CONTAINER_ID = 0

    val PLAYER_ARMOR_CONTAINER_ID = 120

    val PLAYER_OFFHAND_CONTAINER_ID = 119

    // serialization //

    fun serializeInventory(db: InvItemStore, invRef: InvRef): Array<ItemData> {
        val metadata = db.readMetadata(invRef)
        return (0 until metadata.size)
            .map { slot ->
                val slotRef = InvSlotRef(invRef.owner, invRef.name, slot)
                if (db.isEmpty(slotRef)) {
                    serializeEmptySlot()
                } else {
                    serializeItem(db.list(slotRef))
                }
            }
            .toTypedArray()
    }

    fun serializeEmptySlot(): ItemData {
        return ItemData.AIR
    }

    fun serializeItem(components: ComponentMap<ItemComponent>): ItemData {
        val type = components[ItemTypeComponent::class.java]!! as ItemTypeComponent
        if (type is BlockItemType) {
            return serializeBlockItem(type, components)
        } else {
            return serializeNormalItem(type, components)
        }
    }

    private fun serializeBlockItem(type: BlockItemType, components: ComponentMap<ItemComponent>): ItemData {
        val vanilla = (type.block as VanillaBlockType)
        val blockID = (vanilla::class.java.kotlin.companionObjectInstance as VanillaBlockType.Companion).blockID

        val countComponent = components[ItemCountComponent::class.java] as ItemCountComponent?
        val count = countComponent?.count ?: 0

        return ItemData.builder().apply {
            // basics
            id(blockID.toItemID().value)
            count(count)
            damage(0) // TODO: figure this out for block state
            // netId
            usingNetId(true)
            netId(0)
            // block
            blockRuntimeId(vanilla.runtimeID.toInt())
        }.build()
    }

    private fun serializeNormalItem(type: ItemTypeComponent, components: ComponentMap<ItemComponent>): ItemData {
        val vanillaCompanion = type::class.java.kotlin.companionObjectInstance as VanillaItemType.Companion

        val countComponent = components[ItemCountComponent::class.java] as ItemCountComponent?
        val count = countComponent?.count ?: 0

        return ItemData.builder().apply {
            // basics
            id(vanillaCompanion.itemID.value)
            count(count)
            damage(0) // TODO: figure out what to put
            // netId
            usingNetId(true)
            netId(0)
            // block
            blockRuntimeId(0)
            // tags
            // TODO: figure this out
        }.build()
    }
}
