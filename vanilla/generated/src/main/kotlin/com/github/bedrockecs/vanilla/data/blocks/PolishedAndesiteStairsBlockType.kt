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
public data class PolishedAndesiteStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): PolishedAndesiteStairsBlockType {
        val e = PolishedAndesiteStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PolishedAndesiteStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:polished_andesite_stairs"

        public override val itemID: ItemID = ItemID(-174)

        private lateinit var instance5817: PolishedAndesiteStairsBlockType

        private lateinit var instance5818: PolishedAndesiteStairsBlockType

        private lateinit var instance5819: PolishedAndesiteStairsBlockType

        private lateinit var instance5820: PolishedAndesiteStairsBlockType

        private lateinit var instance5821: PolishedAndesiteStairsBlockType

        private lateinit var instance5822: PolishedAndesiteStairsBlockType

        private lateinit var instance5823: PolishedAndesiteStairsBlockType

        private lateinit var instance5824: PolishedAndesiteStairsBlockType

        init {
            init0()
        }

        public override val primary: PolishedAndesiteStairsBlockType = instance5817

        public override val allInstances: List<PolishedAndesiteStairsBlockType> =
            listOf(instance5817, instance5818, instance5819, instance5820, instance5821, instance5822, instance5823, instance5824)

        public fun init0() {
            instance5817 = PolishedAndesiteStairsBlockType(5817.toShort(), 0.toByte(), 0)
            instance5818 = PolishedAndesiteStairsBlockType(5818.toShort(), 0.toByte(), 1)
            instance5819 = PolishedAndesiteStairsBlockType(5819.toShort(), 0.toByte(), 2)
            instance5820 = PolishedAndesiteStairsBlockType(5820.toShort(), 0.toByte(), 3)
            instance5821 = PolishedAndesiteStairsBlockType(5821.toShort(), 1.toByte(), 0)
            instance5822 = PolishedAndesiteStairsBlockType(5822.toShort(), 1.toByte(), 1)
            instance5823 = PolishedAndesiteStairsBlockType(5823.toShort(), 1.toByte(), 2)
            instance5824 = PolishedAndesiteStairsBlockType(5824.toShort(), 1.toByte(), 3)
        }
    }
}