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
public data class Element115BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element115BlockType {
        val e = Element115BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element115BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_115"

        public override val itemID: ItemID = ItemID(-126)

        private lateinit var instance4617: Element115BlockType

        init {
            init0()
        }

        public override val primary: Element115BlockType = instance4617

        public override val allInstances: List<Element115BlockType> = listOf(instance4617)

        public fun init0() {
            instance4617 = Element115BlockType(4617.toShort())
        }
    }
}
