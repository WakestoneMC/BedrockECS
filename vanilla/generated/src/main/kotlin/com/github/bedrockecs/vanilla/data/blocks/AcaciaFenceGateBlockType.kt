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
public data class AcaciaFenceGateBlockType private constructor(
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
    ): AcaciaFenceGateBlockType {
        val e = AcaciaFenceGateBlockType(0.toShort(), direction, inWallBit, openBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: AcaciaFenceGateBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.inWallBit == other.inWallBit)
        ret = ret && (this.openBit == other.openBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:acacia_fence_gate"

        public override val itemID: ItemID = ItemID(187)

        private lateinit var instance44: AcaciaFenceGateBlockType

        private lateinit var instance45: AcaciaFenceGateBlockType

        private lateinit var instance46: AcaciaFenceGateBlockType

        private lateinit var instance47: AcaciaFenceGateBlockType

        private lateinit var instance48: AcaciaFenceGateBlockType

        private lateinit var instance49: AcaciaFenceGateBlockType

        private lateinit var instance50: AcaciaFenceGateBlockType

        private lateinit var instance51: AcaciaFenceGateBlockType

        private lateinit var instance52: AcaciaFenceGateBlockType

        private lateinit var instance53: AcaciaFenceGateBlockType

        private lateinit var instance54: AcaciaFenceGateBlockType

        private lateinit var instance55: AcaciaFenceGateBlockType

        private lateinit var instance56: AcaciaFenceGateBlockType

        private lateinit var instance57: AcaciaFenceGateBlockType

        private lateinit var instance58: AcaciaFenceGateBlockType

        private lateinit var instance59: AcaciaFenceGateBlockType

        init {
            init0()
        }

        public override val primary: AcaciaFenceGateBlockType = instance44

        public override val allInstances: List<AcaciaFenceGateBlockType> =
            listOf(instance44, instance45, instance46, instance47, instance48, instance49, instance50, instance51, instance52, instance53, instance54, instance55, instance56, instance57, instance58, instance59)

        public fun init0() {
            instance44 = AcaciaFenceGateBlockType(44.toShort(), 0, 0.toByte(), 0.toByte())
            instance45 = AcaciaFenceGateBlockType(45.toShort(), 1, 0.toByte(), 0.toByte())
            instance46 = AcaciaFenceGateBlockType(46.toShort(), 2, 0.toByte(), 0.toByte())
            instance47 = AcaciaFenceGateBlockType(47.toShort(), 3, 0.toByte(), 0.toByte())
            instance48 = AcaciaFenceGateBlockType(48.toShort(), 0, 0.toByte(), 1.toByte())
            instance49 = AcaciaFenceGateBlockType(49.toShort(), 1, 0.toByte(), 1.toByte())
            instance50 = AcaciaFenceGateBlockType(50.toShort(), 2, 0.toByte(), 1.toByte())
            instance51 = AcaciaFenceGateBlockType(51.toShort(), 3, 0.toByte(), 1.toByte())
            instance52 = AcaciaFenceGateBlockType(52.toShort(), 0, 1.toByte(), 0.toByte())
            instance53 = AcaciaFenceGateBlockType(53.toShort(), 1, 1.toByte(), 0.toByte())
            instance54 = AcaciaFenceGateBlockType(54.toShort(), 2, 1.toByte(), 0.toByte())
            instance55 = AcaciaFenceGateBlockType(55.toShort(), 3, 1.toByte(), 0.toByte())
            instance56 = AcaciaFenceGateBlockType(56.toShort(), 0, 1.toByte(), 1.toByte())
            instance57 = AcaciaFenceGateBlockType(57.toShort(), 1, 1.toByte(), 1.toByte())
            instance58 = AcaciaFenceGateBlockType(58.toShort(), 2, 1.toByte(), 1.toByte())
            instance59 = AcaciaFenceGateBlockType(59.toShort(), 3, 1.toByte(), 1.toByte())
        }
    }
}