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
public data class Element34BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element34BlockType {
        val e = Element34BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element34BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_34"

        public override val itemID: ItemID = ItemID(-45)

        private lateinit var instance4645: Element34BlockType

        init {
            init0()
        }

        public override val primary: Element34BlockType = instance4645

        public override val allInstances: List<Element34BlockType> = listOf(instance4645)

        public fun init0() {
            instance4645 = Element34BlockType(4645.toShort())
        }
    }
}
