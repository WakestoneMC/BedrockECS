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
public data class AcaciaDoorBlockType private constructor(
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
    ): AcaciaDoorBlockType {
        val e = AcaciaDoorBlockType(0.toShort(), direction, doorHingeBit, openBit, upperBlockBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: AcaciaDoorBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.doorHingeBit == other.doorHingeBit)
        ret = ret && (this.openBit == other.openBit)
        ret = ret && (this.upperBlockBit == other.upperBlockBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:acacia_door"

        public override val itemID: ItemID = ItemID(196)

        private lateinit var instance12: AcaciaDoorBlockType

        private lateinit var instance13: AcaciaDoorBlockType

        private lateinit var instance14: AcaciaDoorBlockType

        private lateinit var instance15: AcaciaDoorBlockType

        private lateinit var instance16: AcaciaDoorBlockType

        private lateinit var instance17: AcaciaDoorBlockType

        private lateinit var instance18: AcaciaDoorBlockType

        private lateinit var instance19: AcaciaDoorBlockType

        private lateinit var instance20: AcaciaDoorBlockType

        private lateinit var instance21: AcaciaDoorBlockType

        private lateinit var instance22: AcaciaDoorBlockType

        private lateinit var instance23: AcaciaDoorBlockType

        private lateinit var instance24: AcaciaDoorBlockType

        private lateinit var instance25: AcaciaDoorBlockType

        private lateinit var instance26: AcaciaDoorBlockType

        private lateinit var instance27: AcaciaDoorBlockType

        private lateinit var instance28: AcaciaDoorBlockType

        private lateinit var instance29: AcaciaDoorBlockType

        private lateinit var instance30: AcaciaDoorBlockType

        private lateinit var instance31: AcaciaDoorBlockType

        private lateinit var instance32: AcaciaDoorBlockType

        private lateinit var instance33: AcaciaDoorBlockType

        private lateinit var instance34: AcaciaDoorBlockType

        private lateinit var instance35: AcaciaDoorBlockType

        private lateinit var instance36: AcaciaDoorBlockType

        private lateinit var instance37: AcaciaDoorBlockType

        private lateinit var instance38: AcaciaDoorBlockType

        private lateinit var instance39: AcaciaDoorBlockType

        private lateinit var instance40: AcaciaDoorBlockType

        private lateinit var instance41: AcaciaDoorBlockType

        private lateinit var instance42: AcaciaDoorBlockType

        private lateinit var instance43: AcaciaDoorBlockType

        init {
            init0()
        }

        public override val primary: AcaciaDoorBlockType = instance12

        public override val allInstances: List<AcaciaDoorBlockType> =
            listOf(instance12, instance13, instance14, instance15, instance16, instance17, instance18, instance19, instance20, instance21, instance22, instance23, instance24, instance25, instance26, instance27, instance28, instance29, instance30, instance31, instance32, instance33, instance34, instance35, instance36, instance37, instance38, instance39, instance40, instance41, instance42, instance43)

        public fun init0() {
            instance12 = AcaciaDoorBlockType(12.toShort(), 0, 0.toByte(), 0.toByte(), 0.toByte())
            instance13 = AcaciaDoorBlockType(13.toShort(), 1, 0.toByte(), 0.toByte(), 0.toByte())
            instance14 = AcaciaDoorBlockType(14.toShort(), 2, 0.toByte(), 0.toByte(), 0.toByte())
            instance15 = AcaciaDoorBlockType(15.toShort(), 3, 0.toByte(), 0.toByte(), 0.toByte())
            instance16 = AcaciaDoorBlockType(16.toShort(), 0, 0.toByte(), 1.toByte(), 0.toByte())
            instance17 = AcaciaDoorBlockType(17.toShort(), 1, 0.toByte(), 1.toByte(), 0.toByte())
            instance18 = AcaciaDoorBlockType(18.toShort(), 2, 0.toByte(), 1.toByte(), 0.toByte())
            instance19 = AcaciaDoorBlockType(19.toShort(), 3, 0.toByte(), 1.toByte(), 0.toByte())
            instance20 = AcaciaDoorBlockType(20.toShort(), 0, 0.toByte(), 0.toByte(), 1.toByte())
            instance21 = AcaciaDoorBlockType(21.toShort(), 1, 0.toByte(), 0.toByte(), 1.toByte())
            instance22 = AcaciaDoorBlockType(22.toShort(), 2, 0.toByte(), 0.toByte(), 1.toByte())
            instance23 = AcaciaDoorBlockType(23.toShort(), 3, 0.toByte(), 0.toByte(), 1.toByte())
            instance24 = AcaciaDoorBlockType(24.toShort(), 0, 0.toByte(), 1.toByte(), 1.toByte())
            instance25 = AcaciaDoorBlockType(25.toShort(), 1, 0.toByte(), 1.toByte(), 1.toByte())
            instance26 = AcaciaDoorBlockType(26.toShort(), 2, 0.toByte(), 1.toByte(), 1.toByte())
            instance27 = AcaciaDoorBlockType(27.toShort(), 3, 0.toByte(), 1.toByte(), 1.toByte())
            instance28 = AcaciaDoorBlockType(28.toShort(), 0, 1.toByte(), 0.toByte(), 0.toByte())
            instance29 = AcaciaDoorBlockType(29.toShort(), 1, 1.toByte(), 0.toByte(), 0.toByte())
            instance30 = AcaciaDoorBlockType(30.toShort(), 2, 1.toByte(), 0.toByte(), 0.toByte())
            instance31 = AcaciaDoorBlockType(31.toShort(), 3, 1.toByte(), 0.toByte(), 0.toByte())
            instance32 = AcaciaDoorBlockType(32.toShort(), 0, 1.toByte(), 1.toByte(), 0.toByte())
            instance33 = AcaciaDoorBlockType(33.toShort(), 1, 1.toByte(), 1.toByte(), 0.toByte())
            instance34 = AcaciaDoorBlockType(34.toShort(), 2, 1.toByte(), 1.toByte(), 0.toByte())
            instance35 = AcaciaDoorBlockType(35.toShort(), 3, 1.toByte(), 1.toByte(), 0.toByte())
            instance36 = AcaciaDoorBlockType(36.toShort(), 0, 1.toByte(), 0.toByte(), 1.toByte())
            instance37 = AcaciaDoorBlockType(37.toShort(), 1, 1.toByte(), 0.toByte(), 1.toByte())
            instance38 = AcaciaDoorBlockType(38.toShort(), 2, 1.toByte(), 0.toByte(), 1.toByte())
            instance39 = AcaciaDoorBlockType(39.toShort(), 3, 1.toByte(), 0.toByte(), 1.toByte())
            instance40 = AcaciaDoorBlockType(40.toShort(), 0, 1.toByte(), 1.toByte(), 1.toByte())
            instance41 = AcaciaDoorBlockType(41.toShort(), 1, 1.toByte(), 1.toByte(), 1.toByte())
            instance42 = AcaciaDoorBlockType(42.toShort(), 2, 1.toByte(), 1.toByte(), 1.toByte())
            instance43 = AcaciaDoorBlockType(43.toShort(), 3, 1.toByte(), 1.toByte(), 1.toByte())
        }
    }
}