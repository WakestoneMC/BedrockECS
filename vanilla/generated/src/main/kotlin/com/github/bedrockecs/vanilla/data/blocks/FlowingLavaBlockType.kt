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
public data class FlowingLavaBlockType private constructor(
    public override val runtimeID: Short,
    public val liquidDepth: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(liquidDepth: Int = this.liquidDepth): FlowingLavaBlockType {
        val e = FlowingLavaBlockType(0.toShort(), liquidDepth)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: FlowingLavaBlockType): Boolean {
        var ret = true
        ret = ret && (this.liquidDepth == other.liquidDepth)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:flowing_lava"

        public override val itemID: ItemID = ItemID(10)

        private lateinit var instance4816: FlowingLavaBlockType

        private lateinit var instance4817: FlowingLavaBlockType

        private lateinit var instance4818: FlowingLavaBlockType

        private lateinit var instance4819: FlowingLavaBlockType

        private lateinit var instance4820: FlowingLavaBlockType

        private lateinit var instance4821: FlowingLavaBlockType

        private lateinit var instance4822: FlowingLavaBlockType

        private lateinit var instance4823: FlowingLavaBlockType

        private lateinit var instance4824: FlowingLavaBlockType

        private lateinit var instance4825: FlowingLavaBlockType

        private lateinit var instance4826: FlowingLavaBlockType

        private lateinit var instance4827: FlowingLavaBlockType

        private lateinit var instance4828: FlowingLavaBlockType

        private lateinit var instance4829: FlowingLavaBlockType

        private lateinit var instance4830: FlowingLavaBlockType

        private lateinit var instance4831: FlowingLavaBlockType

        init {
            init0()
        }

        public override val primary: FlowingLavaBlockType = instance4816

        public override val allInstances: List<FlowingLavaBlockType> =
            listOf(instance4816, instance4817, instance4818, instance4819, instance4820, instance4821, instance4822, instance4823, instance4824, instance4825, instance4826, instance4827, instance4828, instance4829, instance4830, instance4831)

        public fun init0() {
            instance4816 = FlowingLavaBlockType(4816.toShort(), 0)
            instance4817 = FlowingLavaBlockType(4817.toShort(), 1)
            instance4818 = FlowingLavaBlockType(4818.toShort(), 2)
            instance4819 = FlowingLavaBlockType(4819.toShort(), 3)
            instance4820 = FlowingLavaBlockType(4820.toShort(), 4)
            instance4821 = FlowingLavaBlockType(4821.toShort(), 5)
            instance4822 = FlowingLavaBlockType(4822.toShort(), 6)
            instance4823 = FlowingLavaBlockType(4823.toShort(), 7)
            instance4824 = FlowingLavaBlockType(4824.toShort(), 8)
            instance4825 = FlowingLavaBlockType(4825.toShort(), 9)
            instance4826 = FlowingLavaBlockType(4826.toShort(), 10)
            instance4827 = FlowingLavaBlockType(4827.toShort(), 11)
            instance4828 = FlowingLavaBlockType(4828.toShort(), 12)
            instance4829 = FlowingLavaBlockType(4829.toShort(), 13)
            instance4830 = FlowingLavaBlockType(4830.toShort(), 14)
            instance4831 = FlowingLavaBlockType(4831.toShort(), 15)
        }
    }
}
