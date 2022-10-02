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
public data class BeetrootBlockType private constructor(
    public override val runtimeID: Short,
    public val growth: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(growth: Int = this.growth): BeetrootBlockType {
        val e = BeetrootBlockType(0.toShort(), growth)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BeetrootBlockType): Boolean {
        var ret = true
        ret = ret && (this.growth == other.growth)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:beetroot"

        public override val itemID: ItemID = ItemID(244)

        private lateinit var instance284: BeetrootBlockType

        private lateinit var instance285: BeetrootBlockType

        private lateinit var instance286: BeetrootBlockType

        private lateinit var instance287: BeetrootBlockType

        private lateinit var instance288: BeetrootBlockType

        private lateinit var instance289: BeetrootBlockType

        private lateinit var instance290: BeetrootBlockType

        private lateinit var instance291: BeetrootBlockType

        init {
            init0()
        }

        public override val primary: BeetrootBlockType = instance284

        public override val allInstances: List<BeetrootBlockType> =
            listOf(instance284, instance285, instance286, instance287, instance288, instance289, instance290, instance291)

        public fun init0() {
            instance284 = BeetrootBlockType(284.toShort(), 0)
            instance285 = BeetrootBlockType(285.toShort(), 1)
            instance286 = BeetrootBlockType(286.toShort(), 2)
            instance287 = BeetrootBlockType(287.toShort(), 3)
            instance288 = BeetrootBlockType(288.toShort(), 4)
            instance289 = BeetrootBlockType(289.toShort(), 5)
            instance290 = BeetrootBlockType(290.toShort(), 6)
            instance291 = BeetrootBlockType(291.toShort(), 7)
        }
    }
}
