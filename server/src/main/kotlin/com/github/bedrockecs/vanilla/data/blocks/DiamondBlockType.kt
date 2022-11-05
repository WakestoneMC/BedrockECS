package com.github.bedrockecs.vanilla.data.blocks

import com.github.bedrockecs.vanilla.data.items.ItemID

data class DiamondBlockType private constructor(
    override val runtimeID: Short
) : VanillaBlockType {
    companion object : VanillaBlockType.ICompanion {
        override val blockType: String = "minecraft:diamond_block"
        override val itemID: ItemID = ItemID(57)
        private val instance4474 = DiamondBlockType(4474)
        override val primary = instance4474
        override val allInstances: List<DiamondBlockType> = listOf(instance4474)
    }

    fun with(): DiamondBlockType {
        val e = DiamondBlockType.primary.copy()
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    private fun compareVariantProperties(other: DiamondBlockType): Boolean {
        var ret = true
        return ret
    }

    override val blockType: String
        get() = Companion.blockType
}