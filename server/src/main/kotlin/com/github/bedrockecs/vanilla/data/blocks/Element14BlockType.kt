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
public data class Element14BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element14BlockType {
        val e = Element14BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element14BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_14"

        public override val itemID: ItemID = ItemID(-25)

        private lateinit var instance4623: Element14BlockType

        init {
            init0()
        }

        public override val primary: Element14BlockType = instance4623

        public override val allInstances: List<Element14BlockType> = listOf(instance4623)

        public fun init0() {
            instance4623 = Element14BlockType(4623.toShort())
        }
    }
}