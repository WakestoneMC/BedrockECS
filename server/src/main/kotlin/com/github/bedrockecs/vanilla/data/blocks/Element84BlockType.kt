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
public data class Element84BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element84BlockType {
        val e = Element84BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element84BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_84"

        public override val itemID: ItemID = ItemID(-95)

        private lateinit var instance4700: Element84BlockType

        init {
            init0()
        }

        public override val primary: Element84BlockType = instance4700

        public override val allInstances: List<Element84BlockType> = listOf(instance4700)

        public fun init0() {
            instance4700 = Element84BlockType(4700.toShort())
        }
    }
}
