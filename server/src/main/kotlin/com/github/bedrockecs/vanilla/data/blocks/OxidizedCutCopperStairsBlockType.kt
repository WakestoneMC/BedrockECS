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
public data class OxidizedCutCopperStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): OxidizedCutCopperStairsBlockType {
        val e = OxidizedCutCopperStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: OxidizedCutCopperStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:oxidized_cut_copper_stairs"

        public override val itemID: ItemID = ItemID(-357)

        private lateinit var instance5760: OxidizedCutCopperStairsBlockType

        private lateinit var instance5761: OxidizedCutCopperStairsBlockType

        private lateinit var instance5762: OxidizedCutCopperStairsBlockType

        private lateinit var instance5763: OxidizedCutCopperStairsBlockType

        private lateinit var instance5764: OxidizedCutCopperStairsBlockType

        private lateinit var instance5765: OxidizedCutCopperStairsBlockType

        private lateinit var instance5766: OxidizedCutCopperStairsBlockType

        private lateinit var instance5767: OxidizedCutCopperStairsBlockType

        init {
            init0()
        }

        public override val primary: OxidizedCutCopperStairsBlockType = instance5760

        public override val allInstances: List<OxidizedCutCopperStairsBlockType> =
            listOf(instance5760, instance5761, instance5762, instance5763, instance5764, instance5765, instance5766, instance5767)

        public fun init0() {
            instance5760 = OxidizedCutCopperStairsBlockType(5760.toShort(), 0.toByte(), 0)
            instance5761 = OxidizedCutCopperStairsBlockType(5761.toShort(), 0.toByte(), 1)
            instance5762 = OxidizedCutCopperStairsBlockType(5762.toShort(), 0.toByte(), 2)
            instance5763 = OxidizedCutCopperStairsBlockType(5763.toShort(), 0.toByte(), 3)
            instance5764 = OxidizedCutCopperStairsBlockType(5764.toShort(), 1.toByte(), 0)
            instance5765 = OxidizedCutCopperStairsBlockType(5765.toShort(), 1.toByte(), 1)
            instance5766 = OxidizedCutCopperStairsBlockType(5766.toShort(), 1.toByte(), 2)
            instance5767 = OxidizedCutCopperStairsBlockType(5767.toShort(), 1.toByte(), 3)
        }
    }
}