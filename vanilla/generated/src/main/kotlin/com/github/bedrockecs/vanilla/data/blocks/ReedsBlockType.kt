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
public data class ReedsBlockType private constructor(
    public override val runtimeID: Short,
    public val age: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(age: Int = this.age): ReedsBlockType {
        val e = ReedsBlockType(0.toShort(), age)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: ReedsBlockType): Boolean {
        var ret = true
        ret = ret && (this.age == other.age)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:reeds"

        public override val itemID: ItemID = ItemID(83)

        private lateinit var instance6673: ReedsBlockType

        private lateinit var instance6674: ReedsBlockType

        private lateinit var instance6675: ReedsBlockType

        private lateinit var instance6676: ReedsBlockType

        private lateinit var instance6677: ReedsBlockType

        private lateinit var instance6678: ReedsBlockType

        private lateinit var instance6679: ReedsBlockType

        private lateinit var instance6680: ReedsBlockType

        private lateinit var instance6681: ReedsBlockType

        private lateinit var instance6682: ReedsBlockType

        private lateinit var instance6683: ReedsBlockType

        private lateinit var instance6684: ReedsBlockType

        private lateinit var instance6685: ReedsBlockType

        private lateinit var instance6686: ReedsBlockType

        private lateinit var instance6687: ReedsBlockType

        private lateinit var instance6688: ReedsBlockType

        init {
            init0()
        }

        public override val primary: ReedsBlockType = instance6673

        public override val allInstances: List<ReedsBlockType> =
            listOf(instance6673, instance6674, instance6675, instance6676, instance6677, instance6678, instance6679, instance6680, instance6681, instance6682, instance6683, instance6684, instance6685, instance6686, instance6687, instance6688)

        public fun init0() {
            instance6673 = ReedsBlockType(6673.toShort(), 0)
            instance6674 = ReedsBlockType(6674.toShort(), 1)
            instance6675 = ReedsBlockType(6675.toShort(), 2)
            instance6676 = ReedsBlockType(6676.toShort(), 3)
            instance6677 = ReedsBlockType(6677.toShort(), 4)
            instance6678 = ReedsBlockType(6678.toShort(), 5)
            instance6679 = ReedsBlockType(6679.toShort(), 6)
            instance6680 = ReedsBlockType(6680.toShort(), 7)
            instance6681 = ReedsBlockType(6681.toShort(), 8)
            instance6682 = ReedsBlockType(6682.toShort(), 9)
            instance6683 = ReedsBlockType(6683.toShort(), 10)
            instance6684 = ReedsBlockType(6684.toShort(), 11)
            instance6685 = ReedsBlockType(6685.toShort(), 12)
            instance6686 = ReedsBlockType(6686.toShort(), 13)
            instance6687 = ReedsBlockType(6687.toShort(), 14)
            instance6688 = ReedsBlockType(6688.toShort(), 15)
        }
    }
}