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
public data class CoralBlockType private constructor(
    public override val runtimeID: Short,
    public val coralColor: String,
    public val deadBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(coralColor: String = this.coralColor, deadBit: Byte = this.deadBit):
        CoralBlockType {
        val e = CoralBlockType(0.toShort(), coralColor, deadBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CoralBlockType): Boolean {
        var ret = true
        ret = ret && (this.coralColor == other.coralColor)
        ret = ret && (this.deadBit == other.deadBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:coral"

        public override val itemID: ItemID = ItemID(-131)

        private lateinit var instance3679: CoralBlockType

        private lateinit var instance3680: CoralBlockType

        private lateinit var instance3681: CoralBlockType

        private lateinit var instance3682: CoralBlockType

        private lateinit var instance3683: CoralBlockType

        private lateinit var instance3684: CoralBlockType

        private lateinit var instance3685: CoralBlockType

        private lateinit var instance3686: CoralBlockType

        private lateinit var instance3687: CoralBlockType

        private lateinit var instance3688: CoralBlockType

        init {
            init0()
        }

        public override val primary: CoralBlockType = instance3679

        public override val allInstances: List<CoralBlockType> =
            listOf(instance3679, instance3680, instance3681, instance3682, instance3683, instance3684, instance3685, instance3686, instance3687, instance3688)

        public fun init0() {
            instance3679 = CoralBlockType(3679.toShort(), "blue", 0.toByte())
            instance3680 = CoralBlockType(3680.toShort(), "pink", 0.toByte())
            instance3681 = CoralBlockType(3681.toShort(), "purple", 0.toByte())
            instance3682 = CoralBlockType(3682.toShort(), "red", 0.toByte())
            instance3683 = CoralBlockType(3683.toShort(), "yellow", 0.toByte())
            instance3684 = CoralBlockType(3684.toShort(), "blue", 1.toByte())
            instance3685 = CoralBlockType(3685.toShort(), "pink", 1.toByte())
            instance3686 = CoralBlockType(3686.toShort(), "purple", 1.toByte())
            instance3687 = CoralBlockType(3687.toShort(), "red", 1.toByte())
            instance3688 = CoralBlockType(3688.toShort(), "yellow", 1.toByte())
        }
    }
}