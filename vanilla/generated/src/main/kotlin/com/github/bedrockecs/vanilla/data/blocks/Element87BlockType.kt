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
public data class Element87BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element87BlockType {
        val e = Element87BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element87BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_87"

        public override val itemID: ItemID = ItemID(-98)

        private lateinit var instance4703: Element87BlockType

        init {
            init0()
        }

        public override val primary: Element87BlockType = instance4703

        public override val allInstances: List<Element87BlockType> = listOf(instance4703)

        public fun init0() {
            instance4703 = Element87BlockType(4703.toShort())
        }
    }
}
