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
public data class PolishedDeepslateSlabBlockType private constructor(
    public override val runtimeID: Short,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(topSlotBit: Byte = this.topSlotBit): PolishedDeepslateSlabBlockType {
        val e = PolishedDeepslateSlabBlockType(0.toShort(), topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PolishedDeepslateSlabBlockType): Boolean {
        var ret = true
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:polished_deepslate_slab"

        public override val itemID: ItemID = ItemID(-384)

        private lateinit var instance6209: PolishedDeepslateSlabBlockType

        private lateinit var instance6210: PolishedDeepslateSlabBlockType

        init {
            init0()
        }

        public override val primary: PolishedDeepslateSlabBlockType = instance6209

        public override val allInstances: List<PolishedDeepslateSlabBlockType> =
            listOf(instance6209, instance6210)

        public fun init0() {
            instance6209 = PolishedDeepslateSlabBlockType(6209.toShort(), 0.toByte())
            instance6210 = PolishedDeepslateSlabBlockType(6210.toShort(), 1.toByte())
        }
    }
}
