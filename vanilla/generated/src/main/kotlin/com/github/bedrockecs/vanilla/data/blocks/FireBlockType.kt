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
public data class FireBlockType private constructor(
    public override val runtimeID: Short,
    public val age: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(age: Int = this.age): FireBlockType {
        val e = FireBlockType(0.toShort(), age)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: FireBlockType): Boolean {
        var ret = true
        ret = ret && (this.age == other.age)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:fire"

        public override val itemID: ItemID = ItemID(51)

        private lateinit var instance4796: FireBlockType

        private lateinit var instance4797: FireBlockType

        private lateinit var instance4798: FireBlockType

        private lateinit var instance4799: FireBlockType

        private lateinit var instance4800: FireBlockType

        private lateinit var instance4801: FireBlockType

        private lateinit var instance4802: FireBlockType

        private lateinit var instance4803: FireBlockType

        private lateinit var instance4804: FireBlockType

        private lateinit var instance4805: FireBlockType

        private lateinit var instance4806: FireBlockType

        private lateinit var instance4807: FireBlockType

        private lateinit var instance4808: FireBlockType

        private lateinit var instance4809: FireBlockType

        private lateinit var instance4810: FireBlockType

        private lateinit var instance4811: FireBlockType

        init {
            init0()
        }

        public override val primary: FireBlockType = instance4796

        public override val allInstances: List<FireBlockType> =
            listOf(instance4796, instance4797, instance4798, instance4799, instance4800, instance4801, instance4802, instance4803, instance4804, instance4805, instance4806, instance4807, instance4808, instance4809, instance4810, instance4811)

        public fun init0() {
            instance4796 = FireBlockType(4796.toShort(), 0)
            instance4797 = FireBlockType(4797.toShort(), 1)
            instance4798 = FireBlockType(4798.toShort(), 2)
            instance4799 = FireBlockType(4799.toShort(), 3)
            instance4800 = FireBlockType(4800.toShort(), 4)
            instance4801 = FireBlockType(4801.toShort(), 5)
            instance4802 = FireBlockType(4802.toShort(), 6)
            instance4803 = FireBlockType(4803.toShort(), 7)
            instance4804 = FireBlockType(4804.toShort(), 8)
            instance4805 = FireBlockType(4805.toShort(), 9)
            instance4806 = FireBlockType(4806.toShort(), 10)
            instance4807 = FireBlockType(4807.toShort(), 11)
            instance4808 = FireBlockType(4808.toShort(), 12)
            instance4809 = FireBlockType(4809.toShort(), 13)
            instance4810 = FireBlockType(4810.toShort(), 14)
            instance4811 = FireBlockType(4811.toShort(), 15)
        }
    }
}