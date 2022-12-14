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
public data class SandstoneStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): SandstoneStairsBlockType {
        val e = SandstoneStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: SandstoneStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:sandstone_stairs"

        public override val itemID: ItemID = ItemID(128)

        private lateinit var instance6713: SandstoneStairsBlockType

        private lateinit var instance6714: SandstoneStairsBlockType

        private lateinit var instance6715: SandstoneStairsBlockType

        private lateinit var instance6716: SandstoneStairsBlockType

        private lateinit var instance6717: SandstoneStairsBlockType

        private lateinit var instance6718: SandstoneStairsBlockType

        private lateinit var instance6719: SandstoneStairsBlockType

        private lateinit var instance6720: SandstoneStairsBlockType

        init {
            init0()
        }

        public override val primary: SandstoneStairsBlockType = instance6713

        public override val allInstances: List<SandstoneStairsBlockType> =
            listOf(instance6713, instance6714, instance6715, instance6716, instance6717, instance6718, instance6719, instance6720)

        public fun init0() {
            instance6713 = SandstoneStairsBlockType(6713.toShort(), 0.toByte(), 0)
            instance6714 = SandstoneStairsBlockType(6714.toShort(), 0.toByte(), 1)
            instance6715 = SandstoneStairsBlockType(6715.toShort(), 0.toByte(), 2)
            instance6716 = SandstoneStairsBlockType(6716.toShort(), 0.toByte(), 3)
            instance6717 = SandstoneStairsBlockType(6717.toShort(), 1.toByte(), 0)
            instance6718 = SandstoneStairsBlockType(6718.toShort(), 1.toByte(), 1)
            instance6719 = SandstoneStairsBlockType(6719.toShort(), 1.toByte(), 2)
            instance6720 = SandstoneStairsBlockType(6720.toShort(), 1.toByte(), 3)
        }
    }
}
