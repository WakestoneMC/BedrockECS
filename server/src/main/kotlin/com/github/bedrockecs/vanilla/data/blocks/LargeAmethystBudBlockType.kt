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
public data class LargeAmethystBudBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): LargeAmethystBudBlockType {
        val e = LargeAmethystBudBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: LargeAmethystBudBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:large_amethyst_bud"

        public override val itemID: ItemID = ItemID(-330)

        private lateinit var instance5368: LargeAmethystBudBlockType

        private lateinit var instance5369: LargeAmethystBudBlockType

        private lateinit var instance5370: LargeAmethystBudBlockType

        private lateinit var instance5371: LargeAmethystBudBlockType

        private lateinit var instance5372: LargeAmethystBudBlockType

        private lateinit var instance5373: LargeAmethystBudBlockType

        init {
            init0()
        }

        public override val primary: LargeAmethystBudBlockType = instance5368

        public override val allInstances: List<LargeAmethystBudBlockType> =
            listOf(instance5368, instance5369, instance5370, instance5371, instance5372, instance5373)

        public fun init0() {
            instance5368 = LargeAmethystBudBlockType(5368.toShort(), 0)
            instance5369 = LargeAmethystBudBlockType(5369.toShort(), 1)
            instance5370 = LargeAmethystBudBlockType(5370.toShort(), 2)
            instance5371 = LargeAmethystBudBlockType(5371.toShort(), 3)
            instance5372 = LargeAmethystBudBlockType(5372.toShort(), 4)
            instance5373 = LargeAmethystBudBlockType(5373.toShort(), 5)
        }
    }
}