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
public data class YellowCandleBlockType private constructor(
    public override val runtimeID: Short,
    public val candles: Int,
    public val lit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(candles: Int = this.candles, lit: Byte = this.lit): YellowCandleBlockType {
        val e = YellowCandleBlockType(0.toShort(), candles, lit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: YellowCandleBlockType): Boolean {
        var ret = true
        ret = ret && (this.candles == other.candles)
        ret = ret && (this.lit == other.lit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:yellow_candle"

        public override val itemID: ItemID = ItemID(-417)

        private lateinit var instance7929: YellowCandleBlockType

        private lateinit var instance7930: YellowCandleBlockType

        private lateinit var instance7931: YellowCandleBlockType

        private lateinit var instance7932: YellowCandleBlockType

        private lateinit var instance7933: YellowCandleBlockType

        private lateinit var instance7934: YellowCandleBlockType

        private lateinit var instance7935: YellowCandleBlockType

        private lateinit var instance7936: YellowCandleBlockType

        init {
            init0()
        }

        public override val primary: YellowCandleBlockType = instance7929

        public override val allInstances: List<YellowCandleBlockType> =
            listOf(instance7929, instance7930, instance7931, instance7932, instance7933, instance7934, instance7935, instance7936)

        public fun init0() {
            instance7929 = YellowCandleBlockType(7929.toShort(), 0, 0.toByte())
            instance7930 = YellowCandleBlockType(7930.toShort(), 1, 0.toByte())
            instance7931 = YellowCandleBlockType(7931.toShort(), 2, 0.toByte())
            instance7932 = YellowCandleBlockType(7932.toShort(), 3, 0.toByte())
            instance7933 = YellowCandleBlockType(7933.toShort(), 0, 1.toByte())
            instance7934 = YellowCandleBlockType(7934.toShort(), 1, 1.toByte())
            instance7935 = YellowCandleBlockType(7935.toShort(), 2, 1.toByte())
            instance7936 = YellowCandleBlockType(7936.toShort(), 3, 1.toByte())
        }
    }
}
