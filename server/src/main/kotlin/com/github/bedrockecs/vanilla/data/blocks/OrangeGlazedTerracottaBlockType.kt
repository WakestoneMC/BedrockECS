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
public data class OrangeGlazedTerracottaBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): OrangeGlazedTerracottaBlockType {
        val e = OrangeGlazedTerracottaBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: OrangeGlazedTerracottaBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:orange_glazed_terracotta"

        public override val itemID: ItemID = ItemID(221)

        private lateinit var instance5750: OrangeGlazedTerracottaBlockType

        private lateinit var instance5751: OrangeGlazedTerracottaBlockType

        private lateinit var instance5752: OrangeGlazedTerracottaBlockType

        private lateinit var instance5753: OrangeGlazedTerracottaBlockType

        private lateinit var instance5754: OrangeGlazedTerracottaBlockType

        private lateinit var instance5755: OrangeGlazedTerracottaBlockType

        init {
            init0()
        }

        public override val primary: OrangeGlazedTerracottaBlockType = instance5750

        public override val allInstances: List<OrangeGlazedTerracottaBlockType> =
            listOf(instance5750, instance5751, instance5752, instance5753, instance5754, instance5755)

        public fun init0() {
            instance5750 = OrangeGlazedTerracottaBlockType(5750.toShort(), 0)
            instance5751 = OrangeGlazedTerracottaBlockType(5751.toShort(), 1)
            instance5752 = OrangeGlazedTerracottaBlockType(5752.toShort(), 2)
            instance5753 = OrangeGlazedTerracottaBlockType(5753.toShort(), 3)
            instance5754 = OrangeGlazedTerracottaBlockType(5754.toShort(), 4)
            instance5755 = OrangeGlazedTerracottaBlockType(5755.toShort(), 5)
        }
    }
}
