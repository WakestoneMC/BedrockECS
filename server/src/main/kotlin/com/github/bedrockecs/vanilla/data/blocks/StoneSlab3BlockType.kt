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
public data class StoneSlab3BlockType private constructor(
    public override val runtimeID: Short,
    public val stoneSlabType3: String,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(stoneSlabType3: String = this.stoneSlabType3, topSlotBit: Byte = this.topSlotBit):
        StoneSlab3BlockType {
        val e = StoneSlab3BlockType(0.toShort(), stoneSlabType3, topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: StoneSlab3BlockType): Boolean {
        var ret = true
        ret = ret && (this.stoneSlabType3 == other.stoneSlabType3)
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:stone_slab3"

        public override val itemID: ItemID = ItemID(0)

        private lateinit var instance7252: StoneSlab3BlockType

        private lateinit var instance7253: StoneSlab3BlockType

        private lateinit var instance7254: StoneSlab3BlockType

        private lateinit var instance7255: StoneSlab3BlockType

        private lateinit var instance7256: StoneSlab3BlockType

        private lateinit var instance7257: StoneSlab3BlockType

        private lateinit var instance7258: StoneSlab3BlockType

        private lateinit var instance7259: StoneSlab3BlockType

        private lateinit var instance7260: StoneSlab3BlockType

        private lateinit var instance7261: StoneSlab3BlockType

        private lateinit var instance7262: StoneSlab3BlockType

        private lateinit var instance7263: StoneSlab3BlockType

        private lateinit var instance7264: StoneSlab3BlockType

        private lateinit var instance7265: StoneSlab3BlockType

        private lateinit var instance7266: StoneSlab3BlockType

        private lateinit var instance7267: StoneSlab3BlockType

        init {
            init0()
        }

        public override val primary: StoneSlab3BlockType = instance7252

        public override val allInstances: List<StoneSlab3BlockType> =
            listOf(instance7252, instance7253, instance7254, instance7255, instance7256, instance7257, instance7258, instance7259, instance7260, instance7261, instance7262, instance7263, instance7264, instance7265, instance7266, instance7267)

        public fun init0() {
            instance7252 = StoneSlab3BlockType(7252.toShort(), "end_stone_brick", 0.toByte())
            instance7253 = StoneSlab3BlockType(7253.toShort(), "smooth_red_sandstone", 0.toByte())
            instance7254 = StoneSlab3BlockType(7254.toShort(), "polished_andesite", 0.toByte())
            instance7255 = StoneSlab3BlockType(7255.toShort(), "andesite", 0.toByte())
            instance7256 = StoneSlab3BlockType(7256.toShort(), "diorite", 0.toByte())
            instance7257 = StoneSlab3BlockType(7257.toShort(), "polished_diorite", 0.toByte())
            instance7258 = StoneSlab3BlockType(7258.toShort(), "granite", 0.toByte())
            instance7259 = StoneSlab3BlockType(7259.toShort(), "polished_granite", 0.toByte())
            instance7260 = StoneSlab3BlockType(7260.toShort(), "end_stone_brick", 1.toByte())
            instance7261 = StoneSlab3BlockType(7261.toShort(), "smooth_red_sandstone", 1.toByte())
            instance7262 = StoneSlab3BlockType(7262.toShort(), "polished_andesite", 1.toByte())
            instance7263 = StoneSlab3BlockType(7263.toShort(), "andesite", 1.toByte())
            instance7264 = StoneSlab3BlockType(7264.toShort(), "diorite", 1.toByte())
            instance7265 = StoneSlab3BlockType(7265.toShort(), "polished_diorite", 1.toByte())
            instance7266 = StoneSlab3BlockType(7266.toShort(), "granite", 1.toByte())
            instance7267 = StoneSlab3BlockType(7267.toShort(), "polished_granite", 1.toByte())
        }
    }
}
