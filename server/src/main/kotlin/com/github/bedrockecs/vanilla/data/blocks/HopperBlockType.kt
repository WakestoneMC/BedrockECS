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
public data class HopperBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int,
    public val toggleBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection, toggleBit: Byte = this.toggleBit):
        HopperBlockType {
        val e = HopperBlockType(0.toShort(), facingDirection, toggleBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: HopperBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        ret = ret && (this.toggleBit == other.toggleBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:hopper"

        public override val itemID: ItemID = ItemID(154)

        private lateinit var instance5115: HopperBlockType

        private lateinit var instance5116: HopperBlockType

        private lateinit var instance5117: HopperBlockType

        private lateinit var instance5118: HopperBlockType

        private lateinit var instance5119: HopperBlockType

        private lateinit var instance5120: HopperBlockType

        private lateinit var instance5121: HopperBlockType

        private lateinit var instance5122: HopperBlockType

        private lateinit var instance5123: HopperBlockType

        private lateinit var instance5124: HopperBlockType

        private lateinit var instance5125: HopperBlockType

        private lateinit var instance5126: HopperBlockType

        init {
            init0()
        }

        public override val primary: HopperBlockType = instance5115

        public override val allInstances: List<HopperBlockType> =
            listOf(instance5115, instance5116, instance5117, instance5118, instance5119, instance5120, instance5121, instance5122, instance5123, instance5124, instance5125, instance5126)

        public fun init0() {
            instance5115 = HopperBlockType(5115.toShort(), 0, 0.toByte())
            instance5116 = HopperBlockType(5116.toShort(), 1, 0.toByte())
            instance5117 = HopperBlockType(5117.toShort(), 2, 0.toByte())
            instance5118 = HopperBlockType(5118.toShort(), 3, 0.toByte())
            instance5119 = HopperBlockType(5119.toShort(), 4, 0.toByte())
            instance5120 = HopperBlockType(5120.toShort(), 5, 0.toByte())
            instance5121 = HopperBlockType(5121.toShort(), 0, 1.toByte())
            instance5122 = HopperBlockType(5122.toShort(), 1, 1.toByte())
            instance5123 = HopperBlockType(5123.toShort(), 2, 1.toByte())
            instance5124 = HopperBlockType(5124.toShort(), 3, 1.toByte())
            instance5125 = HopperBlockType(5125.toShort(), 4, 1.toByte())
            instance5126 = HopperBlockType(5126.toShort(), 5, 1.toByte())
        }
    }
}
