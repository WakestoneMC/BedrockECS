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
public data class Element8BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element8BlockType {
        val e = Element8BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element8BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_8"

        public override val itemID: ItemID = ItemID(-19)

        private lateinit var instance4695: Element8BlockType

        init {
            init0()
        }

        public override val primary: Element8BlockType = instance4695

        public override val allInstances: List<Element8BlockType> = listOf(instance4695)

        public fun init0() {
            instance4695 = Element8BlockType(4695.toShort())
        }
    }
}