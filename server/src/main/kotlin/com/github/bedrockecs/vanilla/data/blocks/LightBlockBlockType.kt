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
public data class LightBlockBlockType private constructor(
    public override val runtimeID: Short,
    public val blockLightLevel: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(blockLightLevel: Int = this.blockLightLevel): LightBlockBlockType {
        val e = LightBlockBlockType(0.toShort(), blockLightLevel)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: LightBlockBlockType): Boolean {
        var ret = true
        ret = ret && (this.blockLightLevel == other.blockLightLevel)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:light_block"

        public override val itemID: ItemID = ItemID(-215)

        private lateinit var instance5459: LightBlockBlockType

        private lateinit var instance5460: LightBlockBlockType

        private lateinit var instance5461: LightBlockBlockType

        private lateinit var instance5462: LightBlockBlockType

        private lateinit var instance5463: LightBlockBlockType

        private lateinit var instance5464: LightBlockBlockType

        private lateinit var instance5465: LightBlockBlockType

        private lateinit var instance5466: LightBlockBlockType

        private lateinit var instance5467: LightBlockBlockType

        private lateinit var instance5468: LightBlockBlockType

        private lateinit var instance5469: LightBlockBlockType

        private lateinit var instance5470: LightBlockBlockType

        private lateinit var instance5471: LightBlockBlockType

        private lateinit var instance5472: LightBlockBlockType

        private lateinit var instance5473: LightBlockBlockType

        private lateinit var instance5474: LightBlockBlockType

        init {
            init0()
        }

        public override val primary: LightBlockBlockType = instance5459

        public override val allInstances: List<LightBlockBlockType> =
            listOf(instance5459, instance5460, instance5461, instance5462, instance5463, instance5464, instance5465, instance5466, instance5467, instance5468, instance5469, instance5470, instance5471, instance5472, instance5473, instance5474)

        public fun init0() {
            instance5459 = LightBlockBlockType(5459.toShort(), 0)
            instance5460 = LightBlockBlockType(5460.toShort(), 1)
            instance5461 = LightBlockBlockType(5461.toShort(), 2)
            instance5462 = LightBlockBlockType(5462.toShort(), 3)
            instance5463 = LightBlockBlockType(5463.toShort(), 4)
            instance5464 = LightBlockBlockType(5464.toShort(), 5)
            instance5465 = LightBlockBlockType(5465.toShort(), 6)
            instance5466 = LightBlockBlockType(5466.toShort(), 7)
            instance5467 = LightBlockBlockType(5467.toShort(), 8)
            instance5468 = LightBlockBlockType(5468.toShort(), 9)
            instance5469 = LightBlockBlockType(5469.toShort(), 10)
            instance5470 = LightBlockBlockType(5470.toShort(), 11)
            instance5471 = LightBlockBlockType(5471.toShort(), 12)
            instance5472 = LightBlockBlockType(5472.toShort(), 13)
            instance5473 = LightBlockBlockType(5473.toShort(), 14)
            instance5474 = LightBlockBlockType(5474.toShort(), 15)
        }
    }
}
