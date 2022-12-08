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
public data class CyanCandleCakeBlockType private constructor(
    public override val runtimeID: Short,
    public val lit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(lit: Byte = this.lit): CyanCandleCakeBlockType {
        val e = CyanCandleCakeBlockType(0.toShort(), lit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CyanCandleCakeBlockType): Boolean {
        var ret = true
        ret = ret && (this.lit == other.lit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:cyan_candle_cake"

        public override val itemID: ItemID = ItemID(-439)

        private lateinit var instance3929: CyanCandleCakeBlockType

        private lateinit var instance3930: CyanCandleCakeBlockType

        init {
            init0()
        }

        public override val primary: CyanCandleCakeBlockType = instance3929

        public override val allInstances: List<CyanCandleCakeBlockType> =
            listOf(instance3929, instance3930)

        public fun init0() {
            instance3929 = CyanCandleCakeBlockType(3929.toShort(), 0.toByte())
            instance3930 = CyanCandleCakeBlockType(3930.toShort(), 1.toByte())
        }
    }
}