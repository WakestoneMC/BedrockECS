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
public data class Element53BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element53BlockType {
        val e = Element53BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element53BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_53"

        public override val itemID: ItemID = ItemID(-64)

        private lateinit var instance4666: Element53BlockType

        init {
            init0()
        }

        public override val primary: Element53BlockType = instance4666

        public override val allInstances: List<Element53BlockType> = listOf(instance4666)

        public fun init0() {
            instance4666 = Element53BlockType(4666.toShort())
        }
    }
}
