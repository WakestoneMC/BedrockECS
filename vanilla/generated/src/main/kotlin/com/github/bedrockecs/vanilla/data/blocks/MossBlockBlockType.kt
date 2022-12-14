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
public data class MossBlockBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): MossBlockBlockType {
        val e = MossBlockBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: MossBlockBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:moss_block"

        public override val itemID: ItemID = ItemID(-320)

        private lateinit var instance5666: MossBlockBlockType

        init {
            init0()
        }

        public override val primary: MossBlockBlockType = instance5666

        public override val allInstances: List<MossBlockBlockType> = listOf(instance5666)

        public fun init0() {
            instance5666 = MossBlockBlockType(5666.toShort())
        }
    }
}
