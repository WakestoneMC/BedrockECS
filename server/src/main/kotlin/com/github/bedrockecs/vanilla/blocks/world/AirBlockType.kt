package com.github.bedrockecs.vanilla.blocks.world

class AirBlockType private constructor() : VanillaBlockType {
    companion object {
        val instance134 = AirBlockType()
        val allInstances: List<AirBlockType> = listOf(instance134)
    }

    override val blockType: String
        get() = "minecraft:air"

    override val runtimeID: Short
        get() = 134
}
