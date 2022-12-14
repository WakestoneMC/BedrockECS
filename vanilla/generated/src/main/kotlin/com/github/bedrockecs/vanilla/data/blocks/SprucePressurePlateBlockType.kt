// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Int
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class SprucePressurePlateBlockType private constructor(
    public override val runtimeID: Short,
    public val redstoneSignal: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(redstoneSignal: Int = this.redstoneSignal): SprucePressurePlateBlockType {
        val e = SprucePressurePlateBlockType(0.toShort(), redstoneSignal)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: SprucePressurePlateBlockType): Boolean {
        var ret = true
        ret = ret && (this.redstoneSignal == other.redstoneSignal)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:spruce_pressure_plate"

        public override val itemID: ItemID = ItemID(-154)

        private lateinit var instance7023: SprucePressurePlateBlockType

        private lateinit var instance7024: SprucePressurePlateBlockType

        private lateinit var instance7025: SprucePressurePlateBlockType

        private lateinit var instance7026: SprucePressurePlateBlockType

        private lateinit var instance7027: SprucePressurePlateBlockType

        private lateinit var instance7028: SprucePressurePlateBlockType

        private lateinit var instance7029: SprucePressurePlateBlockType

        private lateinit var instance7030: SprucePressurePlateBlockType

        private lateinit var instance7031: SprucePressurePlateBlockType

        private lateinit var instance7032: SprucePressurePlateBlockType

        private lateinit var instance7033: SprucePressurePlateBlockType

        private lateinit var instance7034: SprucePressurePlateBlockType

        private lateinit var instance7035: SprucePressurePlateBlockType

        private lateinit var instance7036: SprucePressurePlateBlockType

        private lateinit var instance7037: SprucePressurePlateBlockType

        private lateinit var instance7038: SprucePressurePlateBlockType

        init {
            init0()
        }

        public override val primary: SprucePressurePlateBlockType = instance7023

        public override val allInstances: List<SprucePressurePlateBlockType> =
            listOf(instance7023, instance7024, instance7025, instance7026, instance7027, instance7028, instance7029, instance7030, instance7031, instance7032, instance7033, instance7034, instance7035, instance7036, instance7037, instance7038)

        public fun init0() {
            instance7023 = SprucePressurePlateBlockType(7023.toShort(), 0)
            instance7024 = SprucePressurePlateBlockType(7024.toShort(), 1)
            instance7025 = SprucePressurePlateBlockType(7025.toShort(), 2)
            instance7026 = SprucePressurePlateBlockType(7026.toShort(), 3)
            instance7027 = SprucePressurePlateBlockType(7027.toShort(), 4)
            instance7028 = SprucePressurePlateBlockType(7028.toShort(), 5)
            instance7029 = SprucePressurePlateBlockType(7029.toShort(), 6)
            instance7030 = SprucePressurePlateBlockType(7030.toShort(), 7)
            instance7031 = SprucePressurePlateBlockType(7031.toShort(), 8)
            instance7032 = SprucePressurePlateBlockType(7032.toShort(), 9)
            instance7033 = SprucePressurePlateBlockType(7033.toShort(), 10)
            instance7034 = SprucePressurePlateBlockType(7034.toShort(), 11)
            instance7035 = SprucePressurePlateBlockType(7035.toShort(), 12)
            instance7036 = SprucePressurePlateBlockType(7036.toShort(), 13)
            instance7037 = SprucePressurePlateBlockType(7037.toShort(), 14)
            instance7038 = SprucePressurePlateBlockType(7038.toShort(), 15)
        }
    }
}
