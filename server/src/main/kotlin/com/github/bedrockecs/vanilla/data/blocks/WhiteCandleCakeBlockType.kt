// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Byte
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class WhiteCandleCakeBlockType private constructor(
    public override val runtimeID: Short,
    public val lit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(lit: Byte = this.lit): WhiteCandleCakeBlockType {
        val e = WhiteCandleCakeBlockType(0.toShort(), lit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WhiteCandleCakeBlockType): Boolean {
        var ret = true
        ret = ret && (this.lit == other.lit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:white_candle_cake"

        public override val itemID: ItemID = ItemID(-430)

        private lateinit var instance7796: WhiteCandleCakeBlockType

        private lateinit var instance7797: WhiteCandleCakeBlockType

        init {
            init0()
        }

        public override val primary: WhiteCandleCakeBlockType = instance7796

        public override val allInstances: List<WhiteCandleCakeBlockType> =
            listOf(instance7796, instance7797)

        public fun init0() {
            instance7796 = WhiteCandleCakeBlockType(7796.toShort(), 0.toByte())
            instance7797 = WhiteCandleCakeBlockType(7797.toShort(), 1.toByte())
        }
    }
}
