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
public data class TripWireBlockType private constructor(
    public override val runtimeID: Short,
    public val attachedBit: Byte,
    public val disarmedBit: Byte,
    public val poweredBit: Byte,
    public val suspendedBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        attachedBit: Byte = this.attachedBit,
        disarmedBit: Byte = this.disarmedBit,
        poweredBit: Byte = this.poweredBit,
        suspendedBit: Byte = this.suspendedBit
    ): TripWireBlockType {
        val e = TripWireBlockType(0.toShort(), attachedBit, disarmedBit, poweredBit, suspendedBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: TripWireBlockType): Boolean {
        var ret = true
        ret = ret && (this.attachedBit == other.attachedBit)
        ret = ret && (this.disarmedBit == other.disarmedBit)
        ret = ret && (this.poweredBit == other.poweredBit)
        ret = ret && (this.suspendedBit == other.suspendedBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:tripWire"

        public override val itemID: ItemID = ItemID(132)

        private lateinit var instance7382: TripWireBlockType

        private lateinit var instance7383: TripWireBlockType

        private lateinit var instance7384: TripWireBlockType

        private lateinit var instance7385: TripWireBlockType

        private lateinit var instance7386: TripWireBlockType

        private lateinit var instance7387: TripWireBlockType

        private lateinit var instance7388: TripWireBlockType

        private lateinit var instance7389: TripWireBlockType

        private lateinit var instance7390: TripWireBlockType

        private lateinit var instance7391: TripWireBlockType

        private lateinit var instance7392: TripWireBlockType

        private lateinit var instance7393: TripWireBlockType

        private lateinit var instance7394: TripWireBlockType

        private lateinit var instance7395: TripWireBlockType

        private lateinit var instance7396: TripWireBlockType

        private lateinit var instance7397: TripWireBlockType

        init {
            init0()
        }

        public override val primary: TripWireBlockType = instance7382

        public override val allInstances: List<TripWireBlockType> =
            listOf(instance7382, instance7383, instance7384, instance7385, instance7386, instance7387, instance7388, instance7389, instance7390, instance7391, instance7392, instance7393, instance7394, instance7395, instance7396, instance7397)

        public fun init0() {
            instance7382 = TripWireBlockType(
                7382.toShort(),
                0.toByte(),
                0.toByte(),
                0.toByte(),
                0.toByte()
            )
            instance7383 = TripWireBlockType(
                7383.toShort(),
                0.toByte(),
                0.toByte(),
                1.toByte(),
                0.toByte()
            )
            instance7384 = TripWireBlockType(
                7384.toShort(),
                0.toByte(),
                0.toByte(),
                0.toByte(),
                1.toByte()
            )
            instance7385 = TripWireBlockType(
                7385.toShort(),
                0.toByte(),
                0.toByte(),
                1.toByte(),
                1.toByte()
            )
            instance7386 = TripWireBlockType(
                7386.toShort(),
                1.toByte(),
                0.toByte(),
                0.toByte(),
                0.toByte()
            )
            instance7387 = TripWireBlockType(
                7387.toShort(),
                1.toByte(),
                0.toByte(),
                1.toByte(),
                0.toByte()
            )
            instance7388 = TripWireBlockType(
                7388.toShort(),
                1.toByte(),
                0.toByte(),
                0.toByte(),
                1.toByte()
            )
            instance7389 = TripWireBlockType(
                7389.toShort(),
                1.toByte(),
                0.toByte(),
                1.toByte(),
                1.toByte()
            )
            instance7390 = TripWireBlockType(
                7390.toShort(),
                0.toByte(),
                1.toByte(),
                0.toByte(),
                0.toByte()
            )
            instance7391 = TripWireBlockType(
                7391.toShort(),
                0.toByte(),
                1.toByte(),
                1.toByte(),
                0.toByte()
            )
            instance7392 = TripWireBlockType(
                7392.toShort(),
                0.toByte(),
                1.toByte(),
                0.toByte(),
                1.toByte()
            )
            instance7393 = TripWireBlockType(
                7393.toShort(),
                0.toByte(),
                1.toByte(),
                1.toByte(),
                1.toByte()
            )
            instance7394 = TripWireBlockType(
                7394.toShort(),
                1.toByte(),
                1.toByte(),
                0.toByte(),
                0.toByte()
            )
            instance7395 = TripWireBlockType(
                7395.toShort(),
                1.toByte(),
                1.toByte(),
                1.toByte(),
                0.toByte()
            )
            instance7396 = TripWireBlockType(
                7396.toShort(),
                1.toByte(),
                1.toByte(),
                0.toByte(),
                1.toByte()
            )
            instance7397 = TripWireBlockType(
                7397.toShort(),
                1.toByte(),
                1.toByte(),
                1.toByte(),
                1.toByte()
            )
        }
    }
}
