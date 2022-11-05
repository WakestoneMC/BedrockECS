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
public data class EndPortalBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): EndPortalBlockType {
        val e = EndPortalBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: EndPortalBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:end_portal"

        public override val itemID: ItemID = ItemID(119)

        private lateinit var instance4730: EndPortalBlockType

        init {
            init0()
        }

        public override val primary: EndPortalBlockType = instance4730

        public override val allInstances: List<EndPortalBlockType> = listOf(instance4730)

        public fun init0() {
            instance4730 = EndPortalBlockType(4730.toShort())
        }
    }
}
