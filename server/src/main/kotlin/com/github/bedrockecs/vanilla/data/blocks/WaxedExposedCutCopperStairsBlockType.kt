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
public data class WaxedExposedCutCopperStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): WaxedExposedCutCopperStairsBlockType {
        val e = WaxedExposedCutCopperStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WaxedExposedCutCopperStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:waxed_exposed_cut_copper_stairs"

        public override val itemID: ItemID = ItemID(-359)

        private lateinit var instance7701: WaxedExposedCutCopperStairsBlockType

        private lateinit var instance7702: WaxedExposedCutCopperStairsBlockType

        private lateinit var instance7703: WaxedExposedCutCopperStairsBlockType

        private lateinit var instance7704: WaxedExposedCutCopperStairsBlockType

        private lateinit var instance7705: WaxedExposedCutCopperStairsBlockType

        private lateinit var instance7706: WaxedExposedCutCopperStairsBlockType

        private lateinit var instance7707: WaxedExposedCutCopperStairsBlockType

        private lateinit var instance7708: WaxedExposedCutCopperStairsBlockType

        init {
            init0()
        }

        public override val primary: WaxedExposedCutCopperStairsBlockType = instance7701

        public override val allInstances: List<WaxedExposedCutCopperStairsBlockType> =
            listOf(instance7701, instance7702, instance7703, instance7704, instance7705, instance7706, instance7707, instance7708)

        public fun init0() {
            instance7701 = WaxedExposedCutCopperStairsBlockType(7701.toShort(), 0.toByte(), 0)
            instance7702 = WaxedExposedCutCopperStairsBlockType(7702.toShort(), 0.toByte(), 1)
            instance7703 = WaxedExposedCutCopperStairsBlockType(7703.toShort(), 0.toByte(), 2)
            instance7704 = WaxedExposedCutCopperStairsBlockType(7704.toShort(), 0.toByte(), 3)
            instance7705 = WaxedExposedCutCopperStairsBlockType(7705.toShort(), 1.toByte(), 0)
            instance7706 = WaxedExposedCutCopperStairsBlockType(7706.toShort(), 1.toByte(), 1)
            instance7707 = WaxedExposedCutCopperStairsBlockType(7707.toShort(), 1.toByte(), 2)
            instance7708 = WaxedExposedCutCopperStairsBlockType(7708.toShort(), 1.toByte(), 3)
        }
    }
}