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
public data class PackedIceBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): PackedIceBlockType {
        val e = PackedIceBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PackedIceBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:packed_ice"

        public override val itemID: ItemID = ItemID(174)

        private lateinit var instance5770: PackedIceBlockType

        init {
            init0()
        }

        public override val primary: PackedIceBlockType = instance5770

        public override val allInstances: List<PackedIceBlockType> = listOf(instance5770)

        public fun init0() {
            instance5770 = PackedIceBlockType(5770.toShort())
        }
    }
}