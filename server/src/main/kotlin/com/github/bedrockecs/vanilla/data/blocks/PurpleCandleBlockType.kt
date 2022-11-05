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
public data class PurpleCandleBlockType private constructor(
    public override val runtimeID: Short,
    public val candles: Int,
    public val lit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(candles: Int = this.candles, lit: Byte = this.lit): PurpleCandleBlockType {
        val e = PurpleCandleBlockType(0.toShort(), candles, lit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PurpleCandleBlockType): Boolean {
        var ret = true
        ret = ret && (this.candles == other.candles)
        ret = ret && (this.lit == other.lit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:purple_candle"

        public override val itemID: ItemID = ItemID(-423)

        private lateinit var instance6512: PurpleCandleBlockType

        private lateinit var instance6513: PurpleCandleBlockType

        private lateinit var instance6514: PurpleCandleBlockType

        private lateinit var instance6515: PurpleCandleBlockType

        private lateinit var instance6516: PurpleCandleBlockType

        private lateinit var instance6517: PurpleCandleBlockType

        private lateinit var instance6518: PurpleCandleBlockType

        private lateinit var instance6519: PurpleCandleBlockType

        init {
            init0()
        }

        public override val primary: PurpleCandleBlockType = instance6512

        public override val allInstances: List<PurpleCandleBlockType> =
            listOf(instance6512, instance6513, instance6514, instance6515, instance6516, instance6517, instance6518, instance6519)

        public fun init0() {
            instance6512 = PurpleCandleBlockType(6512.toShort(), 0, 0.toByte())
            instance6513 = PurpleCandleBlockType(6513.toShort(), 1, 0.toByte())
            instance6514 = PurpleCandleBlockType(6514.toShort(), 2, 0.toByte())
            instance6515 = PurpleCandleBlockType(6515.toShort(), 3, 0.toByte())
            instance6516 = PurpleCandleBlockType(6516.toShort(), 0, 1.toByte())
            instance6517 = PurpleCandleBlockType(6517.toShort(), 1, 1.toByte())
            instance6518 = PurpleCandleBlockType(6518.toShort(), 2, 1.toByte())
            instance6519 = PurpleCandleBlockType(6519.toShort(), 3, 1.toByte())
        }
    }
}
