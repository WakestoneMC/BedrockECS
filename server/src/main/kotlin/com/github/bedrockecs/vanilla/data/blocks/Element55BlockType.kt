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
public data class Element55BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element55BlockType {
        val e = Element55BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element55BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_55"

        public override val itemID: ItemID = ItemID(-66)

        private lateinit var instance4668: Element55BlockType

        init {
            init0()
        }

        public override val primary: Element55BlockType = instance4668

        public override val allInstances: List<Element55BlockType> = listOf(instance4668)

        public fun init0() {
            instance4668 = Element55BlockType(4668.toShort())
        }
    }
}