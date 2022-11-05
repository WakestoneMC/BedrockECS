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
public data class RedCandleCakeBlockType private constructor(
    public override val runtimeID: Short,
    public val lit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(lit: Byte = this.lit): RedCandleCakeBlockType {
        val e = RedCandleCakeBlockType(0.toShort(), lit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: RedCandleCakeBlockType): Boolean {
        var ret = true
        ret = ret && (this.lit == other.lit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:red_candle_cake"

        public override val itemID: ItemID = ItemID(-444)

        private lateinit var instance6591: RedCandleCakeBlockType

        private lateinit var instance6592: RedCandleCakeBlockType

        init {
            init0()
        }

        public override val primary: RedCandleCakeBlockType = instance6591

        public override val allInstances: List<RedCandleCakeBlockType> =
            listOf(instance6591, instance6592)

        public fun init0() {
            instance6591 = RedCandleCakeBlockType(6591.toShort(), 0.toByte())
            instance6592 = RedCandleCakeBlockType(6592.toShort(), 1.toByte())
        }
    }
}