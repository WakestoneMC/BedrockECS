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
public data class LightBlueCandleBlockType private constructor(
    public override val runtimeID: Short,
    public val candles: Int,
    public val lit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(candles: Int = this.candles, lit: Byte = this.lit): LightBlueCandleBlockType {
        val e = LightBlueCandleBlockType(0.toShort(), candles, lit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: LightBlueCandleBlockType): Boolean {
        var ret = true
        ret = ret && (this.candles == other.candles)
        ret = ret && (this.lit == other.lit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:light_blue_candle"

        public override val itemID: ItemID = ItemID(-416)

        private lateinit var instance5475: LightBlueCandleBlockType

        private lateinit var instance5476: LightBlueCandleBlockType

        private lateinit var instance5477: LightBlueCandleBlockType

        private lateinit var instance5478: LightBlueCandleBlockType

        private lateinit var instance5479: LightBlueCandleBlockType

        private lateinit var instance5480: LightBlueCandleBlockType

        private lateinit var instance5481: LightBlueCandleBlockType

        private lateinit var instance5482: LightBlueCandleBlockType

        init {
            init0()
        }

        public override val primary: LightBlueCandleBlockType = instance5475

        public override val allInstances: List<LightBlueCandleBlockType> =
            listOf(instance5475, instance5476, instance5477, instance5478, instance5479, instance5480, instance5481, instance5482)

        public fun init0() {
            instance5475 = LightBlueCandleBlockType(5475.toShort(), 0, 0.toByte())
            instance5476 = LightBlueCandleBlockType(5476.toShort(), 1, 0.toByte())
            instance5477 = LightBlueCandleBlockType(5477.toShort(), 2, 0.toByte())
            instance5478 = LightBlueCandleBlockType(5478.toShort(), 3, 0.toByte())
            instance5479 = LightBlueCandleBlockType(5479.toShort(), 0, 1.toByte())
            instance5480 = LightBlueCandleBlockType(5480.toShort(), 1, 1.toByte())
            instance5481 = LightBlueCandleBlockType(5481.toShort(), 2, 1.toByte())
            instance5482 = LightBlueCandleBlockType(5482.toShort(), 3, 1.toByte())
        }
    }
}
