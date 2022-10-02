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
public data class IronTrapdoorBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int,
    public val openBit: Byte,
    public val upsideDownBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        direction: Int = this.direction,
        openBit: Byte = this.openBit,
        upsideDownBit: Byte = this.upsideDownBit
    ): IronTrapdoorBlockType {
        val e = IronTrapdoorBlockType(0.toShort(), direction, openBit, upsideDownBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: IronTrapdoorBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.openBit == other.openBit)
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:iron_trapdoor"

        public override val itemID: ItemID = ItemID(167)

        private lateinit var instance5169: IronTrapdoorBlockType

        private lateinit var instance5170: IronTrapdoorBlockType

        private lateinit var instance5171: IronTrapdoorBlockType

        private lateinit var instance5172: IronTrapdoorBlockType

        private lateinit var instance5173: IronTrapdoorBlockType

        private lateinit var instance5174: IronTrapdoorBlockType

        private lateinit var instance5175: IronTrapdoorBlockType

        private lateinit var instance5176: IronTrapdoorBlockType

        private lateinit var instance5177: IronTrapdoorBlockType

        private lateinit var instance5178: IronTrapdoorBlockType

        private lateinit var instance5179: IronTrapdoorBlockType

        private lateinit var instance5180: IronTrapdoorBlockType

        private lateinit var instance5181: IronTrapdoorBlockType

        private lateinit var instance5182: IronTrapdoorBlockType

        private lateinit var instance5183: IronTrapdoorBlockType

        private lateinit var instance5184: IronTrapdoorBlockType

        init {
            init0()
        }

        public override val primary: IronTrapdoorBlockType = instance5169

        public override val allInstances: List<IronTrapdoorBlockType> =
            listOf(instance5169, instance5170, instance5171, instance5172, instance5173, instance5174, instance5175, instance5176, instance5177, instance5178, instance5179, instance5180, instance5181, instance5182, instance5183, instance5184)

        public fun init0() {
            instance5169 = IronTrapdoorBlockType(5169.toShort(), 0, 0.toByte(), 0.toByte())
            instance5170 = IronTrapdoorBlockType(5170.toShort(), 1, 0.toByte(), 0.toByte())
            instance5171 = IronTrapdoorBlockType(5171.toShort(), 2, 0.toByte(), 0.toByte())
            instance5172 = IronTrapdoorBlockType(5172.toShort(), 3, 0.toByte(), 0.toByte())
            instance5173 = IronTrapdoorBlockType(5173.toShort(), 0, 0.toByte(), 1.toByte())
            instance5174 = IronTrapdoorBlockType(5174.toShort(), 1, 0.toByte(), 1.toByte())
            instance5175 = IronTrapdoorBlockType(5175.toShort(), 2, 0.toByte(), 1.toByte())
            instance5176 = IronTrapdoorBlockType(5176.toShort(), 3, 0.toByte(), 1.toByte())
            instance5177 = IronTrapdoorBlockType(5177.toShort(), 0, 1.toByte(), 0.toByte())
            instance5178 = IronTrapdoorBlockType(5178.toShort(), 1, 1.toByte(), 0.toByte())
            instance5179 = IronTrapdoorBlockType(5179.toShort(), 2, 1.toByte(), 0.toByte())
            instance5180 = IronTrapdoorBlockType(5180.toShort(), 3, 1.toByte(), 0.toByte())
            instance5181 = IronTrapdoorBlockType(5181.toShort(), 0, 1.toByte(), 1.toByte())
            instance5182 = IronTrapdoorBlockType(5182.toShort(), 1, 1.toByte(), 1.toByte())
            instance5183 = IronTrapdoorBlockType(5183.toShort(), 2, 1.toByte(), 1.toByte())
            instance5184 = IronTrapdoorBlockType(5184.toShort(), 3, 1.toByte(), 1.toByte())
        }
    }
}
