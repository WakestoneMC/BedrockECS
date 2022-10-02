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
public data class GoldOreBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): GoldOreBlockType {
        val e = GoldOreBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: GoldOreBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:gold_ore"

        public override val itemID: ItemID = ItemID(14)

        private lateinit var instance4977: GoldOreBlockType

        init {
            init0()
        }

        public override val primary: GoldOreBlockType = instance4977

        public override val allInstances: List<GoldOreBlockType> = listOf(instance4977)

        public fun init0() {
            instance4977 = GoldOreBlockType(4977.toShort())
        }
    }
}
