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
public data class DeepslateTileStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): DeepslateTileStairsBlockType {
        val e = DeepslateTileStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: DeepslateTileStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:deepslate_tile_stairs"

        public override val itemID: ItemID = ItemID(-389)

        private lateinit var instance4290: DeepslateTileStairsBlockType

        private lateinit var instance4291: DeepslateTileStairsBlockType

        private lateinit var instance4292: DeepslateTileStairsBlockType

        private lateinit var instance4293: DeepslateTileStairsBlockType

        private lateinit var instance4294: DeepslateTileStairsBlockType

        private lateinit var instance4295: DeepslateTileStairsBlockType

        private lateinit var instance4296: DeepslateTileStairsBlockType

        private lateinit var instance4297: DeepslateTileStairsBlockType

        init {
            init0()
        }

        public override val primary: DeepslateTileStairsBlockType = instance4290

        public override val allInstances: List<DeepslateTileStairsBlockType> =
            listOf(instance4290, instance4291, instance4292, instance4293, instance4294, instance4295, instance4296, instance4297)

        public fun init0() {
            instance4290 = DeepslateTileStairsBlockType(4290.toShort(), 0.toByte(), 0)
            instance4291 = DeepslateTileStairsBlockType(4291.toShort(), 0.toByte(), 1)
            instance4292 = DeepslateTileStairsBlockType(4292.toShort(), 0.toByte(), 2)
            instance4293 = DeepslateTileStairsBlockType(4293.toShort(), 0.toByte(), 3)
            instance4294 = DeepslateTileStairsBlockType(4294.toShort(), 1.toByte(), 0)
            instance4295 = DeepslateTileStairsBlockType(4295.toShort(), 1.toByte(), 1)
            instance4296 = DeepslateTileStairsBlockType(4296.toShort(), 1.toByte(), 2)
            instance4297 = DeepslateTileStairsBlockType(4297.toShort(), 1.toByte(), 3)
        }
    }
}