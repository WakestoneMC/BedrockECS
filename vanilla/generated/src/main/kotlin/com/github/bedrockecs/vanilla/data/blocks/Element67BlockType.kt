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
public data class Element67BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element67BlockType {
        val e = Element67BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element67BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_67"

        public override val itemID: ItemID = ItemID(-78)

        private lateinit var instance4681: Element67BlockType

        init {
            init0()
        }

        public override val primary: Element67BlockType = instance4681

        public override val allInstances: List<Element67BlockType> = listOf(instance4681)

        public fun init0() {
            instance4681 = Element67BlockType(4681.toShort())
        }
    }
}
