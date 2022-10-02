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
public data class PolishedBlackstoneSlabBlockType private constructor(
    public override val runtimeID: Short,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(topSlotBit: Byte = this.topSlotBit): PolishedBlackstoneSlabBlockType {
        val e = PolishedBlackstoneSlabBlockType(0.toShort(), topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PolishedBlackstoneSlabBlockType): Boolean {
        var ret = true
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:polished_blackstone_slab"

        public override val itemID: ItemID = ItemID(-293)

        private lateinit var instance6034: PolishedBlackstoneSlabBlockType

        private lateinit var instance6035: PolishedBlackstoneSlabBlockType

        init {
            init0()
        }

        public override val primary: PolishedBlackstoneSlabBlockType = instance6034

        public override val allInstances: List<PolishedBlackstoneSlabBlockType> =
            listOf(instance6034, instance6035)

        public fun init0() {
            instance6034 = PolishedBlackstoneSlabBlockType(6034.toShort(), 0.toByte())
            instance6035 = PolishedBlackstoneSlabBlockType(6035.toShort(), 1.toByte())
        }
    }
}
