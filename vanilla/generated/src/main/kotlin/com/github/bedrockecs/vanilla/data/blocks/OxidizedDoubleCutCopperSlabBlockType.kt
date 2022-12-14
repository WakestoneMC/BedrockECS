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
public data class OxidizedDoubleCutCopperSlabBlockType private constructor(
    public override val runtimeID: Short,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(topSlotBit: Byte = this.topSlotBit): OxidizedDoubleCutCopperSlabBlockType {
        val e = OxidizedDoubleCutCopperSlabBlockType(0.toShort(), topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: OxidizedDoubleCutCopperSlabBlockType): Boolean {
        var ret = true
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:oxidized_double_cut_copper_slab"

        public override val itemID: ItemID = ItemID(-371)

        private lateinit var instance5768: OxidizedDoubleCutCopperSlabBlockType

        private lateinit var instance5769: OxidizedDoubleCutCopperSlabBlockType

        init {
            init0()
        }

        public override val primary: OxidizedDoubleCutCopperSlabBlockType = instance5768

        public override val allInstances: List<OxidizedDoubleCutCopperSlabBlockType> =
            listOf(instance5768, instance5769)

        public fun init0() {
            instance5768 = OxidizedDoubleCutCopperSlabBlockType(5768.toShort(), 0.toByte())
            instance5769 = OxidizedDoubleCutCopperSlabBlockType(5769.toShort(), 1.toByte())
        }
    }
}
