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
public data class Element5BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element5BlockType {
        val e = Element5BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element5BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_5"

        public override val itemID: ItemID = ItemID(-16)

        private lateinit var instance4662: Element5BlockType

        init {
            init0()
        }

        public override val primary: Element5BlockType = instance4662

        public override val allInstances: List<Element5BlockType> = listOf(instance4662)

        public fun init0() {
            instance4662 = Element5BlockType(4662.toShort())
        }
    }
}
