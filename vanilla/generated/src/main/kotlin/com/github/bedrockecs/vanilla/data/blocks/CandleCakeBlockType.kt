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
public data class CandleCakeBlockType private constructor(
    public override val runtimeID: Short,
    public val lit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(lit: Byte = this.lit): CandleCakeBlockType {
        val e = CandleCakeBlockType(0.toShort(), lit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CandleCakeBlockType): Boolean {
        var ret = true
        ret = ret && (this.lit == other.lit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:candle_cake"

        public override val itemID: ItemID = ItemID(-429)

        private lateinit var instance961: CandleCakeBlockType

        private lateinit var instance962: CandleCakeBlockType

        init {
            init0()
        }

        public override val primary: CandleCakeBlockType = instance961

        public override val allInstances: List<CandleCakeBlockType> = listOf(instance961, instance962)

        public fun init0() {
            instance961 = CandleCakeBlockType(961.toShort(), 0.toByte())
            instance962 = CandleCakeBlockType(962.toShort(), 1.toByte())
        }
    }
}
