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
public data class Element30BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element30BlockType {
        val e = Element30BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element30BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_30"

        public override val itemID: ItemID = ItemID(-41)

        private lateinit var instance4641: Element30BlockType

        init {
            init0()
        }

        public override val primary: Element30BlockType = instance4641

        public override val allInstances: List<Element30BlockType> = listOf(instance4641)

        public fun init0() {
            instance4641 = Element30BlockType(4641.toShort())
        }
    }
}
