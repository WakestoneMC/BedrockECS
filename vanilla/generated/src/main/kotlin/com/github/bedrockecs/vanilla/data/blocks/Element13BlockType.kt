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
public data class Element13BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element13BlockType {
        val e = Element13BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element13BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_13"

        public override val itemID: ItemID = ItemID(-24)

        private lateinit var instance4622: Element13BlockType

        init {
            init0()
        }

        public override val primary: Element13BlockType = instance4622

        public override val allInstances: List<Element13BlockType> = listOf(instance4622)

        public fun init0() {
            instance4622 = Element13BlockType(4622.toShort())
        }
    }
}
