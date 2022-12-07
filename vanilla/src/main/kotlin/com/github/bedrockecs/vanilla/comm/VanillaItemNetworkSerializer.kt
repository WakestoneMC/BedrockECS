package com.github.bedrockecs.vanilla.comm

import com.github.bedrockecs.comm.serial.ItemNetworkSerializer
import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.invitem.data.ItemComponent
import com.github.bedrockecs.game.db.invitem.data.ItemCountComponent
import com.github.bedrockecs.game.db.invitem.data.ItemTypeComponent
import com.github.bedrockecs.vanilla.data.items.VanillaItemType
import com.nukkitx.protocol.bedrock.data.inventory.ItemData
import org.springframework.stereotype.Component
import kotlin.reflect.full.companionObjectInstance

@Component
class VanillaItemNetworkSerializer : ItemNetworkSerializer {

    override val order: Int = 2000

    override fun serialize(components: ComponentMap<ItemComponent>): ItemData? {
        val type = components[ItemTypeComponent::class.java]!!
        if (type is VanillaItemType) {
            val companion = type::class.java.kotlin.companionObjectInstance as VanillaItemType.ICompanion
            val itemId = companion.itemID

            val countComponent = components[ItemCountComponent::class.java] as ItemCountComponent?
            val count = countComponent?.count ?: 0

            return ItemData.builder().apply {
                // basics
                id(itemId.value)
                count(count)
                damage(0) // TODO: figure this out for items
                // netId
                usingNetId(true)
                netId(0)
                // block
                blockRuntimeId(0)
            }.build()
        }
        return null
    }
}
