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
public data class Element28BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element28BlockType {
        val e = Element28BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element28BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_28"

        public override val itemID: ItemID = ItemID(-39)

        private lateinit var instance4638: Element28BlockType

        init {
            init0()
        }

        public override val primary: Element28BlockType = instance4638

        public override val allInstances: List<Element28BlockType> = listOf(instance4638)

        public fun init0() {
            instance4638 = Element28BlockType(4638.toShort())
        }
    }
}
