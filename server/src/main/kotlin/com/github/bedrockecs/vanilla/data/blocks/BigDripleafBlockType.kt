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
public data class BigDripleafBlockType private constructor(
    public override val runtimeID: Short,
    public val bigDripleafHead: Byte,
    public val bigDripleafTilt: String,
    public val direction: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        bigDripleafHead: Byte = this.bigDripleafHead,
        bigDripleafTilt: String = this.bigDripleafTilt,
        direction: Int = this.direction
    ): BigDripleafBlockType {
        val e = BigDripleafBlockType(0.toShort(), bigDripleafHead, bigDripleafTilt, direction)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BigDripleafBlockType): Boolean {
        var ret = true
        ret = ret && (this.bigDripleafHead == other.bigDripleafHead)
        ret = ret && (this.bigDripleafTilt == other.bigDripleafTilt)
        ret = ret && (this.direction == other.direction)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:big_dripleaf"

        public override val itemID: ItemID = ItemID(-323)

        private lateinit var instance324: BigDripleafBlockType

        private lateinit var instance325: BigDripleafBlockType

        private lateinit var instance326: BigDripleafBlockType

        private lateinit var instance327: BigDripleafBlockType

        private lateinit var instance328: BigDripleafBlockType

        private lateinit var instance329: BigDripleafBlockType

        private lateinit var instance330: BigDripleafBlockType

        private lateinit var instance331: BigDripleafBlockType

        private lateinit var instance332: BigDripleafBlockType

        private lateinit var instance333: BigDripleafBlockType

        private lateinit var instance334: BigDripleafBlockType

        private lateinit var instance335: BigDripleafBlockType

        private lateinit var instance336: BigDripleafBlockType

        private lateinit var instance337: BigDripleafBlockType

        private lateinit var instance338: BigDripleafBlockType

        private lateinit var instance339: BigDripleafBlockType

        private lateinit var instance340: BigDripleafBlockType

        private lateinit var instance341: BigDripleafBlockType

        private lateinit var instance342: BigDripleafBlockType

        private lateinit var instance343: BigDripleafBlockType

        private lateinit var instance344: BigDripleafBlockType

        private lateinit var instance345: BigDripleafBlockType

        private lateinit var instance346: BigDripleafBlockType

        private lateinit var instance347: BigDripleafBlockType

        private lateinit var instance348: BigDripleafBlockType

        private lateinit var instance349: BigDripleafBlockType

        private lateinit var instance350: BigDripleafBlockType

        private lateinit var instance351: BigDripleafBlockType

        private lateinit var instance352: BigDripleafBlockType

        private lateinit var instance353: BigDripleafBlockType

        private lateinit var instance354: BigDripleafBlockType

        private lateinit var instance355: BigDripleafBlockType

        init {
            init0()
        }

        public override val primary: BigDripleafBlockType = instance324

        public override val allInstances: List<BigDripleafBlockType> =
            listOf(instance324, instance325, instance326, instance327, instance328, instance329, instance330, instance331, instance332, instance333, instance334, instance335, instance336, instance337, instance338, instance339, instance340, instance341, instance342, instance343, instance344, instance345, instance346, instance347, instance348, instance349, instance350, instance351, instance352, instance353, instance354, instance355)

        public fun init0() {
            instance324 = BigDripleafBlockType(324.toShort(), 0.toByte(), "none", 0)
            instance325 = BigDripleafBlockType(325.toShort(), 0.toByte(), "unstable", 0)
            instance326 = BigDripleafBlockType(326.toShort(), 0.toByte(), "partial_tilt", 0)
            instance327 = BigDripleafBlockType(327.toShort(), 0.toByte(), "full_tilt", 0)
            instance328 = BigDripleafBlockType(328.toShort(), 1.toByte(), "none", 0)
            instance329 = BigDripleafBlockType(329.toShort(), 1.toByte(), "unstable", 0)
            instance330 = BigDripleafBlockType(330.toShort(), 1.toByte(), "partial_tilt", 0)
            instance331 = BigDripleafBlockType(331.toShort(), 1.toByte(), "full_tilt", 0)
            instance332 = BigDripleafBlockType(332.toShort(), 0.toByte(), "none", 1)
            instance333 = BigDripleafBlockType(333.toShort(), 0.toByte(), "unstable", 1)
            instance334 = BigDripleafBlockType(334.toShort(), 0.toByte(), "partial_tilt", 1)
            instance335 = BigDripleafBlockType(335.toShort(), 0.toByte(), "full_tilt", 1)
            instance336 = BigDripleafBlockType(336.toShort(), 1.toByte(), "none", 1)
            instance337 = BigDripleafBlockType(337.toShort(), 1.toByte(), "unstable", 1)
            instance338 = BigDripleafBlockType(338.toShort(), 1.toByte(), "partial_tilt", 1)
            instance339 = BigDripleafBlockType(339.toShort(), 1.toByte(), "full_tilt", 1)
            instance340 = BigDripleafBlockType(340.toShort(), 0.toByte(), "none", 2)
            instance341 = BigDripleafBlockType(341.toShort(), 0.toByte(), "unstable", 2)
            instance342 = BigDripleafBlockType(342.toShort(), 0.toByte(), "partial_tilt", 2)
            instance343 = BigDripleafBlockType(343.toShort(), 0.toByte(), "full_tilt", 2)
            instance344 = BigDripleafBlockType(344.toShort(), 1.toByte(), "none", 2)
            instance345 = BigDripleafBlockType(345.toShort(), 1.toByte(), "unstable", 2)
            instance346 = BigDripleafBlockType(346.toShort(), 1.toByte(), "partial_tilt", 2)
            instance347 = BigDripleafBlockType(347.toShort(), 1.toByte(), "full_tilt", 2)
            instance348 = BigDripleafBlockType(348.toShort(), 0.toByte(), "none", 3)
            instance349 = BigDripleafBlockType(349.toShort(), 0.toByte(), "unstable", 3)
            instance350 = BigDripleafBlockType(350.toShort(), 0.toByte(), "partial_tilt", 3)
            instance351 = BigDripleafBlockType(351.toShort(), 0.toByte(), "full_tilt", 3)
            instance352 = BigDripleafBlockType(352.toShort(), 1.toByte(), "none", 3)
            instance353 = BigDripleafBlockType(353.toShort(), 1.toByte(), "unstable", 3)
            instance354 = BigDripleafBlockType(354.toShort(), 1.toByte(), "partial_tilt", 3)
            instance355 = BigDripleafBlockType(355.toShort(), 1.toByte(), "full_tilt", 3)
        }
    }
}
