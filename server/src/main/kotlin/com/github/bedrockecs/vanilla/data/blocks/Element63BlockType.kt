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
public data class Element63BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element63BlockType {
        val e = Element63BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element63BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_63"

        public override val itemID: ItemID = ItemID(-74)

        private lateinit var instance4677: Element63BlockType

        init {
            init0()
        }

        public override val primary: Element63BlockType = instance4677

        public override val allInstances: List<Element63BlockType> = listOf(instance4677)

        public fun init0() {
            instance4677 = Element63BlockType(4677.toShort())
        }
    }
}
