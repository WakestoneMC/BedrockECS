// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Byte
import kotlin.Int
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class CrimsonDoorBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int,
    public val doorHingeBit: Byte,
    public val openBit: Byte,
    public val upperBlockBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        direction: Int = this.direction,
        doorHingeBit: Byte = this.doorHingeBit,
        openBit: Byte = this.openBit,
        upperBlockBit: Byte = this.upperBlockBit
    ): CrimsonDoorBlockType {
        val e = CrimsonDoorBlockType(0.toShort(), direction, doorHingeBit, openBit, upperBlockBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CrimsonDoorBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.doorHingeBit == other.doorHingeBit)
        ret = ret && (this.openBit == other.openBit)
        ret = ret && (this.upperBlockBit == other.upperBlockBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:crimson_door"

        public override val itemID: ItemID = ItemID(-244)

        private lateinit var instance3784: CrimsonDoorBlockType

        private lateinit var instance3785: CrimsonDoorBlockType

        private lateinit var instance3786: CrimsonDoorBlockType

        private lateinit var instance3787: CrimsonDoorBlockType

        private lateinit var instance3788: CrimsonDoorBlockType

        private lateinit var instance3789: CrimsonDoorBlockType

        private lateinit var instance3790: CrimsonDoorBlockType

        private lateinit var instance3791: CrimsonDoorBlockType

        private lateinit var instance3792: CrimsonDoorBlockType

        private lateinit var instance3793: CrimsonDoorBlockType

        private lateinit var instance3794: CrimsonDoorBlockType

        private lateinit var instance3795: CrimsonDoorBlockType

        private lateinit var instance3796: CrimsonDoorBlockType

        private lateinit var instance3797: CrimsonDoorBlockType

        private lateinit var instance3798: CrimsonDoorBlockType

        private lateinit var instance3799: CrimsonDoorBlockType

        private lateinit var instance3800: CrimsonDoorBlockType

        private lateinit var instance3801: CrimsonDoorBlockType

        private lateinit var instance3802: CrimsonDoorBlockType

        private lateinit var instance3803: CrimsonDoorBlockType

        private lateinit var instance3804: CrimsonDoorBlockType

        private lateinit var instance3805: CrimsonDoorBlockType

        private lateinit var instance3806: CrimsonDoorBlockType

        private lateinit var instance3807: CrimsonDoorBlockType

        private lateinit var instance3808: CrimsonDoorBlockType

        private lateinit var instance3809: CrimsonDoorBlockType

        private lateinit var instance3810: CrimsonDoorBlockType

        private lateinit var instance3811: CrimsonDoorBlockType

        private lateinit var instance3812: CrimsonDoorBlockType

        private lateinit var instance3813: CrimsonDoorBlockType

        private lateinit var instance3814: CrimsonDoorBlockType

        private lateinit var instance3815: CrimsonDoorBlockType

        init {
            init0()
        }

        public override val primary: CrimsonDoorBlockType = instance3784

        public override val allInstances: List<CrimsonDoorBlockType> =
            listOf(instance3784, instance3785, instance3786, instance3787, instance3788, instance3789, instance3790, instance3791, instance3792, instance3793, instance3794, instance3795, instance3796, instance3797, instance3798, instance3799, instance3800, instance3801, instance3802, instance3803, instance3804, instance3805, instance3806, instance3807, instance3808, instance3809, instance3810, instance3811, instance3812, instance3813, instance3814, instance3815)

        public fun init0() {
            instance3784 = CrimsonDoorBlockType(3784.toShort(), 0, 0.toByte(), 0.toByte(), 0.toByte())
            instance3785 = CrimsonDoorBlockType(3785.toShort(), 1, 0.toByte(), 0.toByte(), 0.toByte())
            instance3786 = CrimsonDoorBlockType(3786.toShort(), 2, 0.toByte(), 0.toByte(), 0.toByte())
            instance3787 = CrimsonDoorBlockType(3787.toShort(), 3, 0.toByte(), 0.toByte(), 0.toByte())
            instance3788 = CrimsonDoorBlockType(3788.toShort(), 0, 0.toByte(), 1.toByte(), 0.toByte())
            instance3789 = CrimsonDoorBlockType(3789.toShort(), 1, 0.toByte(), 1.toByte(), 0.toByte())
            instance3790 = CrimsonDoorBlockType(3790.toShort(), 2, 0.toByte(), 1.toByte(), 0.toByte())
            instance3791 = CrimsonDoorBlockType(3791.toShort(), 3, 0.toByte(), 1.toByte(), 0.toByte())
            instance3792 = CrimsonDoorBlockType(3792.toShort(), 0, 0.toByte(), 0.toByte(), 1.toByte())
            instance3793 = CrimsonDoorBlockType(3793.toShort(), 1, 0.toByte(), 0.toByte(), 1.toByte())
            instance3794 = CrimsonDoorBlockType(3794.toShort(), 2, 0.toByte(), 0.toByte(), 1.toByte())
            instance3795 = CrimsonDoorBlockType(3795.toShort(), 3, 0.toByte(), 0.toByte(), 1.toByte())
            instance3796 = CrimsonDoorBlockType(3796.toShort(), 0, 0.toByte(), 1.toByte(), 1.toByte())
            instance3797 = CrimsonDoorBlockType(3797.toShort(), 1, 0.toByte(), 1.toByte(), 1.toByte())
            instance3798 = CrimsonDoorBlockType(3798.toShort(), 2, 0.toByte(), 1.toByte(), 1.toByte())
            instance3799 = CrimsonDoorBlockType(3799.toShort(), 3, 0.toByte(), 1.toByte(), 1.toByte())
            instance3800 = CrimsonDoorBlockType(3800.toShort(), 0, 1.toByte(), 0.toByte(), 0.toByte())
            instance3801 = CrimsonDoorBlockType(3801.toShort(), 1, 1.toByte(), 0.toByte(), 0.toByte())
            instance3802 = CrimsonDoorBlockType(3802.toShort(), 2, 1.toByte(), 0.toByte(), 0.toByte())
            instance3803 = CrimsonDoorBlockType(3803.toShort(), 3, 1.toByte(), 0.toByte(), 0.toByte())
            instance3804 = CrimsonDoorBlockType(3804.toShort(), 0, 1.toByte(), 1.toByte(), 0.toByte())
            instance3805 = CrimsonDoorBlockType(3805.toShort(), 1, 1.toByte(), 1.toByte(), 0.toByte())
            instance3806 = CrimsonDoorBlockType(3806.toShort(), 2, 1.toByte(), 1.toByte(), 0.toByte())
            instance3807 = CrimsonDoorBlockType(3807.toShort(), 3, 1.toByte(), 1.toByte(), 0.toByte())
            instance3808 = CrimsonDoorBlockType(3808.toShort(), 0, 1.toByte(), 0.toByte(), 1.toByte())
            instance3809 = CrimsonDoorBlockType(3809.toShort(), 1, 1.toByte(), 0.toByte(), 1.toByte())
            instance3810 = CrimsonDoorBlockType(3810.toShort(), 2, 1.toByte(), 0.toByte(), 1.toByte())
            instance3811 = CrimsonDoorBlockType(3811.toShort(), 3, 1.toByte(), 0.toByte(), 1.toByte())
            instance3812 = CrimsonDoorBlockType(3812.toShort(), 0, 1.toByte(), 1.toByte(), 1.toByte())
            instance3813 = CrimsonDoorBlockType(3813.toShort(), 1, 1.toByte(), 1.toByte(), 1.toByte())
            instance3814 = CrimsonDoorBlockType(3814.toShort(), 2, 1.toByte(), 1.toByte(), 1.toByte())
            instance3815 = CrimsonDoorBlockType(3815.toShort(), 3, 1.toByte(), 1.toByte(), 1.toByte())
        }
    }
}