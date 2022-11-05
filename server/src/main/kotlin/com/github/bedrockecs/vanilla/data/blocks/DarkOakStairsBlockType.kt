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
public data class DarkOakStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): DarkOakStairsBlockType {
        val e = DarkOakStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: DarkOakStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:dark_oak_stairs"

        public override val itemID: ItemID = ItemID(164)

        private lateinit var instance4013: DarkOakStairsBlockType

        private lateinit var instance4014: DarkOakStairsBlockType

        private lateinit var instance4015: DarkOakStairsBlockType

        private lateinit var instance4016: DarkOakStairsBlockType

        private lateinit var instance4017: DarkOakStairsBlockType

        private lateinit var instance4018: DarkOakStairsBlockType

        private lateinit var instance4019: DarkOakStairsBlockType

        private lateinit var instance4020: DarkOakStairsBlockType

        init {
            init0()
        }

        public override val primary: DarkOakStairsBlockType = instance4013

        public override val allInstances: List<DarkOakStairsBlockType> =
            listOf(instance4013, instance4014, instance4015, instance4016, instance4017, instance4018, instance4019, instance4020)

        public fun init0() {
            instance4013 = DarkOakStairsBlockType(4013.toShort(), 0.toByte(), 0)
            instance4014 = DarkOakStairsBlockType(4014.toShort(), 0.toByte(), 1)
            instance4015 = DarkOakStairsBlockType(4015.toShort(), 0.toByte(), 2)
            instance4016 = DarkOakStairsBlockType(4016.toShort(), 0.toByte(), 3)
            instance4017 = DarkOakStairsBlockType(4017.toShort(), 1.toByte(), 0)
            instance4018 = DarkOakStairsBlockType(4018.toShort(), 1.toByte(), 1)
            instance4019 = DarkOakStairsBlockType(4019.toShort(), 1.toByte(), 2)
            instance4020 = DarkOakStairsBlockType(4020.toShort(), 1.toByte(), 3)
        }
    }
}