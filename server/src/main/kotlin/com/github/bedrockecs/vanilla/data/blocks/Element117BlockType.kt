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
public data class Element117BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element117BlockType {
        val e = Element117BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element117BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_117"

        public override val itemID: ItemID = ItemID(-128)

        private lateinit var instance4619: Element117BlockType

        init {
            init0()
        }

        public override val primary: Element117BlockType = instance4619

        public override val allInstances: List<Element117BlockType> = listOf(instance4619)

        public fun init0() {
            instance4619 = Element117BlockType(4619.toShort())
        }
    }
}
