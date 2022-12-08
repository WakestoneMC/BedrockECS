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
public data class ExposedCutCopperStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): ExposedCutCopperStairsBlockType {
        val e = ExposedCutCopperStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: ExposedCutCopperStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:exposed_cut_copper_stairs"

        public override val itemID: ItemID = ItemID(-355)

        private lateinit var instance4756: ExposedCutCopperStairsBlockType

        private lateinit var instance4757: ExposedCutCopperStairsBlockType

        private lateinit var instance4758: ExposedCutCopperStairsBlockType

        private lateinit var instance4759: ExposedCutCopperStairsBlockType

        private lateinit var instance4760: ExposedCutCopperStairsBlockType

        private lateinit var instance4761: ExposedCutCopperStairsBlockType

        private lateinit var instance4762: ExposedCutCopperStairsBlockType

        private lateinit var instance4763: ExposedCutCopperStairsBlockType

        init {
            init0()
        }

        public override val primary: ExposedCutCopperStairsBlockType = instance4756

        public override val allInstances: List<ExposedCutCopperStairsBlockType> =
            listOf(instance4756, instance4757, instance4758, instance4759, instance4760, instance4761, instance4762, instance4763)

        public fun init0() {
            instance4756 = ExposedCutCopperStairsBlockType(4756.toShort(), 0.toByte(), 0)
            instance4757 = ExposedCutCopperStairsBlockType(4757.toShort(), 0.toByte(), 1)
            instance4758 = ExposedCutCopperStairsBlockType(4758.toShort(), 0.toByte(), 2)
            instance4759 = ExposedCutCopperStairsBlockType(4759.toShort(), 0.toByte(), 3)
            instance4760 = ExposedCutCopperStairsBlockType(4760.toShort(), 1.toByte(), 0)
            instance4761 = ExposedCutCopperStairsBlockType(4761.toShort(), 1.toByte(), 1)
            instance4762 = ExposedCutCopperStairsBlockType(4762.toShort(), 1.toByte(), 2)
            instance4763 = ExposedCutCopperStairsBlockType(4763.toShort(), 1.toByte(), 3)
        }
    }
}