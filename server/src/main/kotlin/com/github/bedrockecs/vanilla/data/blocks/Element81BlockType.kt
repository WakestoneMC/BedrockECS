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
public data class Element81BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element81BlockType {
        val e = Element81BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element81BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_81"

        public override val itemID: ItemID = ItemID(-92)

        private lateinit var instance4697: Element81BlockType

        init {
            init0()
        }

        public override val primary: Element81BlockType = instance4697

        public override val allInstances: List<Element81BlockType> = listOf(instance4697)

        public fun init0() {
            instance4697 = Element81BlockType(4697.toShort())
        }
    }
}
