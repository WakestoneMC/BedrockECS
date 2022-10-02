package com.github.bedrockecs.vanilla.data.blocks

import com.github.bedrockecs.vanilla.data.items.ItemID

data class AirBlockType private constructor(
    override val runtimeID: Short
) : VanillaBlockType {
    companion object : VanillaBlockType.Companion {
        override val itemID: ItemID = ItemID(0)
        private val instance134 = AirBlockType(134)
        val primary = instance134
        val allInstances: List<AirBlockType> = listOf(instance134)
    }

    fun with(): AirBlockType {
        val e = primary.copy()
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    private fun compareVariantProperties(other: AirBlockType): Boolean {
        var ret = true
        return ret
    }

    override val blockType: String
        get() = "minecraft:air"
}
