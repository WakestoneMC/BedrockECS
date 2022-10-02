package com.github.bedrockecs.vanilla.comm

import com.github.bedrockecs.server.comm.serial.ItemNetworkSerializer
import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.invitem.data.BlockItemType
import com.github.bedrockecs.server.game.db.invitem.data.ItemComponent
import com.github.bedrockecs.server.game.db.invitem.data.ItemCountComponent
import com.github.bedrockecs.server.game.db.invitem.data.ItemTypeComponent
import com.github.bedrockecs.vanilla.data.blocks.VanillaBlockType
import com.nukkitx.protocol.bedrock.data.inventory.ItemData
import org.springframework.stereotype.Component
import kotlin.reflect.full.companionObjectInstance

@Component
class VanillaBlockItemNetworkSerializer : ItemNetworkSerializer {

    override val order: Int = 1000

    override fun serialize(components: ComponentMap<ItemComponent>): ItemData? {
        val type = components[ItemTypeComponent::class.java]!!
        if (type is BlockItemType && type.block is VanillaBlockType) {
            val blockTypeCompanion = type.block::class.java.kotlin.companionObjectInstance as VanillaBlockType.Companion
            val blockID = blockTypeCompanion.blockID

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
                blockRuntimeId(type.block.runtimeID.toInt())
            }.build()
        }
        return null
    }
}
