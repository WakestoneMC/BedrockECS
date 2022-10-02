package com.github.bedrockecs.vanilla.data.blocks

import kotlin.reflect.KClass

object VanillaBlockTypes {
    val CLASSES: List<KClass<out VanillaBlockType>>

    val TYPES: List<VanillaBlockType>

    init {
        val types = mutableListOf<VanillaBlockType>()
        val classes = mutableListOf<KClass<out VanillaBlockType>>()
        classes.add(AirBlockType::class)
        types.addAll(AirBlockType.allInstances)
        classes.add(DirtBlockType::class)
        types.addAll(DirtBlockType.allInstances)
        classes.add(DiamondBlockType::class)
        types.addAll(DiamondBlockType.allInstances)
        CLASSES = classes
        TYPES = types.sortedBy { it.runtimeID }
    }
}
