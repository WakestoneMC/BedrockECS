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
public data class Element36BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element36BlockType {
        val e = Element36BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element36BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_36"

        public override val itemID: ItemID = ItemID(-47)

        private lateinit var instance4647: Element36BlockType

        init {
            init0()
        }

        public override val primary: Element36BlockType = instance4647

        public override val allInstances: List<Element36BlockType> = listOf(instance4647)

        public fun init0() {
            instance4647 = Element36BlockType(4647.toShort())
        }
    }
}
