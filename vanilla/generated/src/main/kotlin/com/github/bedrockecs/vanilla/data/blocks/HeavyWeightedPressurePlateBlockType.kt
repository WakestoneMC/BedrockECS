// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Int
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class HeavyWeightedPressurePlateBlockType private constructor(
    public override val runtimeID: Short,
    public val redstoneSignal: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(redstoneSignal: Int = this.redstoneSignal): HeavyWeightedPressurePlateBlockType {
        val e = HeavyWeightedPressurePlateBlockType(0.toShort(), redstoneSignal)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: HeavyWeightedPressurePlateBlockType): Boolean {
        var ret = true
        ret = ret && (this.redstoneSignal == other.redstoneSignal)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:heavy_weighted_pressure_plate"

        public override val itemID: ItemID = ItemID(148)

        private lateinit var instance5097: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5098: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5099: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5100: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5101: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5102: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5103: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5104: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5105: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5106: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5107: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5108: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5109: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5110: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5111: HeavyWeightedPressurePlateBlockType

        private lateinit var instance5112: HeavyWeightedPressurePlateBlockType

        init {
            init0()
        }

        public override val primary: HeavyWeightedPressurePlateBlockType = instance5097

        public override val allInstances: List<HeavyWeightedPressurePlateBlockType> =
            listOf(instance5097, instance5098, instance5099, instance5100, instance5101, instance5102, instance5103, instance5104, instance5105, instance5106, instance5107, instance5108, instance5109, instance5110, instance5111, instance5112)

        public fun init0() {
            instance5097 = HeavyWeightedPressurePlateBlockType(5097.toShort(), 0)
            instance5098 = HeavyWeightedPressurePlateBlockType(5098.toShort(), 1)
            instance5099 = HeavyWeightedPressurePlateBlockType(5099.toShort(), 2)
            instance5100 = HeavyWeightedPressurePlateBlockType(5100.toShort(), 3)
            instance5101 = HeavyWeightedPressurePlateBlockType(5101.toShort(), 4)
            instance5102 = HeavyWeightedPressurePlateBlockType(5102.toShort(), 5)
            instance5103 = HeavyWeightedPressurePlateBlockType(5103.toShort(), 6)
            instance5104 = HeavyWeightedPressurePlateBlockType(5104.toShort(), 7)
            instance5105 = HeavyWeightedPressurePlateBlockType(5105.toShort(), 8)
            instance5106 = HeavyWeightedPressurePlateBlockType(5106.toShort(), 9)
            instance5107 = HeavyWeightedPressurePlateBlockType(5107.toShort(), 10)
            instance5108 = HeavyWeightedPressurePlateBlockType(5108.toShort(), 11)
            instance5109 = HeavyWeightedPressurePlateBlockType(5109.toShort(), 12)
            instance5110 = HeavyWeightedPressurePlateBlockType(5110.toShort(), 13)
            instance5111 = HeavyWeightedPressurePlateBlockType(5111.toShort(), 14)
            instance5112 = HeavyWeightedPressurePlateBlockType(5112.toShort(), 15)
        }
    }
}
