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
public data class SlimeBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): SlimeBlockType {
        val e = SlimeBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: SlimeBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:slime"

        public override val itemID: ItemID = ItemID(165)

        private lateinit var instance6861: SlimeBlockType

        init {
            init0()
        }

        public override val primary: SlimeBlockType = instance6861

        public override val allInstances: List<SlimeBlockType> = listOf(instance6861)

        public fun init0() {
            instance6861 = SlimeBlockType(6861.toShort())
        }
    }
}