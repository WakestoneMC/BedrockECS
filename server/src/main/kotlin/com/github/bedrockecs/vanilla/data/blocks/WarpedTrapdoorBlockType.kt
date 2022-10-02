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
public data class WarpedTrapdoorBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int,
    public val openBit: Byte,
    public val upsideDownBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        direction: Int = this.direction,
        openBit: Byte = this.openBit,
        upsideDownBit: Byte = this.upsideDownBit
    ): WarpedTrapdoorBlockType {
        val e = WarpedTrapdoorBlockType(0.toShort(), direction, openBit, upsideDownBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WarpedTrapdoorBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.openBit == other.openBit)
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:warped_trapdoor"

        public override val itemID: ItemID = ItemID(-247)

        private lateinit var instance7643: WarpedTrapdoorBlockType

        private lateinit var instance7644: WarpedTrapdoorBlockType

        private lateinit var instance7645: WarpedTrapdoorBlockType

        private lateinit var instance7646: WarpedTrapdoorBlockType

        private lateinit var instance7647: WarpedTrapdoorBlockType

        private lateinit var instance7648: WarpedTrapdoorBlockType

        private lateinit var instance7649: WarpedTrapdoorBlockType

        private lateinit var instance7650: WarpedTrapdoorBlockType

        private lateinit var instance7651: WarpedTrapdoorBlockType

        private lateinit var instance7652: WarpedTrapdoorBlockType

        private lateinit var instance7653: WarpedTrapdoorBlockType

        private lateinit var instance7654: WarpedTrapdoorBlockType

        private lateinit var instance7655: WarpedTrapdoorBlockType

        private lateinit var instance7656: WarpedTrapdoorBlockType

        private lateinit var instance7657: WarpedTrapdoorBlockType

        private lateinit var instance7658: WarpedTrapdoorBlockType

        init {
            init0()
        }

        public override val primary: WarpedTrapdoorBlockType = instance7643

        public override val allInstances: List<WarpedTrapdoorBlockType> =
            listOf(instance7643, instance7644, instance7645, instance7646, instance7647, instance7648, instance7649, instance7650, instance7651, instance7652, instance7653, instance7654, instance7655, instance7656, instance7657, instance7658)

        public fun init0() {
            instance7643 = WarpedTrapdoorBlockType(7643.toShort(), 0, 0.toByte(), 0.toByte())
            instance7644 = WarpedTrapdoorBlockType(7644.toShort(), 1, 0.toByte(), 0.toByte())
            instance7645 = WarpedTrapdoorBlockType(7645.toShort(), 2, 0.toByte(), 0.toByte())
            instance7646 = WarpedTrapdoorBlockType(7646.toShort(), 3, 0.toByte(), 0.toByte())
            instance7647 = WarpedTrapdoorBlockType(7647.toShort(), 0, 0.toByte(), 1.toByte())
            instance7648 = WarpedTrapdoorBlockType(7648.toShort(), 1, 0.toByte(), 1.toByte())
            instance7649 = WarpedTrapdoorBlockType(7649.toShort(), 2, 0.toByte(), 1.toByte())
            instance7650 = WarpedTrapdoorBlockType(7650.toShort(), 3, 0.toByte(), 1.toByte())
            instance7651 = WarpedTrapdoorBlockType(7651.toShort(), 0, 1.toByte(), 0.toByte())
            instance7652 = WarpedTrapdoorBlockType(7652.toShort(), 1, 1.toByte(), 0.toByte())
            instance7653 = WarpedTrapdoorBlockType(7653.toShort(), 2, 1.toByte(), 0.toByte())
            instance7654 = WarpedTrapdoorBlockType(7654.toShort(), 3, 1.toByte(), 0.toByte())
            instance7655 = WarpedTrapdoorBlockType(7655.toShort(), 0, 1.toByte(), 1.toByte())
            instance7656 = WarpedTrapdoorBlockType(7656.toShort(), 1, 1.toByte(), 1.toByte())
            instance7657 = WarpedTrapdoorBlockType(7657.toShort(), 2, 1.toByte(), 1.toByte())
            instance7658 = WarpedTrapdoorBlockType(7658.toShort(), 3, 1.toByte(), 1.toByte())
        }
    }
}
