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
public data class PinkCandleBlockType private constructor(
    public override val runtimeID: Short,
    public val candles: Int,
    public val lit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(candles: Int = this.candles, lit: Byte = this.lit): PinkCandleBlockType {
        val e = PinkCandleBlockType(0.toShort(), candles, lit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PinkCandleBlockType): Boolean {
        var ret = true
        ret = ret && (this.candles == other.candles)
        ret = ret && (this.lit == other.lit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:pink_candle"

        public override val itemID: ItemID = ItemID(-419)

        private lateinit var instance5772: PinkCandleBlockType

        private lateinit var instance5773: PinkCandleBlockType

        private lateinit var instance5774: PinkCandleBlockType

        private lateinit var instance5775: PinkCandleBlockType

        private lateinit var instance5776: PinkCandleBlockType

        private lateinit var instance5777: PinkCandleBlockType

        private lateinit var instance5778: PinkCandleBlockType

        private lateinit var instance5779: PinkCandleBlockType

        init {
            init0()
        }

        public override val primary: PinkCandleBlockType = instance5772

        public override val allInstances: List<PinkCandleBlockType> =
            listOf(instance5772, instance5773, instance5774, instance5775, instance5776, instance5777, instance5778, instance5779)

        public fun init0() {
            instance5772 = PinkCandleBlockType(5772.toShort(), 0, 0.toByte())
            instance5773 = PinkCandleBlockType(5773.toShort(), 1, 0.toByte())
            instance5774 = PinkCandleBlockType(5774.toShort(), 2, 0.toByte())
            instance5775 = PinkCandleBlockType(5775.toShort(), 3, 0.toByte())
            instance5776 = PinkCandleBlockType(5776.toShort(), 0, 1.toByte())
            instance5777 = PinkCandleBlockType(5777.toShort(), 1, 1.toByte())
            instance5778 = PinkCandleBlockType(5778.toShort(), 2, 1.toByte())
            instance5779 = PinkCandleBlockType(5779.toShort(), 3, 1.toByte())
        }
    }
}
