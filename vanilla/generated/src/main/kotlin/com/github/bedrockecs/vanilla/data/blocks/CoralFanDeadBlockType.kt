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
public data class CoralFanDeadBlockType private constructor(
    public override val runtimeID: Short,
    public val coralColor: String,
    public val coralFanDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        coralColor: String = this.coralColor,
        coralFanDirection: Int =
            this.coralFanDirection
    ): CoralFanDeadBlockType {
        val e = CoralFanDeadBlockType(0.toShort(), coralColor, coralFanDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CoralFanDeadBlockType): Boolean {
        var ret = true
        ret = ret && (this.coralColor == other.coralColor)
        ret = ret && (this.coralFanDirection == other.coralFanDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:coral_fan_dead"

        public override val itemID: ItemID = ItemID(-134)

        private lateinit var instance3709: CoralFanDeadBlockType

        private lateinit var instance3710: CoralFanDeadBlockType

        private lateinit var instance3711: CoralFanDeadBlockType

        private lateinit var instance3712: CoralFanDeadBlockType

        private lateinit var instance3713: CoralFanDeadBlockType

        private lateinit var instance3714: CoralFanDeadBlockType

        private lateinit var instance3715: CoralFanDeadBlockType

        private lateinit var instance3716: CoralFanDeadBlockType

        private lateinit var instance3717: CoralFanDeadBlockType

        private lateinit var instance3718: CoralFanDeadBlockType

        init {
            init0()
        }

        public override val primary: CoralFanDeadBlockType = instance3709

        public override val allInstances: List<CoralFanDeadBlockType> =
            listOf(instance3709, instance3710, instance3711, instance3712, instance3713, instance3714, instance3715, instance3716, instance3717, instance3718)

        public fun init0() {
            instance3709 = CoralFanDeadBlockType(3709.toShort(), "blue", 0)
            instance3710 = CoralFanDeadBlockType(3710.toShort(), "pink", 0)
            instance3711 = CoralFanDeadBlockType(3711.toShort(), "purple", 0)
            instance3712 = CoralFanDeadBlockType(3712.toShort(), "red", 0)
            instance3713 = CoralFanDeadBlockType(3713.toShort(), "yellow", 0)
            instance3714 = CoralFanDeadBlockType(3714.toShort(), "blue", 1)
            instance3715 = CoralFanDeadBlockType(3715.toShort(), "pink", 1)
            instance3716 = CoralFanDeadBlockType(3716.toShort(), "purple", 1)
            instance3717 = CoralFanDeadBlockType(3717.toShort(), "red", 1)
            instance3718 = CoralFanDeadBlockType(3718.toShort(), "yellow", 1)
        }
    }
}
