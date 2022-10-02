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
public data class DoubleStoneSlab4BlockType private constructor(
    public override val runtimeID: Short,
    public val stoneSlabType4: String,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(stoneSlabType4: String = this.stoneSlabType4, topSlotBit: Byte = this.topSlotBit):
        DoubleStoneSlab4BlockType {
        val e = DoubleStoneSlab4BlockType(0.toShort(), stoneSlabType4, topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: DoubleStoneSlab4BlockType): Boolean {
        var ret = true
        ret = ret && (this.stoneSlabType4 == other.stoneSlabType4)
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:double_stone_slab4"

        public override val itemID: ItemID = ItemID(-166)

        private lateinit var instance4561: DoubleStoneSlab4BlockType

        private lateinit var instance4562: DoubleStoneSlab4BlockType

        private lateinit var instance4563: DoubleStoneSlab4BlockType

        private lateinit var instance4564: DoubleStoneSlab4BlockType

        private lateinit var instance4565: DoubleStoneSlab4BlockType

        private lateinit var instance4566: DoubleStoneSlab4BlockType

        private lateinit var instance4567: DoubleStoneSlab4BlockType

        private lateinit var instance4568: DoubleStoneSlab4BlockType

        private lateinit var instance4569: DoubleStoneSlab4BlockType

        private lateinit var instance4570: DoubleStoneSlab4BlockType

        init {
            init0()
        }

        public override val primary: DoubleStoneSlab4BlockType = instance4561

        public override val allInstances: List<DoubleStoneSlab4BlockType> =
            listOf(instance4561, instance4562, instance4563, instance4564, instance4565, instance4566, instance4567, instance4568, instance4569, instance4570)

        public fun init0() {
            instance4561 = DoubleStoneSlab4BlockType(4561.toShort(), "mossy_stone_brick", 0.toByte())
            instance4562 = DoubleStoneSlab4BlockType(4562.toShort(), "smooth_quartz", 0.toByte())
            instance4563 = DoubleStoneSlab4BlockType(4563.toShort(), "stone", 0.toByte())
            instance4564 = DoubleStoneSlab4BlockType(4564.toShort(), "cut_sandstone", 0.toByte())
            instance4565 = DoubleStoneSlab4BlockType(4565.toShort(), "cut_red_sandstone", 0.toByte())
            instance4566 = DoubleStoneSlab4BlockType(4566.toShort(), "mossy_stone_brick", 1.toByte())
            instance4567 = DoubleStoneSlab4BlockType(4567.toShort(), "smooth_quartz", 1.toByte())
            instance4568 = DoubleStoneSlab4BlockType(4568.toShort(), "stone", 1.toByte())
            instance4569 = DoubleStoneSlab4BlockType(4569.toShort(), "cut_sandstone", 1.toByte())
            instance4570 = DoubleStoneSlab4BlockType(4570.toShort(), "cut_red_sandstone", 1.toByte())
        }
    }
}
