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
public data class AndesiteStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): AndesiteStairsBlockType {
        val e = AndesiteStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: AndesiteStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:andesite_stairs"

        public override val itemID: ItemID = ItemID(-171)

        private lateinit var instance144: AndesiteStairsBlockType

        private lateinit var instance145: AndesiteStairsBlockType

        private lateinit var instance146: AndesiteStairsBlockType

        private lateinit var instance147: AndesiteStairsBlockType

        private lateinit var instance148: AndesiteStairsBlockType

        private lateinit var instance149: AndesiteStairsBlockType

        private lateinit var instance150: AndesiteStairsBlockType

        private lateinit var instance151: AndesiteStairsBlockType

        init {
            init0()
        }

        public override val primary: AndesiteStairsBlockType = instance144

        public override val allInstances: List<AndesiteStairsBlockType> =
            listOf(instance144, instance145, instance146, instance147, instance148, instance149, instance150, instance151)

        public fun init0() {
            instance144 = AndesiteStairsBlockType(144.toShort(), 0.toByte(), 0)
            instance145 = AndesiteStairsBlockType(145.toShort(), 0.toByte(), 1)
            instance146 = AndesiteStairsBlockType(146.toShort(), 0.toByte(), 2)
            instance147 = AndesiteStairsBlockType(147.toShort(), 0.toByte(), 3)
            instance148 = AndesiteStairsBlockType(148.toShort(), 1.toByte(), 0)
            instance149 = AndesiteStairsBlockType(149.toShort(), 1.toByte(), 1)
            instance150 = AndesiteStairsBlockType(150.toShort(), 1.toByte(), 2)
            instance151 = AndesiteStairsBlockType(151.toShort(), 1.toByte(), 3)
        }
    }
}
