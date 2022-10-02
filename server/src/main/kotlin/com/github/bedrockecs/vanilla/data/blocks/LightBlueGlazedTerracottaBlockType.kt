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
public data class LightBlueGlazedTerracottaBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): LightBlueGlazedTerracottaBlockType {
        val e = LightBlueGlazedTerracottaBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: LightBlueGlazedTerracottaBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:light_blue_glazed_terracotta"

        public override val itemID: ItemID = ItemID(223)

        private lateinit var instance5485: LightBlueGlazedTerracottaBlockType

        private lateinit var instance5486: LightBlueGlazedTerracottaBlockType

        private lateinit var instance5487: LightBlueGlazedTerracottaBlockType

        private lateinit var instance5488: LightBlueGlazedTerracottaBlockType

        private lateinit var instance5489: LightBlueGlazedTerracottaBlockType

        private lateinit var instance5490: LightBlueGlazedTerracottaBlockType

        init {
            init0()
        }

        public override val primary: LightBlueGlazedTerracottaBlockType = instance5485

        public override val allInstances: List<LightBlueGlazedTerracottaBlockType> =
            listOf(instance5485, instance5486, instance5487, instance5488, instance5489, instance5490)

        public fun init0() {
            instance5485 = LightBlueGlazedTerracottaBlockType(5485.toShort(), 0)
            instance5486 = LightBlueGlazedTerracottaBlockType(5486.toShort(), 1)
            instance5487 = LightBlueGlazedTerracottaBlockType(5487.toShort(), 2)
            instance5488 = LightBlueGlazedTerracottaBlockType(5488.toShort(), 3)
            instance5489 = LightBlueGlazedTerracottaBlockType(5489.toShort(), 4)
            instance5490 = LightBlueGlazedTerracottaBlockType(5490.toShort(), 5)
        }
    }
}
