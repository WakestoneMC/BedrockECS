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
public data class AcaciaTrapdoorBlockType private constructor(
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
    ): AcaciaTrapdoorBlockType {
        val e = AcaciaTrapdoorBlockType(0.toShort(), direction, openBit, upsideDownBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: AcaciaTrapdoorBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.openBit == other.openBit)
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:acacia_trapdoor"

        public override val itemID: ItemID = ItemID(-145)

        private lateinit var instance100: AcaciaTrapdoorBlockType

        private lateinit var instance101: AcaciaTrapdoorBlockType

        private lateinit var instance102: AcaciaTrapdoorBlockType

        private lateinit var instance103: AcaciaTrapdoorBlockType

        private lateinit var instance104: AcaciaTrapdoorBlockType

        private lateinit var instance105: AcaciaTrapdoorBlockType

        private lateinit var instance106: AcaciaTrapdoorBlockType

        private lateinit var instance107: AcaciaTrapdoorBlockType

        private lateinit var instance108: AcaciaTrapdoorBlockType

        private lateinit var instance109: AcaciaTrapdoorBlockType

        private lateinit var instance110: AcaciaTrapdoorBlockType

        private lateinit var instance111: AcaciaTrapdoorBlockType

        private lateinit var instance112: AcaciaTrapdoorBlockType

        private lateinit var instance113: AcaciaTrapdoorBlockType

        private lateinit var instance114: AcaciaTrapdoorBlockType

        private lateinit var instance115: AcaciaTrapdoorBlockType

        init {
            init0()
        }

        public override val primary: AcaciaTrapdoorBlockType = instance100

        public override val allInstances: List<AcaciaTrapdoorBlockType> =
            listOf(instance100, instance101, instance102, instance103, instance104, instance105, instance106, instance107, instance108, instance109, instance110, instance111, instance112, instance113, instance114, instance115)

        public fun init0() {
            instance100 = AcaciaTrapdoorBlockType(100.toShort(), 0, 0.toByte(), 0.toByte())
            instance101 = AcaciaTrapdoorBlockType(101.toShort(), 1, 0.toByte(), 0.toByte())
            instance102 = AcaciaTrapdoorBlockType(102.toShort(), 2, 0.toByte(), 0.toByte())
            instance103 = AcaciaTrapdoorBlockType(103.toShort(), 3, 0.toByte(), 0.toByte())
            instance104 = AcaciaTrapdoorBlockType(104.toShort(), 0, 0.toByte(), 1.toByte())
            instance105 = AcaciaTrapdoorBlockType(105.toShort(), 1, 0.toByte(), 1.toByte())
            instance106 = AcaciaTrapdoorBlockType(106.toShort(), 2, 0.toByte(), 1.toByte())
            instance107 = AcaciaTrapdoorBlockType(107.toShort(), 3, 0.toByte(), 1.toByte())
            instance108 = AcaciaTrapdoorBlockType(108.toShort(), 0, 1.toByte(), 0.toByte())
            instance109 = AcaciaTrapdoorBlockType(109.toShort(), 1, 1.toByte(), 0.toByte())
            instance110 = AcaciaTrapdoorBlockType(110.toShort(), 2, 1.toByte(), 0.toByte())
            instance111 = AcaciaTrapdoorBlockType(111.toShort(), 3, 1.toByte(), 0.toByte())
            instance112 = AcaciaTrapdoorBlockType(112.toShort(), 0, 1.toByte(), 1.toByte())
            instance113 = AcaciaTrapdoorBlockType(113.toShort(), 1, 1.toByte(), 1.toByte())
            instance114 = AcaciaTrapdoorBlockType(114.toShort(), 2, 1.toByte(), 1.toByte())
            instance115 = AcaciaTrapdoorBlockType(115.toShort(), 3, 1.toByte(), 1.toByte())
        }
    }
}
