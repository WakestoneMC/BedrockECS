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
public data class LapisBlockBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): LapisBlockBlockType {
        val e = LapisBlockBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: LapisBlockBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:lapis_block"

        public override val itemID: ItemID = ItemID(22)

        private lateinit var instance5366: LapisBlockBlockType

        init {
            init0()
        }

        public override val primary: LapisBlockBlockType = instance5366

        public override val allInstances: List<LapisBlockBlockType> = listOf(instance5366)

        public fun init0() {
            instance5366 = LapisBlockBlockType(5366.toShort())
        }
    }
}
