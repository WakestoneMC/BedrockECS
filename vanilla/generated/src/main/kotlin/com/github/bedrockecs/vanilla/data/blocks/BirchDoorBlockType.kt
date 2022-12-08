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
public data class BirchDoorBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int,
    public val doorHingeBit: Byte,
    public val openBit: Byte,
    public val upperBlockBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        direction: Int = this.direction,
        doorHingeBit: Byte = this.doorHingeBit,
        openBit: Byte = this.openBit,
        upperBlockBit: Byte = this.upperBlockBit
    ): BirchDoorBlockType {
        val e = BirchDoorBlockType(0.toShort(), direction, doorHingeBit, openBit, upperBlockBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BirchDoorBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.doorHingeBit == other.doorHingeBit)
        ret = ret && (this.openBit == other.openBit)
        ret = ret && (this.upperBlockBit == other.upperBlockBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:birch_door"

        public override val itemID: ItemID = ItemID(194)

        private lateinit var instance368: BirchDoorBlockType

        private lateinit var instance369: BirchDoorBlockType

        private lateinit var instance370: BirchDoorBlockType

        private lateinit var instance371: BirchDoorBlockType

        private lateinit var instance372: BirchDoorBlockType

        private lateinit var instance373: BirchDoorBlockType

        private lateinit var instance374: BirchDoorBlockType

        private lateinit var instance375: BirchDoorBlockType

        private lateinit var instance376: BirchDoorBlockType

        private lateinit var instance377: BirchDoorBlockType

        private lateinit var instance378: BirchDoorBlockType

        private lateinit var instance379: BirchDoorBlockType

        private lateinit var instance380: BirchDoorBlockType

        private lateinit var instance381: BirchDoorBlockType

        private lateinit var instance382: BirchDoorBlockType

        private lateinit var instance383: BirchDoorBlockType

        private lateinit var instance384: BirchDoorBlockType

        private lateinit var instance385: BirchDoorBlockType

        private lateinit var instance386: BirchDoorBlockType

        private lateinit var instance387: BirchDoorBlockType

        private lateinit var instance388: BirchDoorBlockType

        private lateinit var instance389: BirchDoorBlockType

        private lateinit var instance390: BirchDoorBlockType

        private lateinit var instance391: BirchDoorBlockType

        private lateinit var instance392: BirchDoorBlockType

        private lateinit var instance393: BirchDoorBlockType

        private lateinit var instance394: BirchDoorBlockType

        private lateinit var instance395: BirchDoorBlockType

        private lateinit var instance396: BirchDoorBlockType

        private lateinit var instance397: BirchDoorBlockType

        private lateinit var instance398: BirchDoorBlockType

        private lateinit var instance399: BirchDoorBlockType

        init {
            init0()
        }

        public override val primary: BirchDoorBlockType = instance368

        public override val allInstances: List<BirchDoorBlockType> =
            listOf(instance368, instance369, instance370, instance371, instance372, instance373, instance374, instance375, instance376, instance377, instance378, instance379, instance380, instance381, instance382, instance383, instance384, instance385, instance386, instance387, instance388, instance389, instance390, instance391, instance392, instance393, instance394, instance395, instance396, instance397, instance398, instance399)

        public fun init0() {
            instance368 = BirchDoorBlockType(368.toShort(), 0, 0.toByte(), 0.toByte(), 0.toByte())
            instance369 = BirchDoorBlockType(369.toShort(), 1, 0.toByte(), 0.toByte(), 0.toByte())
            instance370 = BirchDoorBlockType(370.toShort(), 2, 0.toByte(), 0.toByte(), 0.toByte())
            instance371 = BirchDoorBlockType(371.toShort(), 3, 0.toByte(), 0.toByte(), 0.toByte())
            instance372 = BirchDoorBlockType(372.toShort(), 0, 0.toByte(), 1.toByte(), 0.toByte())
            instance373 = BirchDoorBlockType(373.toShort(), 1, 0.toByte(), 1.toByte(), 0.toByte())
            instance374 = BirchDoorBlockType(374.toShort(), 2, 0.toByte(), 1.toByte(), 0.toByte())
            instance375 = BirchDoorBlockType(375.toShort(), 3, 0.toByte(), 1.toByte(), 0.toByte())
            instance376 = BirchDoorBlockType(376.toShort(), 0, 0.toByte(), 0.toByte(), 1.toByte())
            instance377 = BirchDoorBlockType(377.toShort(), 1, 0.toByte(), 0.toByte(), 1.toByte())
            instance378 = BirchDoorBlockType(378.toShort(), 2, 0.toByte(), 0.toByte(), 1.toByte())
            instance379 = BirchDoorBlockType(379.toShort(), 3, 0.toByte(), 0.toByte(), 1.toByte())
            instance380 = BirchDoorBlockType(380.toShort(), 0, 0.toByte(), 1.toByte(), 1.toByte())
            instance381 = BirchDoorBlockType(381.toShort(), 1, 0.toByte(), 1.toByte(), 1.toByte())
            instance382 = BirchDoorBlockType(382.toShort(), 2, 0.toByte(), 1.toByte(), 1.toByte())
            instance383 = BirchDoorBlockType(383.toShort(), 3, 0.toByte(), 1.toByte(), 1.toByte())
            instance384 = BirchDoorBlockType(384.toShort(), 0, 1.toByte(), 0.toByte(), 0.toByte())
            instance385 = BirchDoorBlockType(385.toShort(), 1, 1.toByte(), 0.toByte(), 0.toByte())
            instance386 = BirchDoorBlockType(386.toShort(), 2, 1.toByte(), 0.toByte(), 0.toByte())
            instance387 = BirchDoorBlockType(387.toShort(), 3, 1.toByte(), 0.toByte(), 0.toByte())
            instance388 = BirchDoorBlockType(388.toShort(), 0, 1.toByte(), 1.toByte(), 0.toByte())
            instance389 = BirchDoorBlockType(389.toShort(), 1, 1.toByte(), 1.toByte(), 0.toByte())
            instance390 = BirchDoorBlockType(390.toShort(), 2, 1.toByte(), 1.toByte(), 0.toByte())
            instance391 = BirchDoorBlockType(391.toShort(), 3, 1.toByte(), 1.toByte(), 0.toByte())
            instance392 = BirchDoorBlockType(392.toShort(), 0, 1.toByte(), 0.toByte(), 1.toByte())
            instance393 = BirchDoorBlockType(393.toShort(), 1, 1.toByte(), 0.toByte(), 1.toByte())
            instance394 = BirchDoorBlockType(394.toShort(), 2, 1.toByte(), 0.toByte(), 1.toByte())
            instance395 = BirchDoorBlockType(395.toShort(), 3, 1.toByte(), 0.toByte(), 1.toByte())
            instance396 = BirchDoorBlockType(396.toShort(), 0, 1.toByte(), 1.toByte(), 1.toByte())
            instance397 = BirchDoorBlockType(397.toShort(), 1, 1.toByte(), 1.toByte(), 1.toByte())
            instance398 = BirchDoorBlockType(398.toShort(), 2, 1.toByte(), 1.toByte(), 1.toByte())
            instance399 = BirchDoorBlockType(399.toShort(), 3, 1.toByte(), 1.toByte(), 1.toByte())
        }
    }
}