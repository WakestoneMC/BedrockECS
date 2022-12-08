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
public data class Element64BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element64BlockType {
        val e = Element64BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element64BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_64"

        public override val itemID: ItemID = ItemID(-75)

        private lateinit var instance4678: Element64BlockType

        init {
            init0()
        }

        public override val primary: Element64BlockType = instance4678

        public override val allInstances: List<Element64BlockType> = listOf(instance4678)

        public fun init0() {
            instance4678 = Element64BlockType(4678.toShort())
        }
    }
}