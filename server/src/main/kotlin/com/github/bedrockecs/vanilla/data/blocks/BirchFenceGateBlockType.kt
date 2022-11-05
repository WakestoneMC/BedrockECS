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
public data class BirchFenceGateBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int,
    public val inWallBit: Byte,
    public val openBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        direction: Int = this.direction,
        inWallBit: Byte = this.inWallBit,
        openBit: Byte = this.openBit
    ): BirchFenceGateBlockType {
        val e = BirchFenceGateBlockType(0.toShort(), direction, inWallBit, openBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BirchFenceGateBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.inWallBit == other.inWallBit)
        ret = ret && (this.openBit == other.openBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:birch_fence_gate"

        public override val itemID: ItemID = ItemID(184)

        private lateinit var instance400: BirchFenceGateBlockType

        private lateinit var instance401: BirchFenceGateBlockType

        private lateinit var instance402: BirchFenceGateBlockType

        private lateinit var instance403: BirchFenceGateBlockType

        private lateinit var instance404: BirchFenceGateBlockType

        private lateinit var instance405: BirchFenceGateBlockType

        private lateinit var instance406: BirchFenceGateBlockType

        private lateinit var instance407: BirchFenceGateBlockType

        private lateinit var instance408: BirchFenceGateBlockType

        private lateinit var instance409: BirchFenceGateBlockType

        private lateinit var instance410: BirchFenceGateBlockType

        private lateinit var instance411: BirchFenceGateBlockType

        private lateinit var instance412: BirchFenceGateBlockType

        private lateinit var instance413: BirchFenceGateBlockType

        private lateinit var instance414: BirchFenceGateBlockType

        private lateinit var instance415: BirchFenceGateBlockType

        init {
            init0()
        }

        public override val primary: BirchFenceGateBlockType = instance400

        public override val allInstances: List<BirchFenceGateBlockType> =
            listOf(instance400, instance401, instance402, instance403, instance404, instance405, instance406, instance407, instance408, instance409, instance410, instance411, instance412, instance413, instance414, instance415)

        public fun init0() {
            instance400 = BirchFenceGateBlockType(400.toShort(), 0, 0.toByte(), 0.toByte())
            instance401 = BirchFenceGateBlockType(401.toShort(), 1, 0.toByte(), 0.toByte())
            instance402 = BirchFenceGateBlockType(402.toShort(), 2, 0.toByte(), 0.toByte())
            instance403 = BirchFenceGateBlockType(403.toShort(), 3, 0.toByte(), 0.toByte())
            instance404 = BirchFenceGateBlockType(404.toShort(), 0, 0.toByte(), 1.toByte())
            instance405 = BirchFenceGateBlockType(405.toShort(), 1, 0.toByte(), 1.toByte())
            instance406 = BirchFenceGateBlockType(406.toShort(), 2, 0.toByte(), 1.toByte())
            instance407 = BirchFenceGateBlockType(407.toShort(), 3, 0.toByte(), 1.toByte())
            instance408 = BirchFenceGateBlockType(408.toShort(), 0, 1.toByte(), 0.toByte())
            instance409 = BirchFenceGateBlockType(409.toShort(), 1, 1.toByte(), 0.toByte())
            instance410 = BirchFenceGateBlockType(410.toShort(), 2, 1.toByte(), 0.toByte())
            instance411 = BirchFenceGateBlockType(411.toShort(), 3, 1.toByte(), 0.toByte())
            instance412 = BirchFenceGateBlockType(412.toShort(), 0, 1.toByte(), 1.toByte())
            instance413 = BirchFenceGateBlockType(413.toShort(), 1, 1.toByte(), 1.toByte())
            instance414 = BirchFenceGateBlockType(414.toShort(), 2, 1.toByte(), 1.toByte())
            instance415 = BirchFenceGateBlockType(415.toShort(), 3, 1.toByte(), 1.toByte())
        }
    }
}
