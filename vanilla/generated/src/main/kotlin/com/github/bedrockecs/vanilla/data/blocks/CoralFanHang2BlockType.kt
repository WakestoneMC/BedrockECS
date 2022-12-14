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
public data class CoralFanHang2BlockType private constructor(
    public override val runtimeID: Short,
    public val coralDirection: Int,
    public val coralHangTypeBit: Byte,
    public val deadBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        coralDirection: Int = this.coralDirection,
        coralHangTypeBit: Byte = this.coralHangTypeBit,
        deadBit: Byte = this.deadBit
    ): CoralFanHang2BlockType {
        val e = CoralFanHang2BlockType(0.toShort(), coralDirection, coralHangTypeBit, deadBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CoralFanHang2BlockType): Boolean {
        var ret = true
        ret = ret && (this.coralDirection == other.coralDirection)
        ret = ret && (this.coralHangTypeBit == other.coralHangTypeBit)
        ret = ret && (this.deadBit == other.deadBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:coral_fan_hang2"

        public override val itemID: ItemID = ItemID(-136)

        private lateinit var instance3735: CoralFanHang2BlockType

        private lateinit var instance3736: CoralFanHang2BlockType

        private lateinit var instance3737: CoralFanHang2BlockType

        private lateinit var instance3738: CoralFanHang2BlockType

        private lateinit var instance3739: CoralFanHang2BlockType

        private lateinit var instance3740: CoralFanHang2BlockType

        private lateinit var instance3741: CoralFanHang2BlockType

        private lateinit var instance3742: CoralFanHang2BlockType

        private lateinit var instance3743: CoralFanHang2BlockType

        private lateinit var instance3744: CoralFanHang2BlockType

        private lateinit var instance3745: CoralFanHang2BlockType

        private lateinit var instance3746: CoralFanHang2BlockType

        private lateinit var instance3747: CoralFanHang2BlockType

        private lateinit var instance3748: CoralFanHang2BlockType

        private lateinit var instance3749: CoralFanHang2BlockType

        private lateinit var instance3750: CoralFanHang2BlockType

        init {
            init0()
        }

        public override val primary: CoralFanHang2BlockType = instance3735

        public override val allInstances: List<CoralFanHang2BlockType> =
            listOf(instance3735, instance3736, instance3737, instance3738, instance3739, instance3740, instance3741, instance3742, instance3743, instance3744, instance3745, instance3746, instance3747, instance3748, instance3749, instance3750)

        public fun init0() {
            instance3735 = CoralFanHang2BlockType(3735.toShort(), 0, 0.toByte(), 0.toByte())
            instance3736 = CoralFanHang2BlockType(3736.toShort(), 0, 1.toByte(), 0.toByte())
            instance3737 = CoralFanHang2BlockType(3737.toShort(), 0, 0.toByte(), 1.toByte())
            instance3738 = CoralFanHang2BlockType(3738.toShort(), 0, 1.toByte(), 1.toByte())
            instance3739 = CoralFanHang2BlockType(3739.toShort(), 1, 0.toByte(), 0.toByte())
            instance3740 = CoralFanHang2BlockType(3740.toShort(), 1, 1.toByte(), 0.toByte())
            instance3741 = CoralFanHang2BlockType(3741.toShort(), 1, 0.toByte(), 1.toByte())
            instance3742 = CoralFanHang2BlockType(3742.toShort(), 1, 1.toByte(), 1.toByte())
            instance3743 = CoralFanHang2BlockType(3743.toShort(), 2, 0.toByte(), 0.toByte())
            instance3744 = CoralFanHang2BlockType(3744.toShort(), 2, 1.toByte(), 0.toByte())
            instance3745 = CoralFanHang2BlockType(3745.toShort(), 2, 0.toByte(), 1.toByte())
            instance3746 = CoralFanHang2BlockType(3746.toShort(), 2, 1.toByte(), 1.toByte())
            instance3747 = CoralFanHang2BlockType(3747.toShort(), 3, 0.toByte(), 0.toByte())
            instance3748 = CoralFanHang2BlockType(3748.toShort(), 3, 1.toByte(), 0.toByte())
            instance3749 = CoralFanHang2BlockType(3749.toShort(), 3, 0.toByte(), 1.toByte())
            instance3750 = CoralFanHang2BlockType(3750.toShort(), 3, 1.toByte(), 1.toByte())
        }
    }
}
