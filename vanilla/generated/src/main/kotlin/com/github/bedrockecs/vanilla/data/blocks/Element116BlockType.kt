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
public data class Element116BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element116BlockType {
        val e = Element116BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element116BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_116"

        public override val itemID: ItemID = ItemID(-127)

        private lateinit var instance4618: Element116BlockType

        init {
            init0()
        }

        public override val primary: Element116BlockType = instance4618

        public override val allInstances: List<Element116BlockType> = listOf(instance4618)

        public fun init0() {
            instance4618 = Element116BlockType(4618.toShort())
        }
    }
}
