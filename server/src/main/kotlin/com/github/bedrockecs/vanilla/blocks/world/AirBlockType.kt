package com.github.bedrockecs.vanilla.blocks.world

class AirBlockType private constructor(
    override val runtimeID: Short
) : VanillaBlockType {
    companion object {
        private val instance134 = AirBlockType(134)
        val primary = instance134
        val allInstances: List<AirBlockType> = listOf(instance134)
    }

    fun with(): AirBlockType {
        val e = AirBlockType(0)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    private fun compareVariantProperties(other: AirBlockType): Boolean {
        var ret = true
        return ret
    }

    override val blockType: String
        get() = "minecraft:air"
}
