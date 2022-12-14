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
public data class BlackCandleBlockType private constructor(
    public override val runtimeID: Short,
    public val candles: Int,
    public val lit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(candles: Int = this.candles, lit: Byte = this.lit): BlackCandleBlockType {
        val e = BlackCandleBlockType(0.toShort(), candles, lit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BlackCandleBlockType): Boolean {
        var ret = true
        ret = ret && (this.candles == other.candles)
        ret = ret && (this.lit == other.lit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:black_candle"

        public override val itemID: ItemID = ItemID(-428)

        private lateinit var instance478: BlackCandleBlockType

        private lateinit var instance479: BlackCandleBlockType

        private lateinit var instance480: BlackCandleBlockType

        private lateinit var instance481: BlackCandleBlockType

        private lateinit var instance482: BlackCandleBlockType

        private lateinit var instance483: BlackCandleBlockType

        private lateinit var instance484: BlackCandleBlockType

        private lateinit var instance485: BlackCandleBlockType

        init {
            init0()
        }

        public override val primary: BlackCandleBlockType = instance478

        public override val allInstances: List<BlackCandleBlockType> =
            listOf(instance478, instance479, instance480, instance481, instance482, instance483, instance484, instance485)

        public fun init0() {
            instance478 = BlackCandleBlockType(478.toShort(), 0, 0.toByte())
            instance479 = BlackCandleBlockType(479.toShort(), 1, 0.toByte())
            instance480 = BlackCandleBlockType(480.toShort(), 2, 0.toByte())
            instance481 = BlackCandleBlockType(481.toShort(), 3, 0.toByte())
            instance482 = BlackCandleBlockType(482.toShort(), 0, 1.toByte())
            instance483 = BlackCandleBlockType(483.toShort(), 1, 1.toByte())
            instance484 = BlackCandleBlockType(484.toShort(), 2, 1.toByte())
            instance485 = BlackCandleBlockType(485.toShort(), 3, 1.toByte())
        }
    }
}
