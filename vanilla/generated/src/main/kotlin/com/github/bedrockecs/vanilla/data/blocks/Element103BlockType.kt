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
public data class Element103BlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): Element103BlockType {
        val e = Element103BlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Element103BlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:element_103"

        public override val itemID: ItemID = ItemID(-114)

        private lateinit var instance4604: Element103BlockType

        init {
            init0()
        }

        public override val primary: Element103BlockType = instance4604

        public override val allInstances: List<Element103BlockType> = listOf(instance4604)

        public fun init0() {
            instance4604 = Element103BlockType(4604.toShort())
        }
    }
}
