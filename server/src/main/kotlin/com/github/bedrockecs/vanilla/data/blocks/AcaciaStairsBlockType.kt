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
public data class AcaciaStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): AcaciaStairsBlockType {
        val e = AcaciaStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: AcaciaStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:acacia_stairs"

        public override val itemID: ItemID = ItemID(163)

        private lateinit var instance76: AcaciaStairsBlockType

        private lateinit var instance77: AcaciaStairsBlockType

        private lateinit var instance78: AcaciaStairsBlockType

        private lateinit var instance79: AcaciaStairsBlockType

        private lateinit var instance80: AcaciaStairsBlockType

        private lateinit var instance81: AcaciaStairsBlockType

        private lateinit var instance82: AcaciaStairsBlockType

        private lateinit var instance83: AcaciaStairsBlockType

        init {
            init0()
        }

        public override val primary: AcaciaStairsBlockType = instance76

        public override val allInstances: List<AcaciaStairsBlockType> =
            listOf(instance76, instance77, instance78, instance79, instance80, instance81, instance82, instance83)

        public fun init0() {
            instance76 = AcaciaStairsBlockType(76.toShort(), 0.toByte(), 0)
            instance77 = AcaciaStairsBlockType(77.toShort(), 0.toByte(), 1)
            instance78 = AcaciaStairsBlockType(78.toShort(), 0.toByte(), 2)
            instance79 = AcaciaStairsBlockType(79.toShort(), 0.toByte(), 3)
            instance80 = AcaciaStairsBlockType(80.toShort(), 1.toByte(), 0)
            instance81 = AcaciaStairsBlockType(81.toShort(), 1.toByte(), 1)
            instance82 = AcaciaStairsBlockType(82.toShort(), 1.toByte(), 2)
            instance83 = AcaciaStairsBlockType(83.toShort(), 1.toByte(), 3)
        }
    }
}
