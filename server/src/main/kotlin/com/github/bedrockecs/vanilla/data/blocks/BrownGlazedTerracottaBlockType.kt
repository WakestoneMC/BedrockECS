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
public data class BrownGlazedTerracottaBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): BrownGlazedTerracottaBlockType {
        val e = BrownGlazedTerracottaBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BrownGlazedTerracottaBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:brown_glazed_terracotta"

        public override val itemID: ItemID = ItemID(232)

        private lateinit var instance894: BrownGlazedTerracottaBlockType

        private lateinit var instance895: BrownGlazedTerracottaBlockType

        private lateinit var instance896: BrownGlazedTerracottaBlockType

        private lateinit var instance897: BrownGlazedTerracottaBlockType

        private lateinit var instance898: BrownGlazedTerracottaBlockType

        private lateinit var instance899: BrownGlazedTerracottaBlockType

        init {
            init0()
        }

        public override val primary: BrownGlazedTerracottaBlockType = instance894

        public override val allInstances: List<BrownGlazedTerracottaBlockType> =
            listOf(instance894, instance895, instance896, instance897, instance898, instance899)

        public fun init0() {
            instance894 = BrownGlazedTerracottaBlockType(894.toShort(), 0)
            instance895 = BrownGlazedTerracottaBlockType(895.toShort(), 1)
            instance896 = BrownGlazedTerracottaBlockType(896.toShort(), 2)
            instance897 = BrownGlazedTerracottaBlockType(897.toShort(), 3)
            instance898 = BrownGlazedTerracottaBlockType(898.toShort(), 4)
            instance899 = BrownGlazedTerracottaBlockType(899.toShort(), 5)
        }
    }
}
