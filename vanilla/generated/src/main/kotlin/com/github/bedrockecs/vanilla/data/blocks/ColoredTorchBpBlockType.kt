// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Byte
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class ColoredTorchBpBlockType private constructor(
    public override val runtimeID: Short,
    public val colorBit: Byte,
    public val torchFacingDirection: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        colorBit: Byte = this.colorBit,
        torchFacingDirection: String =
            this.torchFacingDirection
    ): ColoredTorchBpBlockType {
        val e = ColoredTorchBpBlockType(0.toShort(), colorBit, torchFacingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: ColoredTorchBpBlockType): Boolean {
        var ret = true
        ret = ret && (this.colorBit == other.colorBit)
        ret = ret && (this.torchFacingDirection == other.torchFacingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:colored_torch_bp"

        public override val itemID: ItemID = ItemID(204)

        private lateinit var instance3599: ColoredTorchBpBlockType

        private lateinit var instance3600: ColoredTorchBpBlockType

        private lateinit var instance3601: ColoredTorchBpBlockType

        private lateinit var instance3602: ColoredTorchBpBlockType

        private lateinit var instance3603: ColoredTorchBpBlockType

        private lateinit var instance3604: ColoredTorchBpBlockType

        private lateinit var instance3605: ColoredTorchBpBlockType

        private lateinit var instance3606: ColoredTorchBpBlockType

        private lateinit var instance3607: ColoredTorchBpBlockType

        private lateinit var instance3608: ColoredTorchBpBlockType

        private lateinit var instance3609: ColoredTorchBpBlockType

        private lateinit var instance3610: ColoredTorchBpBlockType

        init {
            init0()
        }

        public override val primary: ColoredTorchBpBlockType = instance3599

        public override val allInstances: List<ColoredTorchBpBlockType> =
            listOf(instance3599, instance3600, instance3601, instance3602, instance3603, instance3604, instance3605, instance3606, instance3607, instance3608, instance3609, instance3610)

        public fun init0() {
            instance3599 = ColoredTorchBpBlockType(3599.toShort(), 0.toByte(), "unknown")
            instance3600 = ColoredTorchBpBlockType(3600.toShort(), 0.toByte(), "west")
            instance3601 = ColoredTorchBpBlockType(3601.toShort(), 0.toByte(), "east")
            instance3602 = ColoredTorchBpBlockType(3602.toShort(), 0.toByte(), "north")
            instance3603 = ColoredTorchBpBlockType(3603.toShort(), 0.toByte(), "south")
            instance3604 = ColoredTorchBpBlockType(3604.toShort(), 0.toByte(), "top")
            instance3605 = ColoredTorchBpBlockType(3605.toShort(), 1.toByte(), "unknown")
            instance3606 = ColoredTorchBpBlockType(3606.toShort(), 1.toByte(), "west")
            instance3607 = ColoredTorchBpBlockType(3607.toShort(), 1.toByte(), "east")
            instance3608 = ColoredTorchBpBlockType(3608.toShort(), 1.toByte(), "north")
            instance3609 = ColoredTorchBpBlockType(3609.toShort(), 1.toByte(), "south")
            instance3610 = ColoredTorchBpBlockType(3610.toShort(), 1.toByte(), "top")
        }
    }
}
