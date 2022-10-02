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
public data class DoublePlantBlockType private constructor(
    public override val runtimeID: Short,
    public val doublePlantType: String,
    public val upperBlockBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        doublePlantType: String = this.doublePlantType,
        upperBlockBit: Byte =
            this.upperBlockBit
    ): DoublePlantBlockType {
        val e = DoublePlantBlockType(0.toShort(), doublePlantType, upperBlockBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: DoublePlantBlockType): Boolean {
        var ret = true
        ret = ret && (this.doublePlantType == other.doublePlantType)
        ret = ret && (this.upperBlockBit == other.upperBlockBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:double_plant"

        public override val itemID: ItemID = ItemID(175)

        private lateinit var instance4501: DoublePlantBlockType

        private lateinit var instance4502: DoublePlantBlockType

        private lateinit var instance4503: DoublePlantBlockType

        private lateinit var instance4504: DoublePlantBlockType

        private lateinit var instance4505: DoublePlantBlockType

        private lateinit var instance4506: DoublePlantBlockType

        private lateinit var instance4507: DoublePlantBlockType

        private lateinit var instance4508: DoublePlantBlockType

        private lateinit var instance4509: DoublePlantBlockType

        private lateinit var instance4510: DoublePlantBlockType

        private lateinit var instance4511: DoublePlantBlockType

        private lateinit var instance4512: DoublePlantBlockType

        init {
            init0()
        }

        public override val primary: DoublePlantBlockType = instance4501

        public override val allInstances: List<DoublePlantBlockType> =
            listOf(instance4501, instance4502, instance4503, instance4504, instance4505, instance4506, instance4507, instance4508, instance4509, instance4510, instance4511, instance4512)

        public fun init0() {
            instance4501 = DoublePlantBlockType(4501.toShort(), "sunflower", 0.toByte())
            instance4502 = DoublePlantBlockType(4502.toShort(), "syringa", 0.toByte())
            instance4503 = DoublePlantBlockType(4503.toShort(), "grass", 0.toByte())
            instance4504 = DoublePlantBlockType(4504.toShort(), "fern", 0.toByte())
            instance4505 = DoublePlantBlockType(4505.toShort(), "rose", 0.toByte())
            instance4506 = DoublePlantBlockType(4506.toShort(), "paeonia", 0.toByte())
            instance4507 = DoublePlantBlockType(4507.toShort(), "sunflower", 1.toByte())
            instance4508 = DoublePlantBlockType(4508.toShort(), "syringa", 1.toByte())
            instance4509 = DoublePlantBlockType(4509.toShort(), "grass", 1.toByte())
            instance4510 = DoublePlantBlockType(4510.toShort(), "fern", 1.toByte())
            instance4511 = DoublePlantBlockType(4511.toShort(), "rose", 1.toByte())
            instance4512 = DoublePlantBlockType(4512.toShort(), "paeonia", 1.toByte())
        }
    }
}
