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
public data class EmeraldBlockBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): EmeraldBlockBlockType {
        val e = EmeraldBlockBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: EmeraldBlockBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:emerald_block"

        public override val itemID: ItemID = ItemID(133)

        private lateinit var instance4717: EmeraldBlockBlockType

        init {
            init0()
        }

        public override val primary: EmeraldBlockBlockType = instance4717

        public override val allInstances: List<EmeraldBlockBlockType> = listOf(instance4717)

        public fun init0() {
            instance4717 = EmeraldBlockBlockType(4717.toShort())
        }
    }
}
