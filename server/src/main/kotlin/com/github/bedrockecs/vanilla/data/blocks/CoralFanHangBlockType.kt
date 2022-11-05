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
public data class CoralFanHangBlockType private constructor(
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
    ): CoralFanHangBlockType {
        val e = CoralFanHangBlockType(0.toShort(), coralDirection, coralHangTypeBit, deadBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CoralFanHangBlockType): Boolean {
        var ret = true
        ret = ret && (this.coralDirection == other.coralDirection)
        ret = ret && (this.coralHangTypeBit == other.coralHangTypeBit)
        ret = ret && (this.deadBit == other.deadBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:coral_fan_hang"

        public override val itemID: ItemID = ItemID(-135)

        private lateinit var instance3719: CoralFanHangBlockType

        private lateinit var instance3720: CoralFanHangBlockType

        private lateinit var instance3721: CoralFanHangBlockType

        private lateinit var instance3722: CoralFanHangBlockType

        private lateinit var instance3723: CoralFanHangBlockType

        private lateinit var instance3724: CoralFanHangBlockType

        private lateinit var instance3725: CoralFanHangBlockType

        private lateinit var instance3726: CoralFanHangBlockType

        private lateinit var instance3727: CoralFanHangBlockType

        private lateinit var instance3728: CoralFanHangBlockType

        private lateinit var instance3729: CoralFanHangBlockType

        private lateinit var instance3730: CoralFanHangBlockType

        private lateinit var instance3731: CoralFanHangBlockType

        private lateinit var instance3732: CoralFanHangBlockType

        private lateinit var instance3733: CoralFanHangBlockType

        private lateinit var instance3734: CoralFanHangBlockType

        init {
            init0()
        }

        public override val primary: CoralFanHangBlockType = instance3719

        public override val allInstances: List<CoralFanHangBlockType> =
            listOf(instance3719, instance3720, instance3721, instance3722, instance3723, instance3724, instance3725, instance3726, instance3727, instance3728, instance3729, instance3730, instance3731, instance3732, instance3733, instance3734)

        public fun init0() {
            instance3719 = CoralFanHangBlockType(3719.toShort(), 0, 0.toByte(), 0.toByte())
            instance3720 = CoralFanHangBlockType(3720.toShort(), 0, 1.toByte(), 0.toByte())
            instance3721 = CoralFanHangBlockType(3721.toShort(), 0, 0.toByte(), 1.toByte())
            instance3722 = CoralFanHangBlockType(3722.toShort(), 0, 1.toByte(), 1.toByte())
            instance3723 = CoralFanHangBlockType(3723.toShort(), 1, 0.toByte(), 0.toByte())
            instance3724 = CoralFanHangBlockType(3724.toShort(), 1, 1.toByte(), 0.toByte())
            instance3725 = CoralFanHangBlockType(3725.toShort(), 1, 0.toByte(), 1.toByte())
            instance3726 = CoralFanHangBlockType(3726.toShort(), 1, 1.toByte(), 1.toByte())
            instance3727 = CoralFanHangBlockType(3727.toShort(), 2, 0.toByte(), 0.toByte())
            instance3728 = CoralFanHangBlockType(3728.toShort(), 2, 1.toByte(), 0.toByte())
            instance3729 = CoralFanHangBlockType(3729.toShort(), 2, 0.toByte(), 1.toByte())
            instance3730 = CoralFanHangBlockType(3730.toShort(), 2, 1.toByte(), 1.toByte())
            instance3731 = CoralFanHangBlockType(3731.toShort(), 3, 0.toByte(), 0.toByte())
            instance3732 = CoralFanHangBlockType(3732.toShort(), 3, 1.toByte(), 0.toByte())
            instance3733 = CoralFanHangBlockType(3733.toShort(), 3, 0.toByte(), 1.toByte())
            instance3734 = CoralFanHangBlockType(3734.toShort(), 3, 1.toByte(), 1.toByte())
        }
    }
}
