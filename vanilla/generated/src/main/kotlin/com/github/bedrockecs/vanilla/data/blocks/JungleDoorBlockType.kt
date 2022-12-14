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
public data class JungleDoorBlockType private constructor(
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
    ): JungleDoorBlockType {
        val e = JungleDoorBlockType(0.toShort(), direction, doorHingeBit, openBit, upperBlockBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: JungleDoorBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.doorHingeBit == other.doorHingeBit)
        ret = ret && (this.openBit == other.openBit)
        ret = ret && (this.upperBlockBit == other.upperBlockBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:jungle_door"

        public override val itemID: ItemID = ItemID(195)

        private lateinit var instance5222: JungleDoorBlockType

        private lateinit var instance5223: JungleDoorBlockType

        private lateinit var instance5224: JungleDoorBlockType

        private lateinit var instance5225: JungleDoorBlockType

        private lateinit var instance5226: JungleDoorBlockType

        private lateinit var instance5227: JungleDoorBlockType

        private lateinit var instance5228: JungleDoorBlockType

        private lateinit var instance5229: JungleDoorBlockType

        private lateinit var instance5230: JungleDoorBlockType

        private lateinit var instance5231: JungleDoorBlockType

        private lateinit var instance5232: JungleDoorBlockType

        private lateinit var instance5233: JungleDoorBlockType

        private lateinit var instance5234: JungleDoorBlockType

        private lateinit var instance5235: JungleDoorBlockType

        private lateinit var instance5236: JungleDoorBlockType

        private lateinit var instance5237: JungleDoorBlockType

        private lateinit var instance5238: JungleDoorBlockType

        private lateinit var instance5239: JungleDoorBlockType

        private lateinit var instance5240: JungleDoorBlockType

        private lateinit var instance5241: JungleDoorBlockType

        private lateinit var instance5242: JungleDoorBlockType

        private lateinit var instance5243: JungleDoorBlockType

        private lateinit var instance5244: JungleDoorBlockType

        private lateinit var instance5245: JungleDoorBlockType

        private lateinit var instance5246: JungleDoorBlockType

        private lateinit var instance5247: JungleDoorBlockType

        private lateinit var instance5248: JungleDoorBlockType

        private lateinit var instance5249: JungleDoorBlockType

        private lateinit var instance5250: JungleDoorBlockType

        private lateinit var instance5251: JungleDoorBlockType

        private lateinit var instance5252: JungleDoorBlockType

        private lateinit var instance5253: JungleDoorBlockType

        init {
            init0()
        }

        public override val primary: JungleDoorBlockType = instance5222

        public override val allInstances: List<JungleDoorBlockType> =
            listOf(instance5222, instance5223, instance5224, instance5225, instance5226, instance5227, instance5228, instance5229, instance5230, instance5231, instance5232, instance5233, instance5234, instance5235, instance5236, instance5237, instance5238, instance5239, instance5240, instance5241, instance5242, instance5243, instance5244, instance5245, instance5246, instance5247, instance5248, instance5249, instance5250, instance5251, instance5252, instance5253)

        public fun init0() {
            instance5222 = JungleDoorBlockType(5222.toShort(), 0, 0.toByte(), 0.toByte(), 0.toByte())
            instance5223 = JungleDoorBlockType(5223.toShort(), 1, 0.toByte(), 0.toByte(), 0.toByte())
            instance5224 = JungleDoorBlockType(5224.toShort(), 2, 0.toByte(), 0.toByte(), 0.toByte())
            instance5225 = JungleDoorBlockType(5225.toShort(), 3, 0.toByte(), 0.toByte(), 0.toByte())
            instance5226 = JungleDoorBlockType(5226.toShort(), 0, 0.toByte(), 1.toByte(), 0.toByte())
            instance5227 = JungleDoorBlockType(5227.toShort(), 1, 0.toByte(), 1.toByte(), 0.toByte())
            instance5228 = JungleDoorBlockType(5228.toShort(), 2, 0.toByte(), 1.toByte(), 0.toByte())
            instance5229 = JungleDoorBlockType(5229.toShort(), 3, 0.toByte(), 1.toByte(), 0.toByte())
            instance5230 = JungleDoorBlockType(5230.toShort(), 0, 0.toByte(), 0.toByte(), 1.toByte())
            instance5231 = JungleDoorBlockType(5231.toShort(), 1, 0.toByte(), 0.toByte(), 1.toByte())
            instance5232 = JungleDoorBlockType(5232.toShort(), 2, 0.toByte(), 0.toByte(), 1.toByte())
            instance5233 = JungleDoorBlockType(5233.toShort(), 3, 0.toByte(), 0.toByte(), 1.toByte())
            instance5234 = JungleDoorBlockType(5234.toShort(), 0, 0.toByte(), 1.toByte(), 1.toByte())
            instance5235 = JungleDoorBlockType(5235.toShort(), 1, 0.toByte(), 1.toByte(), 1.toByte())
            instance5236 = JungleDoorBlockType(5236.toShort(), 2, 0.toByte(), 1.toByte(), 1.toByte())
            instance5237 = JungleDoorBlockType(5237.toShort(), 3, 0.toByte(), 1.toByte(), 1.toByte())
            instance5238 = JungleDoorBlockType(5238.toShort(), 0, 1.toByte(), 0.toByte(), 0.toByte())
            instance5239 = JungleDoorBlockType(5239.toShort(), 1, 1.toByte(), 0.toByte(), 0.toByte())
            instance5240 = JungleDoorBlockType(5240.toShort(), 2, 1.toByte(), 0.toByte(), 0.toByte())
            instance5241 = JungleDoorBlockType(5241.toShort(), 3, 1.toByte(), 0.toByte(), 0.toByte())
            instance5242 = JungleDoorBlockType(5242.toShort(), 0, 1.toByte(), 1.toByte(), 0.toByte())
            instance5243 = JungleDoorBlockType(5243.toShort(), 1, 1.toByte(), 1.toByte(), 0.toByte())
            instance5244 = JungleDoorBlockType(5244.toShort(), 2, 1.toByte(), 1.toByte(), 0.toByte())
            instance5245 = JungleDoorBlockType(5245.toShort(), 3, 1.toByte(), 1.toByte(), 0.toByte())
            instance5246 = JungleDoorBlockType(5246.toShort(), 0, 1.toByte(), 0.toByte(), 1.toByte())
            instance5247 = JungleDoorBlockType(5247.toShort(), 1, 1.toByte(), 0.toByte(), 1.toByte())
            instance5248 = JungleDoorBlockType(5248.toShort(), 2, 1.toByte(), 0.toByte(), 1.toByte())
            instance5249 = JungleDoorBlockType(5249.toShort(), 3, 1.toByte(), 0.toByte(), 1.toByte())
            instance5250 = JungleDoorBlockType(5250.toShort(), 0, 1.toByte(), 1.toByte(), 1.toByte())
            instance5251 = JungleDoorBlockType(5251.toShort(), 1, 1.toByte(), 1.toByte(), 1.toByte())
            instance5252 = JungleDoorBlockType(5252.toShort(), 2, 1.toByte(), 1.toByte(), 1.toByte())
            instance5253 = JungleDoorBlockType(5253.toShort(), 3, 1.toByte(), 1.toByte(), 1.toByte())
        }
    }
}
