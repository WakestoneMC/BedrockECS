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
public data class RedMushroomBlockBlockType private constructor(
    public override val runtimeID: Short,
    public val hugeMushroomBits: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(hugeMushroomBits: Int = this.hugeMushroomBits): RedMushroomBlockBlockType {
        val e = RedMushroomBlockBlockType(0.toShort(), hugeMushroomBits)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: RedMushroomBlockBlockType): Boolean {
        var ret = true
        ret = ret && (this.hugeMushroomBits == other.hugeMushroomBits)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:red_mushroom_block"

        public override val itemID: ItemID = ItemID(100)

        private lateinit var instance6611: RedMushroomBlockBlockType

        private lateinit var instance6612: RedMushroomBlockBlockType

        private lateinit var instance6613: RedMushroomBlockBlockType

        private lateinit var instance6614: RedMushroomBlockBlockType

        private lateinit var instance6615: RedMushroomBlockBlockType

        private lateinit var instance6616: RedMushroomBlockBlockType

        private lateinit var instance6617: RedMushroomBlockBlockType

        private lateinit var instance6618: RedMushroomBlockBlockType

        private lateinit var instance6619: RedMushroomBlockBlockType

        private lateinit var instance6620: RedMushroomBlockBlockType

        private lateinit var instance6621: RedMushroomBlockBlockType

        private lateinit var instance6622: RedMushroomBlockBlockType

        private lateinit var instance6623: RedMushroomBlockBlockType

        private lateinit var instance6624: RedMushroomBlockBlockType

        private lateinit var instance6625: RedMushroomBlockBlockType

        private lateinit var instance6626: RedMushroomBlockBlockType

        init {
            init0()
        }

        public override val primary: RedMushroomBlockBlockType = instance6611

        public override val allInstances: List<RedMushroomBlockBlockType> =
            listOf(instance6611, instance6612, instance6613, instance6614, instance6615, instance6616, instance6617, instance6618, instance6619, instance6620, instance6621, instance6622, instance6623, instance6624, instance6625, instance6626)

        public fun init0() {
            instance6611 = RedMushroomBlockBlockType(6611.toShort(), 0)
            instance6612 = RedMushroomBlockBlockType(6612.toShort(), 1)
            instance6613 = RedMushroomBlockBlockType(6613.toShort(), 2)
            instance6614 = RedMushroomBlockBlockType(6614.toShort(), 3)
            instance6615 = RedMushroomBlockBlockType(6615.toShort(), 4)
            instance6616 = RedMushroomBlockBlockType(6616.toShort(), 5)
            instance6617 = RedMushroomBlockBlockType(6617.toShort(), 6)
            instance6618 = RedMushroomBlockBlockType(6618.toShort(), 7)
            instance6619 = RedMushroomBlockBlockType(6619.toShort(), 8)
            instance6620 = RedMushroomBlockBlockType(6620.toShort(), 9)
            instance6621 = RedMushroomBlockBlockType(6621.toShort(), 10)
            instance6622 = RedMushroomBlockBlockType(6622.toShort(), 11)
            instance6623 = RedMushroomBlockBlockType(6623.toShort(), 12)
            instance6624 = RedMushroomBlockBlockType(6624.toShort(), 13)
            instance6625 = RedMushroomBlockBlockType(6625.toShort(), 14)
            instance6626 = RedMushroomBlockBlockType(6626.toShort(), 15)
        }
    }
}
