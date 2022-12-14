// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Byte
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class WeatheredDoubleCutCopperSlabBlockType private constructor(
    public override val runtimeID: Short,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(topSlotBit: Byte = this.topSlotBit): WeatheredDoubleCutCopperSlabBlockType {
        val e = WeatheredDoubleCutCopperSlabBlockType(0.toShort(), topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WeatheredDoubleCutCopperSlabBlockType): Boolean {
        var ret = true
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:weathered_double_cut_copper_slab"

        public override val itemID: ItemID = ItemID(-370)

        private lateinit var instance7751: WeatheredDoubleCutCopperSlabBlockType

        private lateinit var instance7752: WeatheredDoubleCutCopperSlabBlockType

        init {
            init0()
        }

        public override val primary: WeatheredDoubleCutCopperSlabBlockType = instance7751

        public override val allInstances: List<WeatheredDoubleCutCopperSlabBlockType> =
            listOf(instance7751, instance7752)

        public fun init0() {
            instance7751 = WeatheredDoubleCutCopperSlabBlockType(7751.toShort(), 0.toByte())
            instance7752 = WeatheredDoubleCutCopperSlabBlockType(7752.toShort(), 1.toByte())
        }
    }
}
