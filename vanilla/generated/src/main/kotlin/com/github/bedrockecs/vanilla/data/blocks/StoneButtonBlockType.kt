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
public data class StoneButtonBlockType private constructor(
    public override val runtimeID: Short,
    public val buttonPressedBit: Byte,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        buttonPressedBit: Byte = this.buttonPressedBit,
        facingDirection: Int =
            this.facingDirection
    ): StoneButtonBlockType {
        val e = StoneButtonBlockType(0.toShort(), buttonPressedBit, facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: StoneButtonBlockType): Boolean {
        var ret = true
        ret = ret && (this.buttonPressedBit == other.buttonPressedBit)
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:stone_button"

        public override val itemID: ItemID = ItemID(77)

        private lateinit var instance7192: StoneButtonBlockType

        private lateinit var instance7193: StoneButtonBlockType

        private lateinit var instance7194: StoneButtonBlockType

        private lateinit var instance7195: StoneButtonBlockType

        private lateinit var instance7196: StoneButtonBlockType

        private lateinit var instance7197: StoneButtonBlockType

        private lateinit var instance7198: StoneButtonBlockType

        private lateinit var instance7199: StoneButtonBlockType

        private lateinit var instance7200: StoneButtonBlockType

        private lateinit var instance7201: StoneButtonBlockType

        private lateinit var instance7202: StoneButtonBlockType

        private lateinit var instance7203: StoneButtonBlockType

        init {
            init0()
        }

        public override val primary: StoneButtonBlockType = instance7192

        public override val allInstances: List<StoneButtonBlockType> =
            listOf(instance7192, instance7193, instance7194, instance7195, instance7196, instance7197, instance7198, instance7199, instance7200, instance7201, instance7202, instance7203)

        public fun init0() {
            instance7192 = StoneButtonBlockType(7192.toShort(), 0.toByte(), 0)
            instance7193 = StoneButtonBlockType(7193.toShort(), 0.toByte(), 1)
            instance7194 = StoneButtonBlockType(7194.toShort(), 0.toByte(), 2)
            instance7195 = StoneButtonBlockType(7195.toShort(), 0.toByte(), 3)
            instance7196 = StoneButtonBlockType(7196.toShort(), 0.toByte(), 4)
            instance7197 = StoneButtonBlockType(7197.toShort(), 0.toByte(), 5)
            instance7198 = StoneButtonBlockType(7198.toShort(), 1.toByte(), 0)
            instance7199 = StoneButtonBlockType(7199.toShort(), 1.toByte(), 1)
            instance7200 = StoneButtonBlockType(7200.toShort(), 1.toByte(), 2)
            instance7201 = StoneButtonBlockType(7201.toShort(), 1.toByte(), 3)
            instance7202 = StoneButtonBlockType(7202.toShort(), 1.toByte(), 4)
            instance7203 = StoneButtonBlockType(7203.toShort(), 1.toByte(), 5)
        }
    }
}