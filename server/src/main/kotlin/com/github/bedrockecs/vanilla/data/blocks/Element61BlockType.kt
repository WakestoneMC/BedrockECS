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
public data class Element61BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element61BlockType {
        val e = Element61BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element61BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_61"

        public override val itemID: ItemID = ItemID(-72)

        private lateinit var instance4675: Element61BlockType

        init {
            init0()
        }

        public override val primary: Element61BlockType = instance4675

        public override val allInstances: List<Element61BlockType> = listOf(instance4675)

        public fun init0() {
            instance4675 = Element61BlockType(4675.toShort())
        }
    }
}
