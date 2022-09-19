package com.github.bedrockecs.vanilla.blocks.world

object VanillaBlockTypes {
    val TYPES: List<VanillaBlockType>

    init {
        val types = mutableListOf<VanillaBlockType>()
        types.addAll(AirBlockType.allInstances)
        types.addAll(DirtBlockType.allInstances)
        TYPES = types.sortedBy { it.runtimeID }
    }
}
