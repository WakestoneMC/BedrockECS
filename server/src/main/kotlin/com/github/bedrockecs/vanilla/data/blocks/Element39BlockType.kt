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
public data class Element39BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element39BlockType {
        val e = Element39BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element39BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_39"

        public override val itemID: ItemID = ItemID(-50)

        private lateinit var instance4650: Element39BlockType

        init {
            init0()
        }

        public override val primary: Element39BlockType = instance4650

        public override val allInstances: List<Element39BlockType> = listOf(instance4650)

        public fun init0() {
            instance4650 = Element39BlockType(4650.toShort())
        }
    }
}
