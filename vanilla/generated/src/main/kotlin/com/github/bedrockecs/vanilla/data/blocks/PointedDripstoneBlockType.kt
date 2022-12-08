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
public data class PointedDripstoneBlockType private constructor(
    public override val runtimeID: Short,
    public val dripstoneThickness: String,
    public val hanging: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        dripstoneThickness: String = this.dripstoneThickness,
        hanging: Byte =
            this.hanging
    ): PointedDripstoneBlockType {
        val e = PointedDripstoneBlockType(0.toShort(), dripstoneThickness, hanging)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PointedDripstoneBlockType): Boolean {
        var ret = true
        ret = ret && (this.dripstoneThickness == other.dripstoneThickness)
        ret = ret && (this.hanging == other.hanging)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:pointed_dripstone"

        public override val itemID: ItemID = ItemID(-308)

        private lateinit var instance5807: PointedDripstoneBlockType

        private lateinit var instance5808: PointedDripstoneBlockType

        private lateinit var instance5809: PointedDripstoneBlockType

        private lateinit var instance5810: PointedDripstoneBlockType

        private lateinit var instance5811: PointedDripstoneBlockType

        private lateinit var instance5812: PointedDripstoneBlockType

        private lateinit var instance5813: PointedDripstoneBlockType

        private lateinit var instance5814: PointedDripstoneBlockType

        private lateinit var instance5815: PointedDripstoneBlockType

        private lateinit var instance5816: PointedDripstoneBlockType

        init {
            init0()
        }

        public override val primary: PointedDripstoneBlockType = instance5807

        public override val allInstances: List<PointedDripstoneBlockType> =
            listOf(instance5807, instance5808, instance5809, instance5810, instance5811, instance5812, instance5813, instance5814, instance5815, instance5816)

        public fun init0() {
            instance5807 = PointedDripstoneBlockType(5807.toShort(), "tip", 0.toByte())
            instance5808 = PointedDripstoneBlockType(5808.toShort(), "frustum", 0.toByte())
            instance5809 = PointedDripstoneBlockType(5809.toShort(), "middle", 0.toByte())
            instance5810 = PointedDripstoneBlockType(5810.toShort(), "base", 0.toByte())
            instance5811 = PointedDripstoneBlockType(5811.toShort(), "merge", 0.toByte())
            instance5812 = PointedDripstoneBlockType(5812.toShort(), "tip", 1.toByte())
            instance5813 = PointedDripstoneBlockType(5813.toShort(), "frustum", 1.toByte())
            instance5814 = PointedDripstoneBlockType(5814.toShort(), "middle", 1.toByte())
            instance5815 = PointedDripstoneBlockType(5815.toShort(), "base", 1.toByte())
            instance5816 = PointedDripstoneBlockType(5816.toShort(), "merge", 1.toByte())
        }
    }
}