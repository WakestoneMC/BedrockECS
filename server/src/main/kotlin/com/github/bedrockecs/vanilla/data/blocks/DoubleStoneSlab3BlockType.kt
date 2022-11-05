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
public data class DoubleStoneSlab3BlockType private constructor(
    public override val runtimeID: Short,
    public val stoneSlabType3: String,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(stoneSlabType3: String = this.stoneSlabType3, topSlotBit: Byte = this.topSlotBit):
        DoubleStoneSlab3BlockType {
        val e = DoubleStoneSlab3BlockType(0.toShort(), stoneSlabType3, topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: DoubleStoneSlab3BlockType): Boolean {
        var ret = true
        ret = ret && (this.stoneSlabType3 == other.stoneSlabType3)
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:double_stone_slab3"

        public override val itemID: ItemID = ItemID(-162)

        private lateinit var instance4545: DoubleStoneSlab3BlockType

        private lateinit var instance4546: DoubleStoneSlab3BlockType

        private lateinit var instance4547: DoubleStoneSlab3BlockType

        private lateinit var instance4548: DoubleStoneSlab3BlockType

        private lateinit var instance4549: DoubleStoneSlab3BlockType

        private lateinit var instance4550: DoubleStoneSlab3BlockType

        private lateinit var instance4551: DoubleStoneSlab3BlockType

        private lateinit var instance4552: DoubleStoneSlab3BlockType

        private lateinit var instance4553: DoubleStoneSlab3BlockType

        private lateinit var instance4554: DoubleStoneSlab3BlockType

        private lateinit var instance4555: DoubleStoneSlab3BlockType

        private lateinit var instance4556: DoubleStoneSlab3BlockType

        private lateinit var instance4557: DoubleStoneSlab3BlockType

        private lateinit var instance4558: DoubleStoneSlab3BlockType

        private lateinit var instance4559: DoubleStoneSlab3BlockType

        private lateinit var instance4560: DoubleStoneSlab3BlockType

        init {
            init0()
        }

        public override val primary: DoubleStoneSlab3BlockType = instance4545

        public override val allInstances: List<DoubleStoneSlab3BlockType> =
            listOf(instance4545, instance4546, instance4547, instance4548, instance4549, instance4550, instance4551, instance4552, instance4553, instance4554, instance4555, instance4556, instance4557, instance4558, instance4559, instance4560)

        public fun init0() {
            instance4545 = DoubleStoneSlab3BlockType(4545.toShort(), "end_stone_brick", 0.toByte())
            instance4546 = DoubleStoneSlab3BlockType(4546.toShort(), "smooth_red_sandstone", 0.toByte())
            instance4547 = DoubleStoneSlab3BlockType(4547.toShort(), "polished_andesite", 0.toByte())
            instance4548 = DoubleStoneSlab3BlockType(4548.toShort(), "andesite", 0.toByte())
            instance4549 = DoubleStoneSlab3BlockType(4549.toShort(), "diorite", 0.toByte())
            instance4550 = DoubleStoneSlab3BlockType(4550.toShort(), "polished_diorite", 0.toByte())
            instance4551 = DoubleStoneSlab3BlockType(4551.toShort(), "granite", 0.toByte())
            instance4552 = DoubleStoneSlab3BlockType(4552.toShort(), "polished_granite", 0.toByte())
            instance4553 = DoubleStoneSlab3BlockType(4553.toShort(), "end_stone_brick", 1.toByte())
            instance4554 = DoubleStoneSlab3BlockType(4554.toShort(), "smooth_red_sandstone", 1.toByte())
            instance4555 = DoubleStoneSlab3BlockType(4555.toShort(), "polished_andesite", 1.toByte())
            instance4556 = DoubleStoneSlab3BlockType(4556.toShort(), "andesite", 1.toByte())
            instance4557 = DoubleStoneSlab3BlockType(4557.toShort(), "diorite", 1.toByte())
            instance4558 = DoubleStoneSlab3BlockType(4558.toShort(), "polished_diorite", 1.toByte())
            instance4559 = DoubleStoneSlab3BlockType(4559.toShort(), "granite", 1.toByte())
            instance4560 = DoubleStoneSlab3BlockType(4560.toShort(), "polished_granite", 1.toByte())
        }
    }
}
