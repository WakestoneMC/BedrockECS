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
public data class Element102BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element102BlockType {
        val e = Element102BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element102BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_102"

        public override val itemID: ItemID = ItemID(-113)

        private lateinit var instance4603: Element102BlockType

        init {
            init0()
        }

        public override val primary: Element102BlockType = instance4603

        public override val allInstances: List<Element102BlockType> = listOf(instance4603)

        public fun init0() {
            instance4603 = Element102BlockType(4603.toShort())
        }
    }
}
