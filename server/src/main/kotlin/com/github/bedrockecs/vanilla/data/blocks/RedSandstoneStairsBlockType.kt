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
public data class RedSandstoneStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): RedSandstoneStairsBlockType {
        val e = RedSandstoneStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: RedSandstoneStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:red_sandstone_stairs"

        public override val itemID: ItemID = ItemID(180)

        private lateinit var instance6640: RedSandstoneStairsBlockType

        private lateinit var instance6641: RedSandstoneStairsBlockType

        private lateinit var instance6642: RedSandstoneStairsBlockType

        private lateinit var instance6643: RedSandstoneStairsBlockType

        private lateinit var instance6644: RedSandstoneStairsBlockType

        private lateinit var instance6645: RedSandstoneStairsBlockType

        private lateinit var instance6646: RedSandstoneStairsBlockType

        private lateinit var instance6647: RedSandstoneStairsBlockType

        init {
            init0()
        }

        public override val primary: RedSandstoneStairsBlockType = instance6640

        public override val allInstances: List<RedSandstoneStairsBlockType> =
            listOf(instance6640, instance6641, instance6642, instance6643, instance6644, instance6645, instance6646, instance6647)

        public fun init0() {
            instance6640 = RedSandstoneStairsBlockType(6640.toShort(), 0.toByte(), 0)
            instance6641 = RedSandstoneStairsBlockType(6641.toShort(), 0.toByte(), 1)
            instance6642 = RedSandstoneStairsBlockType(6642.toShort(), 0.toByte(), 2)
            instance6643 = RedSandstoneStairsBlockType(6643.toShort(), 0.toByte(), 3)
            instance6644 = RedSandstoneStairsBlockType(6644.toShort(), 1.toByte(), 0)
            instance6645 = RedSandstoneStairsBlockType(6645.toShort(), 1.toByte(), 1)
            instance6646 = RedSandstoneStairsBlockType(6646.toShort(), 1.toByte(), 2)
            instance6647 = RedSandstoneStairsBlockType(6647.toShort(), 1.toByte(), 3)
        }
    }
}
