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
public data class QuartzStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): QuartzStairsBlockType {
        val e = QuartzStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: QuartzStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:quartz_stairs"

        public override val itemID: ItemID = ItemID(156)

        private lateinit var instance6562: QuartzStairsBlockType

        private lateinit var instance6563: QuartzStairsBlockType

        private lateinit var instance6564: QuartzStairsBlockType

        private lateinit var instance6565: QuartzStairsBlockType

        private lateinit var instance6566: QuartzStairsBlockType

        private lateinit var instance6567: QuartzStairsBlockType

        private lateinit var instance6568: QuartzStairsBlockType

        private lateinit var instance6569: QuartzStairsBlockType

        init {
            init0()
        }

        public override val primary: QuartzStairsBlockType = instance6562

        public override val allInstances: List<QuartzStairsBlockType> =
            listOf(instance6562, instance6563, instance6564, instance6565, instance6566, instance6567, instance6568, instance6569)

        public fun init0() {
            instance6562 = QuartzStairsBlockType(6562.toShort(), 0.toByte(), 0)
            instance6563 = QuartzStairsBlockType(6563.toShort(), 0.toByte(), 1)
            instance6564 = QuartzStairsBlockType(6564.toShort(), 0.toByte(), 2)
            instance6565 = QuartzStairsBlockType(6565.toShort(), 0.toByte(), 3)
            instance6566 = QuartzStairsBlockType(6566.toShort(), 1.toByte(), 0)
            instance6567 = QuartzStairsBlockType(6567.toShort(), 1.toByte(), 1)
            instance6568 = QuartzStairsBlockType(6568.toShort(), 1.toByte(), 2)
            instance6569 = QuartzStairsBlockType(6569.toShort(), 1.toByte(), 3)
        }
    }
}