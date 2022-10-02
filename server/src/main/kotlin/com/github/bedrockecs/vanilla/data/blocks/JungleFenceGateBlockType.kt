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
public data class JungleFenceGateBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int,
    public val inWallBit: Byte,
    public val openBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        direction: Int = this.direction,
        inWallBit: Byte = this.inWallBit,
        openBit: Byte = this.openBit
    ): JungleFenceGateBlockType {
        val e = JungleFenceGateBlockType(0.toShort(), direction, inWallBit, openBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: JungleFenceGateBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.inWallBit == other.inWallBit)
        ret = ret && (this.openBit == other.openBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:jungle_fence_gate"

        public override val itemID: ItemID = ItemID(185)

        private lateinit var instance5254: JungleFenceGateBlockType

        private lateinit var instance5255: JungleFenceGateBlockType

        private lateinit var instance5256: JungleFenceGateBlockType

        private lateinit var instance5257: JungleFenceGateBlockType

        private lateinit var instance5258: JungleFenceGateBlockType

        private lateinit var instance5259: JungleFenceGateBlockType

        private lateinit var instance5260: JungleFenceGateBlockType

        private lateinit var instance5261: JungleFenceGateBlockType

        private lateinit var instance5262: JungleFenceGateBlockType

        private lateinit var instance5263: JungleFenceGateBlockType

        private lateinit var instance5264: JungleFenceGateBlockType

        private lateinit var instance5265: JungleFenceGateBlockType

        private lateinit var instance5266: JungleFenceGateBlockType

        private lateinit var instance5267: JungleFenceGateBlockType

        private lateinit var instance5268: JungleFenceGateBlockType

        private lateinit var instance5269: JungleFenceGateBlockType

        init {
            init0()
        }

        public override val primary: JungleFenceGateBlockType = instance5254

        public override val allInstances: List<JungleFenceGateBlockType> =
            listOf(instance5254, instance5255, instance5256, instance5257, instance5258, instance5259, instance5260, instance5261, instance5262, instance5263, instance5264, instance5265, instance5266, instance5267, instance5268, instance5269)

        public fun init0() {
            instance5254 = JungleFenceGateBlockType(5254.toShort(), 0, 0.toByte(), 0.toByte())
            instance5255 = JungleFenceGateBlockType(5255.toShort(), 1, 0.toByte(), 0.toByte())
            instance5256 = JungleFenceGateBlockType(5256.toShort(), 2, 0.toByte(), 0.toByte())
            instance5257 = JungleFenceGateBlockType(5257.toShort(), 3, 0.toByte(), 0.toByte())
            instance5258 = JungleFenceGateBlockType(5258.toShort(), 0, 0.toByte(), 1.toByte())
            instance5259 = JungleFenceGateBlockType(5259.toShort(), 1, 0.toByte(), 1.toByte())
            instance5260 = JungleFenceGateBlockType(5260.toShort(), 2, 0.toByte(), 1.toByte())
            instance5261 = JungleFenceGateBlockType(5261.toShort(), 3, 0.toByte(), 1.toByte())
            instance5262 = JungleFenceGateBlockType(5262.toShort(), 0, 1.toByte(), 0.toByte())
            instance5263 = JungleFenceGateBlockType(5263.toShort(), 1, 1.toByte(), 0.toByte())
            instance5264 = JungleFenceGateBlockType(5264.toShort(), 2, 1.toByte(), 0.toByte())
            instance5265 = JungleFenceGateBlockType(5265.toShort(), 3, 1.toByte(), 0.toByte())
            instance5266 = JungleFenceGateBlockType(5266.toShort(), 0, 1.toByte(), 1.toByte())
            instance5267 = JungleFenceGateBlockType(5267.toShort(), 1, 1.toByte(), 1.toByte())
            instance5268 = JungleFenceGateBlockType(5268.toShort(), 2, 1.toByte(), 1.toByte())
            instance5269 = JungleFenceGateBlockType(5269.toShort(), 3, 1.toByte(), 1.toByte())
        }
    }
}
