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
public data class BlackGlazedTerracottaBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): BlackGlazedTerracottaBlockType {
        val e = BlackGlazedTerracottaBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BlackGlazedTerracottaBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:black_glazed_terracotta"

        public override val itemID: ItemID = ItemID(235)

        private lateinit var instance488: BlackGlazedTerracottaBlockType

        private lateinit var instance489: BlackGlazedTerracottaBlockType

        private lateinit var instance490: BlackGlazedTerracottaBlockType

        private lateinit var instance491: BlackGlazedTerracottaBlockType

        private lateinit var instance492: BlackGlazedTerracottaBlockType

        private lateinit var instance493: BlackGlazedTerracottaBlockType

        init {
            init0()
        }

        public override val primary: BlackGlazedTerracottaBlockType = instance488

        public override val allInstances: List<BlackGlazedTerracottaBlockType> =
            listOf(instance488, instance489, instance490, instance491, instance492, instance493)

        public fun init0() {
            instance488 = BlackGlazedTerracottaBlockType(488.toShort(), 0)
            instance489 = BlackGlazedTerracottaBlockType(489.toShort(), 1)
            instance490 = BlackGlazedTerracottaBlockType(490.toShort(), 2)
            instance491 = BlackGlazedTerracottaBlockType(491.toShort(), 3)
            instance492 = BlackGlazedTerracottaBlockType(492.toShort(), 4)
            instance493 = BlackGlazedTerracottaBlockType(493.toShort(), 5)
        }
    }
}