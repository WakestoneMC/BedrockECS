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
public data class WarpedButtonBlockType private constructor(
    public override val runtimeID: Short,
    public val buttonPressedBit: Byte,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        buttonPressedBit: Byte = this.buttonPressedBit,
        facingDirection: Int =
            this.facingDirection
    ): WarpedButtonBlockType {
        val e = WarpedButtonBlockType(0.toShort(), buttonPressedBit, facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WarpedButtonBlockType): Boolean {
        var ret = true
        ret = ret && (this.buttonPressedBit == other.buttonPressedBit)
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:warped_button"

        public override val itemID: ItemID = ItemID(-261)

        private lateinit var instance7528: WarpedButtonBlockType

        private lateinit var instance7529: WarpedButtonBlockType

        private lateinit var instance7530: WarpedButtonBlockType

        private lateinit var instance7531: WarpedButtonBlockType

        private lateinit var instance7532: WarpedButtonBlockType

        private lateinit var instance7533: WarpedButtonBlockType

        private lateinit var instance7534: WarpedButtonBlockType

        private lateinit var instance7535: WarpedButtonBlockType

        private lateinit var instance7536: WarpedButtonBlockType

        private lateinit var instance7537: WarpedButtonBlockType

        private lateinit var instance7538: WarpedButtonBlockType

        private lateinit var instance7539: WarpedButtonBlockType

        init {
            init0()
        }

        public override val primary: WarpedButtonBlockType = instance7528

        public override val allInstances: List<WarpedButtonBlockType> =
            listOf(instance7528, instance7529, instance7530, instance7531, instance7532, instance7533, instance7534, instance7535, instance7536, instance7537, instance7538, instance7539)

        public fun init0() {
            instance7528 = WarpedButtonBlockType(7528.toShort(), 0.toByte(), 0)
            instance7529 = WarpedButtonBlockType(7529.toShort(), 0.toByte(), 1)
            instance7530 = WarpedButtonBlockType(7530.toShort(), 0.toByte(), 2)
            instance7531 = WarpedButtonBlockType(7531.toShort(), 0.toByte(), 3)
            instance7532 = WarpedButtonBlockType(7532.toShort(), 0.toByte(), 4)
            instance7533 = WarpedButtonBlockType(7533.toShort(), 0.toByte(), 5)
            instance7534 = WarpedButtonBlockType(7534.toShort(), 1.toByte(), 0)
            instance7535 = WarpedButtonBlockType(7535.toShort(), 1.toByte(), 1)
            instance7536 = WarpedButtonBlockType(7536.toShort(), 1.toByte(), 2)
            instance7537 = WarpedButtonBlockType(7537.toShort(), 1.toByte(), 3)
            instance7538 = WarpedButtonBlockType(7538.toShort(), 1.toByte(), 4)
            instance7539 = WarpedButtonBlockType(7539.toShort(), 1.toByte(), 5)
        }
    }
}
