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
public data class Element91BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element91BlockType {
        val e = Element91BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element91BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_91"

        public override val itemID: ItemID = ItemID(-102)

        private lateinit var instance4708: Element91BlockType

        init {
            init0()
        }

        public override val primary: Element91BlockType = instance4708

        public override val allInstances: List<Element91BlockType> = listOf(instance4708)

        public fun init0() {
            instance4708 = Element91BlockType(4708.toShort())
        }
    }
}