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
public data class CobbledDeepslateStairsBlockType private constructor(
    public override val runtimeID: Short,
    public val upsideDownBit: Byte,
    public val weirdoDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        upsideDownBit: Byte = this.upsideDownBit,
        weirdoDirection: Int =
            this.weirdoDirection
    ): CobbledDeepslateStairsBlockType {
        val e = CobbledDeepslateStairsBlockType(0.toShort(), upsideDownBit, weirdoDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CobbledDeepslateStairsBlockType): Boolean {
        var ret = true
        ret = ret && (this.upsideDownBit == other.upsideDownBit)
        ret = ret && (this.weirdoDirection == other.weirdoDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:cobbled_deepslate_stairs"

        public override val itemID: ItemID = ItemID(-381)

        private lateinit var instance1148: CobbledDeepslateStairsBlockType

        private lateinit var instance1149: CobbledDeepslateStairsBlockType

        private lateinit var instance1150: CobbledDeepslateStairsBlockType

        private lateinit var instance1151: CobbledDeepslateStairsBlockType

        private lateinit var instance1152: CobbledDeepslateStairsBlockType

        private lateinit var instance1153: CobbledDeepslateStairsBlockType

        private lateinit var instance1154: CobbledDeepslateStairsBlockType

        private lateinit var instance1155: CobbledDeepslateStairsBlockType

        init {
            init0()
        }

        public override val primary: CobbledDeepslateStairsBlockType = instance1148

        public override val allInstances: List<CobbledDeepslateStairsBlockType> =
            listOf(instance1148, instance1149, instance1150, instance1151, instance1152, instance1153, instance1154, instance1155)

        public fun init0() {
            instance1148 = CobbledDeepslateStairsBlockType(1148.toShort(), 0.toByte(), 0)
            instance1149 = CobbledDeepslateStairsBlockType(1149.toShort(), 0.toByte(), 1)
            instance1150 = CobbledDeepslateStairsBlockType(1150.toShort(), 0.toByte(), 2)
            instance1151 = CobbledDeepslateStairsBlockType(1151.toShort(), 0.toByte(), 3)
            instance1152 = CobbledDeepslateStairsBlockType(1152.toShort(), 1.toByte(), 0)
            instance1153 = CobbledDeepslateStairsBlockType(1153.toShort(), 1.toByte(), 1)
            instance1154 = CobbledDeepslateStairsBlockType(1154.toShort(), 1.toByte(), 2)
            instance1155 = CobbledDeepslateStairsBlockType(1155.toShort(), 1.toByte(), 3)
        }
    }
}
