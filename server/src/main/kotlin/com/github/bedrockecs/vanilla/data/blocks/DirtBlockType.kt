package com.github.bedrockecs.vanilla.data.blocks

import com.github.bedrockecs.vanilla.data.items.ItemID

data class DirtBlockType private constructor(
    override val runtimeID: Short,
    val dirtType: String
) : VanillaBlockType {
    companion object : VanillaBlockType.Companion {
        override val blockType: String = "minecraft:dirt"
        override val itemID: ItemID = ItemID(3)
        private val instance4484 = DirtBlockType(4484, "normal")
        private val instance4485 = DirtBlockType(4485, "coarse")
        override val primary = instance4484
        override val allInstances: List<DirtBlockType> = listOf(instance4484, instance4485)
    }

    fun with(dirtType: String = this.dirtType): DirtBlockType {
        val e = DirtBlockType.primary.copy(dirtType = dirtType)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    private fun compareVariantProperties(other: DirtBlockType): Boolean {
        var ret = true
        ret = ret && this.dirtType == other.dirtType
        return ret
    }

    override val blockType: String
        get() = "minecraft:dirt"
}
