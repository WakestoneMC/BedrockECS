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
public data class UnpoweredComparatorBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int,
    public val outputLitBit: Byte,
    public val outputSubtractBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        direction: Int = this.direction,
        outputLitBit: Byte = this.outputLitBit,
        outputSubtractBit: Byte = this.outputSubtractBit
    ): UnpoweredComparatorBlockType {
        val e = UnpoweredComparatorBlockType(0.toShort(), direction, outputLitBit, outputSubtractBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: UnpoweredComparatorBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.outputLitBit == other.outputLitBit)
        ret = ret && (this.outputSubtractBit == other.outputSubtractBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:unpowered_comparator"

        public override val itemID: ItemID = ItemID(149)

        private lateinit var instance7467: UnpoweredComparatorBlockType

        private lateinit var instance7468: UnpoweredComparatorBlockType

        private lateinit var instance7469: UnpoweredComparatorBlockType

        private lateinit var instance7470: UnpoweredComparatorBlockType

        private lateinit var instance7471: UnpoweredComparatorBlockType

        private lateinit var instance7472: UnpoweredComparatorBlockType

        private lateinit var instance7473: UnpoweredComparatorBlockType

        private lateinit var instance7474: UnpoweredComparatorBlockType

        private lateinit var instance7475: UnpoweredComparatorBlockType

        private lateinit var instance7476: UnpoweredComparatorBlockType

        private lateinit var instance7477: UnpoweredComparatorBlockType

        private lateinit var instance7478: UnpoweredComparatorBlockType

        private lateinit var instance7479: UnpoweredComparatorBlockType

        private lateinit var instance7480: UnpoweredComparatorBlockType

        private lateinit var instance7481: UnpoweredComparatorBlockType

        private lateinit var instance7482: UnpoweredComparatorBlockType

        init {
            init0()
        }

        public override val primary: UnpoweredComparatorBlockType = instance7467

        public override val allInstances: List<UnpoweredComparatorBlockType> =
            listOf(instance7467, instance7468, instance7469, instance7470, instance7471, instance7472, instance7473, instance7474, instance7475, instance7476, instance7477, instance7478, instance7479, instance7480, instance7481, instance7482)

        public fun init0() {
            instance7467 = UnpoweredComparatorBlockType(7467.toShort(), 0, 0.toByte(), 0.toByte())
            instance7468 = UnpoweredComparatorBlockType(7468.toShort(), 1, 0.toByte(), 0.toByte())
            instance7469 = UnpoweredComparatorBlockType(7469.toShort(), 2, 0.toByte(), 0.toByte())
            instance7470 = UnpoweredComparatorBlockType(7470.toShort(), 3, 0.toByte(), 0.toByte())
            instance7471 = UnpoweredComparatorBlockType(7471.toShort(), 0, 0.toByte(), 1.toByte())
            instance7472 = UnpoweredComparatorBlockType(7472.toShort(), 1, 0.toByte(), 1.toByte())
            instance7473 = UnpoweredComparatorBlockType(7473.toShort(), 2, 0.toByte(), 1.toByte())
            instance7474 = UnpoweredComparatorBlockType(7474.toShort(), 3, 0.toByte(), 1.toByte())
            instance7475 = UnpoweredComparatorBlockType(7475.toShort(), 0, 1.toByte(), 0.toByte())
            instance7476 = UnpoweredComparatorBlockType(7476.toShort(), 1, 1.toByte(), 0.toByte())
            instance7477 = UnpoweredComparatorBlockType(7477.toShort(), 2, 1.toByte(), 0.toByte())
            instance7478 = UnpoweredComparatorBlockType(7478.toShort(), 3, 1.toByte(), 0.toByte())
            instance7479 = UnpoweredComparatorBlockType(7479.toShort(), 0, 1.toByte(), 1.toByte())
            instance7480 = UnpoweredComparatorBlockType(7480.toShort(), 1, 1.toByte(), 1.toByte())
            instance7481 = UnpoweredComparatorBlockType(7481.toShort(), 2, 1.toByte(), 1.toByte())
            instance7482 = UnpoweredComparatorBlockType(7482.toShort(), 3, 1.toByte(), 1.toByte())
        }
    }
}
