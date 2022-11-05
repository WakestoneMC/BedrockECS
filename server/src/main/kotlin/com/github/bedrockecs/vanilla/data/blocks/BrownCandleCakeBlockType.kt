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
public data class BrownCandleCakeBlockType private constructor(
    public override val runtimeID: Short,
    public val lit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(lit: Byte = this.lit): BrownCandleCakeBlockType {
        val e = BrownCandleCakeBlockType(0.toShort(), lit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BrownCandleCakeBlockType): Boolean {
        var ret = true
        ret = ret && (this.lit == other.lit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:brown_candle_cake"

        public override val itemID: ItemID = ItemID(-442)

        private lateinit var instance892: BrownCandleCakeBlockType

        private lateinit var instance893: BrownCandleCakeBlockType

        init {
            init0()
        }

        public override val primary: BrownCandleCakeBlockType = instance892

        public override val allInstances: List<BrownCandleCakeBlockType> =
            listOf(instance892, instance893)

        public fun init0() {
            instance892 = BrownCandleCakeBlockType(892.toShort(), 0.toByte())
            instance893 = BrownCandleCakeBlockType(893.toShort(), 1.toByte())
        }
    }
}