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
public data class BlackstoneSlabBlockType private constructor(
    public override val runtimeID: Short,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(topSlotBit: Byte = this.topSlotBit): BlackstoneSlabBlockType {
        val e = BlackstoneSlabBlockType(0.toShort(), topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BlackstoneSlabBlockType): Boolean {
        var ret = true
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:blackstone_slab"

        public override val itemID: ItemID = ItemID(-282)

        private lateinit var instance497: BlackstoneSlabBlockType

        private lateinit var instance498: BlackstoneSlabBlockType

        init {
            init0()
        }

        public override val primary: BlackstoneSlabBlockType = instance497

        public override val allInstances: List<BlackstoneSlabBlockType> =
            listOf(instance497, instance498)

        public fun init0() {
            instance497 = BlackstoneSlabBlockType(497.toShort(), 0.toByte())
            instance498 = BlackstoneSlabBlockType(498.toShort(), 1.toByte())
        }
    }
}
