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
public data class Element86BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element86BlockType {
        val e = Element86BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element86BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_86"

        public override val itemID: ItemID = ItemID(-97)

        private lateinit var instance4702: Element86BlockType

        init {
            init0()
        }

        public override val primary: Element86BlockType = instance4702

        public override val allInstances: List<Element86BlockType> = listOf(instance4702)

        public fun init0() {
            instance4702 = Element86BlockType(4702.toShort())
        }
    }
}
