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
public data class RedNetherBrickBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): RedNetherBrickBlockType {
        val e = RedNetherBrickBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: RedNetherBrickBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:red_nether_brick"

        public override val itemID: ItemID = ItemID(215)

        private lateinit var instance6627: RedNetherBrickBlockType

        init {
            init0()
        }

        public override val primary: RedNetherBrickBlockType = instance6627

        public override val allInstances: List<RedNetherBrickBlockType> = listOf(instance6627)

        public fun init0() {
            instance6627 = RedNetherBrickBlockType(6627.toShort())
        }
    }
}
