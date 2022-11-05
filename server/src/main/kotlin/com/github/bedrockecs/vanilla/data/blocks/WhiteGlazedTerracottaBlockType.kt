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
public data class WhiteGlazedTerracottaBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): WhiteGlazedTerracottaBlockType {
        val e = WhiteGlazedTerracottaBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WhiteGlazedTerracottaBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:white_glazed_terracotta"

        public override val itemID: ItemID = ItemID(220)

        private lateinit var instance7798: WhiteGlazedTerracottaBlockType

        private lateinit var instance7799: WhiteGlazedTerracottaBlockType

        private lateinit var instance7800: WhiteGlazedTerracottaBlockType

        private lateinit var instance7801: WhiteGlazedTerracottaBlockType

        private lateinit var instance7802: WhiteGlazedTerracottaBlockType

        private lateinit var instance7803: WhiteGlazedTerracottaBlockType

        init {
            init0()
        }

        public override val primary: WhiteGlazedTerracottaBlockType = instance7798

        public override val allInstances: List<WhiteGlazedTerracottaBlockType> =
            listOf(instance7798, instance7799, instance7800, instance7801, instance7802, instance7803)

        public fun init0() {
            instance7798 = WhiteGlazedTerracottaBlockType(7798.toShort(), 0)
            instance7799 = WhiteGlazedTerracottaBlockType(7799.toShort(), 1)
            instance7800 = WhiteGlazedTerracottaBlockType(7800.toShort(), 2)
            instance7801 = WhiteGlazedTerracottaBlockType(7801.toShort(), 3)
            instance7802 = WhiteGlazedTerracottaBlockType(7802.toShort(), 4)
            instance7803 = WhiteGlazedTerracottaBlockType(7803.toShort(), 5)
        }
    }
}