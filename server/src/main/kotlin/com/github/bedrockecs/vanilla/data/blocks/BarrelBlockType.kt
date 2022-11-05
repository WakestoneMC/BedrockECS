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
public data class BarrelBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int,
    public val openBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection, openBit: Byte = this.openBit):
        BarrelBlockType {
        val e = BarrelBlockType(0.toShort(), facingDirection, openBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BarrelBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        ret = ret && (this.openBit == other.openBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:barrel"

        public override val itemID: ItemID = ItemID(-203)

        private lateinit var instance201: BarrelBlockType

        private lateinit var instance202: BarrelBlockType

        private lateinit var instance203: BarrelBlockType

        private lateinit var instance204: BarrelBlockType

        private lateinit var instance205: BarrelBlockType

        private lateinit var instance206: BarrelBlockType

        private lateinit var instance207: BarrelBlockType

        private lateinit var instance208: BarrelBlockType

        private lateinit var instance209: BarrelBlockType

        private lateinit var instance210: BarrelBlockType

        private lateinit var instance211: BarrelBlockType

        private lateinit var instance212: BarrelBlockType

        init {
            init0()
        }

        public override val primary: BarrelBlockType = instance201

        public override val allInstances: List<BarrelBlockType> =
            listOf(instance201, instance202, instance203, instance204, instance205, instance206, instance207, instance208, instance209, instance210, instance211, instance212)

        public fun init0() {
            instance201 = BarrelBlockType(201.toShort(), 0, 0.toByte())
            instance202 = BarrelBlockType(202.toShort(), 1, 0.toByte())
            instance203 = BarrelBlockType(203.toShort(), 2, 0.toByte())
            instance204 = BarrelBlockType(204.toShort(), 3, 0.toByte())
            instance205 = BarrelBlockType(205.toShort(), 4, 0.toByte())
            instance206 = BarrelBlockType(206.toShort(), 5, 0.toByte())
            instance207 = BarrelBlockType(207.toShort(), 0, 1.toByte())
            instance208 = BarrelBlockType(208.toShort(), 1, 1.toByte())
            instance209 = BarrelBlockType(209.toShort(), 2, 1.toByte())
            instance210 = BarrelBlockType(210.toShort(), 3, 1.toByte())
            instance211 = BarrelBlockType(211.toShort(), 4, 1.toByte())
            instance212 = BarrelBlockType(212.toShort(), 5, 1.toByte())
        }
    }
}
