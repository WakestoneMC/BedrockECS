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
public data class Element94BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element94BlockType {
        val e = Element94BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element94BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_94"

        public override val itemID: ItemID = ItemID(-105)

        private lateinit var instance4711: Element94BlockType

        init {
            init0()
        }

        public override val primary: Element94BlockType = instance4711

        public override val allInstances: List<Element94BlockType> = listOf(instance4711)

        public fun init0() {
            instance4711 = Element94BlockType(4711.toShort())
        }
    }
}
