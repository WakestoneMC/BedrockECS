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
public data class WarpedDoorBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int,
    public val doorHingeBit: Byte,
    public val openBit: Byte,
    public val upperBlockBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        direction: Int = this.direction,
        doorHingeBit: Byte = this.doorHingeBit,
        openBit: Byte = this.openBit,
        upperBlockBit: Byte = this.upperBlockBit
    ): WarpedDoorBlockType {
        val e = WarpedDoorBlockType(0.toShort(), direction, doorHingeBit, openBit, upperBlockBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WarpedDoorBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.doorHingeBit == other.doorHingeBit)
        ret = ret && (this.openBit == other.openBit)
        ret = ret && (this.upperBlockBit == other.upperBlockBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:warped_door"

        public override val itemID: ItemID = ItemID(-245)

        private lateinit var instance7540: WarpedDoorBlockType

        private lateinit var instance7541: WarpedDoorBlockType

        private lateinit var instance7542: WarpedDoorBlockType

        private lateinit var instance7543: WarpedDoorBlockType

        private lateinit var instance7544: WarpedDoorBlockType

        private lateinit var instance7545: WarpedDoorBlockType

        private lateinit var instance7546: WarpedDoorBlockType

        private lateinit var instance7547: WarpedDoorBlockType

        private lateinit var instance7548: WarpedDoorBlockType

        private lateinit var instance7549: WarpedDoorBlockType

        private lateinit var instance7550: WarpedDoorBlockType

        private lateinit var instance7551: WarpedDoorBlockType

        private lateinit var instance7552: WarpedDoorBlockType

        private lateinit var instance7553: WarpedDoorBlockType

        private lateinit var instance7554: WarpedDoorBlockType

        private lateinit var instance7555: WarpedDoorBlockType

        private lateinit var instance7556: WarpedDoorBlockType

        private lateinit var instance7557: WarpedDoorBlockType

        private lateinit var instance7558: WarpedDoorBlockType

        private lateinit var instance7559: WarpedDoorBlockType

        private lateinit var instance7560: WarpedDoorBlockType

        private lateinit var instance7561: WarpedDoorBlockType

        private lateinit var instance7562: WarpedDoorBlockType

        private lateinit var instance7563: WarpedDoorBlockType

        private lateinit var instance7564: WarpedDoorBlockType

        private lateinit var instance7565: WarpedDoorBlockType

        private lateinit var instance7566: WarpedDoorBlockType

        private lateinit var instance7567: WarpedDoorBlockType

        private lateinit var instance7568: WarpedDoorBlockType

        private lateinit var instance7569: WarpedDoorBlockType

        private lateinit var instance7570: WarpedDoorBlockType

        private lateinit var instance7571: WarpedDoorBlockType

        init {
            init0()
        }

        public override val primary: WarpedDoorBlockType = instance7540

        public override val allInstances: List<WarpedDoorBlockType> =
            listOf(instance7540, instance7541, instance7542, instance7543, instance7544, instance7545, instance7546, instance7547, instance7548, instance7549, instance7550, instance7551, instance7552, instance7553, instance7554, instance7555, instance7556, instance7557, instance7558, instance7559, instance7560, instance7561, instance7562, instance7563, instance7564, instance7565, instance7566, instance7567, instance7568, instance7569, instance7570, instance7571)

        public fun init0() {
            instance7540 = WarpedDoorBlockType(7540.toShort(), 0, 0.toByte(), 0.toByte(), 0.toByte())
            instance7541 = WarpedDoorBlockType(7541.toShort(), 1, 0.toByte(), 0.toByte(), 0.toByte())
            instance7542 = WarpedDoorBlockType(7542.toShort(), 2, 0.toByte(), 0.toByte(), 0.toByte())
            instance7543 = WarpedDoorBlockType(7543.toShort(), 3, 0.toByte(), 0.toByte(), 0.toByte())
            instance7544 = WarpedDoorBlockType(7544.toShort(), 0, 0.toByte(), 1.toByte(), 0.toByte())
            instance7545 = WarpedDoorBlockType(7545.toShort(), 1, 0.toByte(), 1.toByte(), 0.toByte())
            instance7546 = WarpedDoorBlockType(7546.toShort(), 2, 0.toByte(), 1.toByte(), 0.toByte())
            instance7547 = WarpedDoorBlockType(7547.toShort(), 3, 0.toByte(), 1.toByte(), 0.toByte())
            instance7548 = WarpedDoorBlockType(7548.toShort(), 0, 0.toByte(), 0.toByte(), 1.toByte())
            instance7549 = WarpedDoorBlockType(7549.toShort(), 1, 0.toByte(), 0.toByte(), 1.toByte())
            instance7550 = WarpedDoorBlockType(7550.toShort(), 2, 0.toByte(), 0.toByte(), 1.toByte())
            instance7551 = WarpedDoorBlockType(7551.toShort(), 3, 0.toByte(), 0.toByte(), 1.toByte())
            instance7552 = WarpedDoorBlockType(7552.toShort(), 0, 0.toByte(), 1.toByte(), 1.toByte())
            instance7553 = WarpedDoorBlockType(7553.toShort(), 1, 0.toByte(), 1.toByte(), 1.toByte())
            instance7554 = WarpedDoorBlockType(7554.toShort(), 2, 0.toByte(), 1.toByte(), 1.toByte())
            instance7555 = WarpedDoorBlockType(7555.toShort(), 3, 0.toByte(), 1.toByte(), 1.toByte())
            instance7556 = WarpedDoorBlockType(7556.toShort(), 0, 1.toByte(), 0.toByte(), 0.toByte())
            instance7557 = WarpedDoorBlockType(7557.toShort(), 1, 1.toByte(), 0.toByte(), 0.toByte())
            instance7558 = WarpedDoorBlockType(7558.toShort(), 2, 1.toByte(), 0.toByte(), 0.toByte())
            instance7559 = WarpedDoorBlockType(7559.toShort(), 3, 1.toByte(), 0.toByte(), 0.toByte())
            instance7560 = WarpedDoorBlockType(7560.toShort(), 0, 1.toByte(), 1.toByte(), 0.toByte())
            instance7561 = WarpedDoorBlockType(7561.toShort(), 1, 1.toByte(), 1.toByte(), 0.toByte())
            instance7562 = WarpedDoorBlockType(7562.toShort(), 2, 1.toByte(), 1.toByte(), 0.toByte())
            instance7563 = WarpedDoorBlockType(7563.toShort(), 3, 1.toByte(), 1.toByte(), 0.toByte())
            instance7564 = WarpedDoorBlockType(7564.toShort(), 0, 1.toByte(), 0.toByte(), 1.toByte())
            instance7565 = WarpedDoorBlockType(7565.toShort(), 1, 1.toByte(), 0.toByte(), 1.toByte())
            instance7566 = WarpedDoorBlockType(7566.toShort(), 2, 1.toByte(), 0.toByte(), 1.toByte())
            instance7567 = WarpedDoorBlockType(7567.toShort(), 3, 1.toByte(), 0.toByte(), 1.toByte())
            instance7568 = WarpedDoorBlockType(7568.toShort(), 0, 1.toByte(), 1.toByte(), 1.toByte())
            instance7569 = WarpedDoorBlockType(7569.toShort(), 1, 1.toByte(), 1.toByte(), 1.toByte())
            instance7570 = WarpedDoorBlockType(7570.toShort(), 2, 1.toByte(), 1.toByte(), 1.toByte())
            instance7571 = WarpedDoorBlockType(7571.toShort(), 3, 1.toByte(), 1.toByte(), 1.toByte())
        }
    }
}
