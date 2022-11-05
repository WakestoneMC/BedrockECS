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
public data class JungleTrapdoorBlockType private constructor(
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
    ): JungleTrapdoorBlockType {
        val e = JungleTrapdoorBlockType(0.toShort(), direction, openBit, upsideDownBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: JungleTrapdoorBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.openBit == other.openBit)
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:jungle_trapdoor"

        public override val itemID: ItemID = ItemID(-148)

        private lateinit var instance5310: JungleTrapdoorBlockType

        private lateinit var instance5311: JungleTrapdoorBlockType

        private lateinit var instance5312: JungleTrapdoorBlockType

        private lateinit var instance5313: JungleTrapdoorBlockType

        private lateinit var instance5314: JungleTrapdoorBlockType

        private lateinit var instance5315: JungleTrapdoorBlockType

        private lateinit var instance5316: JungleTrapdoorBlockType

        private lateinit var instance5317: JungleTrapdoorBlockType

        private lateinit var instance5318: JungleTrapdoorBlockType

        private lateinit var instance5319: JungleTrapdoorBlockType

        private lateinit var instance5320: JungleTrapdoorBlockType

        private lateinit var instance5321: JungleTrapdoorBlockType

        private lateinit var instance5322: JungleTrapdoorBlockType

        private lateinit var instance5323: JungleTrapdoorBlockType

        private lateinit var instance5324: JungleTrapdoorBlockType

        private lateinit var instance5325: JungleTrapdoorBlockType

        init {
            init0()
        }

        public override val primary: JungleTrapdoorBlockType = instance5310

        public override val allInstances: List<JungleTrapdoorBlockType> =
            listOf(instance5310, instance5311, instance5312, instance5313, instance5314, instance5315, instance5316, instance5317, instance5318, instance5319, instance5320, instance5321, instance5322, instance5323, instance5324, instance5325)

        public fun init0() {
            instance5310 = JungleTrapdoorBlockType(5310.toShort(), 0, 0.toByte(), 0.toByte())
            instance5311 = JungleTrapdoorBlockType(5311.toShort(), 1, 0.toByte(), 0.toByte())
            instance5312 = JungleTrapdoorBlockType(5312.toShort(), 2, 0.toByte(), 0.toByte())
            instance5313 = JungleTrapdoorBlockType(5313.toShort(), 3, 0.toByte(), 0.toByte())
            instance5314 = JungleTrapdoorBlockType(5314.toShort(), 0, 0.toByte(), 1.toByte())
            instance5315 = JungleTrapdoorBlockType(5315.toShort(), 1, 0.toByte(), 1.toByte())
            instance5316 = JungleTrapdoorBlockType(5316.toShort(), 2, 0.toByte(), 1.toByte())
            instance5317 = JungleTrapdoorBlockType(5317.toShort(), 3, 0.toByte(), 1.toByte())
            instance5318 = JungleTrapdoorBlockType(5318.toShort(), 0, 1.toByte(), 0.toByte())
            instance5319 = JungleTrapdoorBlockType(5319.toShort(), 1, 1.toByte(), 0.toByte())
            instance5320 = JungleTrapdoorBlockType(5320.toShort(), 2, 1.toByte(), 0.toByte())
            instance5321 = JungleTrapdoorBlockType(5321.toShort(), 3, 1.toByte(), 0.toByte())
            instance5322 = JungleTrapdoorBlockType(5322.toShort(), 0, 1.toByte(), 1.toByte())
            instance5323 = JungleTrapdoorBlockType(5323.toShort(), 1, 1.toByte(), 1.toByte())
            instance5324 = JungleTrapdoorBlockType(5324.toShort(), 2, 1.toByte(), 1.toByte())
            instance5325 = JungleTrapdoorBlockType(5325.toShort(), 3, 1.toByte(), 1.toByte())
        }
    }
}