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
public data class Element107BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element107BlockType {
        val e = Element107BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element107BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_107"

        public override val itemID: ItemID = ItemID(-118)

        private lateinit var instance4608: Element107BlockType

        init {
            init0()
        }

        public override val primary: Element107BlockType = instance4608

        public override val allInstances: List<Element107BlockType> = listOf(instance4608)

        public fun init0() {
            instance4608 = Element107BlockType(4608.toShort())
        }
    }
}