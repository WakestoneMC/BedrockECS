// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class FenceBlockType private constructor(
    public override val runtimeID: Short,
    public val woodType: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(woodType: String = this.woodType): FenceBlockType {
        val e = FenceBlockType(0.toShort(), woodType)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: FenceBlockType): Boolean {
        var ret = true
        ret = ret && (this.woodType == other.woodType)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:fence"

        public override val itemID: ItemID = ItemID(85)

        private lateinit var instance4774: FenceBlockType

        private lateinit var instance4775: FenceBlockType

        private lateinit var instance4776: FenceBlockType

        private lateinit var instance4777: FenceBlockType

        private lateinit var instance4778: FenceBlockType

        private lateinit var instance4779: FenceBlockType

        init {
            init0()
        }

        public override val primary: FenceBlockType = instance4774

        public override val allInstances: List<FenceBlockType> =
            listOf(instance4774, instance4775, instance4776, instance4777, instance4778, instance4779)

        public fun init0() {
            instance4774 = FenceBlockType(4774.toShort(), "oak")
            instance4775 = FenceBlockType(4775.toShort(), "spruce")
            instance4776 = FenceBlockType(4776.toShort(), "birch")
            instance4777 = FenceBlockType(4777.toShort(), "jungle")
            instance4778 = FenceBlockType(4778.toShort(), "acacia")
            instance4779 = FenceBlockType(4779.toShort(), "dark_oak")
        }
    }
}
