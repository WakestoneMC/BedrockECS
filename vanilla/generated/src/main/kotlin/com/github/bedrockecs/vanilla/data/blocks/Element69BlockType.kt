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
public data class Element69BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element69BlockType {
        val e = Element69BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element69BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_69"

        public override val itemID: ItemID = ItemID(-80)

        private lateinit var instance4683: Element69BlockType

        init {
            init0()
        }

        public override val primary: Element69BlockType = instance4683

        public override val allInstances: List<Element69BlockType> = listOf(instance4683)

        public fun init0() {
            instance4683 = Element69BlockType(4683.toShort())
        }
    }
}
