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
public data class Element82BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element82BlockType {
        val e = Element82BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element82BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_82"

        public override val itemID: ItemID = ItemID(-93)

        private lateinit var instance4698: Element82BlockType

        init {
            init0()
        }

        public override val primary: Element82BlockType = instance4698

        public override val allInstances: List<Element82BlockType> = listOf(instance4698)

        public fun init0() {
            instance4698 = Element82BlockType(4698.toShort())
        }
    }
}
