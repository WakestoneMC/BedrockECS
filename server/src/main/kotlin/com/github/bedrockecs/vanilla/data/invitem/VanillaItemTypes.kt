package com.github.bedrockecs.vanilla.data.invitem

import kotlin.reflect.KClass

object VanillaItemTypes {
    val CLASSES: List<KClass<out VanillaItemType>>

    init {
        val types = mutableListOf<KClass<out VanillaItemType>>()
        types.add(StickItemType::class)
        CLASSES = types
    }
}