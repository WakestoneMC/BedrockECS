// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class Element80BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element80BlockType {
        val e = Element80BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element80BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_80"

        public override val itemID: ItemID = ItemID(-91)

        private lateinit var instance4696: Element80BlockType

        init {
            init0()
        }

        public override val primary: Element80BlockType = instance4696

        public override val allInstances: List<Element80BlockType> = listOf(instance4696)

        public fun init0() {
            instance4696 = Element80BlockType(4696.toShort())
        }
    }
}