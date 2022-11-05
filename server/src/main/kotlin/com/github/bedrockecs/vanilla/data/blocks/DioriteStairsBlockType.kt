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
public data class DioriteStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): DioriteStairsBlockType {
        val e = DioriteStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: DioriteStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:diorite_stairs"

        public override val itemID: ItemID = ItemID(-170)

        private lateinit var instance4476: DioriteStairsBlockType

        private lateinit var instance4477: DioriteStairsBlockType

        private lateinit var instance4478: DioriteStairsBlockType

        private lateinit var instance4479: DioriteStairsBlockType

        private lateinit var instance4480: DioriteStairsBlockType

        private lateinit var instance4481: DioriteStairsBlockType

        private lateinit var instance4482: DioriteStairsBlockType

        private lateinit var instance4483: DioriteStairsBlockType

        init {
            init0()
        }

        public override val primary: DioriteStairsBlockType = instance4476

        public override val allInstances: List<DioriteStairsBlockType> =
            listOf(instance4476, instance4477, instance4478, instance4479, instance4480, instance4481, instance4482, instance4483)

        public fun init0() {
            instance4476 = DioriteStairsBlockType(4476.toShort(), 0.toByte(), 0)
            instance4477 = DioriteStairsBlockType(4477.toShort(), 0.toByte(), 1)
            instance4478 = DioriteStairsBlockType(4478.toShort(), 0.toByte(), 2)
            instance4479 = DioriteStairsBlockType(4479.toShort(), 0.toByte(), 3)
            instance4480 = DioriteStairsBlockType(4480.toShort(), 1.toByte(), 0)
            instance4481 = DioriteStairsBlockType(4481.toShort(), 1.toByte(), 1)
            instance4482 = DioriteStairsBlockType(4482.toShort(), 1.toByte(), 2)
            instance4483 = DioriteStairsBlockType(4483.toShort(), 1.toByte(), 3)
        }
    }
}
