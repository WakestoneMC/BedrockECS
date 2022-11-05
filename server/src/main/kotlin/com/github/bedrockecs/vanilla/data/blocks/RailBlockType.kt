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
public data class RailBlockType private constructor(
    public override val runtimeID: Short,
    public val railDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(railDirection: Int = this.railDirection): RailBlockType {
        val e = RailBlockType(0.toShort(), railDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: RailBlockType): Boolean {
        var ret = true
        ret = ret && (this.railDirection == other.railDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:rail"

        public override val itemID: ItemID = ItemID(66)

        private lateinit var instance6570: RailBlockType

        private lateinit var instance6571: RailBlockType

        private lateinit var instance6572: RailBlockType

        private lateinit var instance6573: RailBlockType

        private lateinit var instance6574: RailBlockType

        private lateinit var instance6575: RailBlockType

        private lateinit var instance6576: RailBlockType

        private lateinit var instance6577: RailBlockType

        private lateinit var instance6578: RailBlockType

        private lateinit var instance6579: RailBlockType

        init {
            init0()
        }

        public override val primary: RailBlockType = instance6570

        public override val allInstances: List<RailBlockType> =
            listOf(instance6570, instance6571, instance6572, instance6573, instance6574, instance6575, instance6576, instance6577, instance6578, instance6579)

        public fun init0() {
            instance6570 = RailBlockType(6570.toShort(), 0)
            instance6571 = RailBlockType(6571.toShort(), 1)
            instance6572 = RailBlockType(6572.toShort(), 2)
            instance6573 = RailBlockType(6573.toShort(), 3)
            instance6574 = RailBlockType(6574.toShort(), 4)
            instance6575 = RailBlockType(6575.toShort(), 5)
            instance6576 = RailBlockType(6576.toShort(), 6)
            instance6577 = RailBlockType(6577.toShort(), 7)
            instance6578 = RailBlockType(6578.toShort(), 8)
            instance6579 = RailBlockType(6579.toShort(), 9)
        }
    }
}
