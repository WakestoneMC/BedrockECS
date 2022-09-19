package com.github.bedrockecs.vanilla.blocks.world

class DirtBlockType private constructor() : VanillaBlockType {
    companion object {
        val instance4484 = DirtBlockType()
        val allInstances: List<DirtBlockType> = listOf(instance4484)
    }

    override val blockType: String
        get() = "minecraft:air"

    override val runtimeID: Short
        get() = 4484
}
