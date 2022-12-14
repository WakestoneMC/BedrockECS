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
public data class WarpedSlabBlockType private constructor(
    public override val runtimeID: Short,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(topSlotBit: Byte = this.topSlotBit): WarpedSlabBlockType {
        val e = WarpedSlabBlockType(0.toShort(), topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WarpedSlabBlockType): Boolean {
        var ret = true
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:warped_slab"

        public override val itemID: ItemID = ItemID(-265)

        private lateinit var instance7614: WarpedSlabBlockType

        private lateinit var instance7615: WarpedSlabBlockType

        init {
            init0()
        }

        public override val primary: WarpedSlabBlockType = instance7614

        public override val allInstances: List<WarpedSlabBlockType> = listOf(instance7614, instance7615)

        public fun init0() {
            instance7614 = WarpedSlabBlockType(7614.toShort(), 0.toByte())
            instance7615 = WarpedSlabBlockType(7615.toShort(), 1.toByte())
        }
    }
}
