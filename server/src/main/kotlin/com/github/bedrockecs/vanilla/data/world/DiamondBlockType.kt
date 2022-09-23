package com.github.bedrockecs.vanilla.data.world

data class DiamondBlockType private constructor(
    override val runtimeID: Short
) : VanillaBlockType {
    companion object : VanillaBlockType.Companion {
        override val blockID = BlockID(57)
        private val instance4474 = DiamondBlockType(4474)
        val primary = instance4474
        val allInstances: List<DiamondBlockType> = listOf(instance4474)
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
        get() = "minecraft:air"
}
