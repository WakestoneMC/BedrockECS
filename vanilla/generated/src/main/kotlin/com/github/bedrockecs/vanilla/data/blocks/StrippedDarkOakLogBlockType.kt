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
public data class StrippedDarkOakLogBlockType private constructor(
    public override val runtimeID: Short,
    public val pillarAxis: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(pillarAxis: String = this.pillarAxis): StrippedDarkOakLogBlockType {
        val e = StrippedDarkOakLogBlockType(0.toShort(), pillarAxis)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: StrippedDarkOakLogBlockType): Boolean {
        var ret = true
        ret = ret && (this.pillarAxis == other.pillarAxis)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:stripped_dark_oak_log"

        public override val itemID: ItemID = ItemID(-9)

        private lateinit var instance7310: StrippedDarkOakLogBlockType

        private lateinit var instance7311: StrippedDarkOakLogBlockType

        private lateinit var instance7312: StrippedDarkOakLogBlockType

        init {
            init0()
        }

        public override val primary: StrippedDarkOakLogBlockType = instance7310

        public override val allInstances: List<StrippedDarkOakLogBlockType> =
            listOf(instance7310, instance7311, instance7312)

        public fun init0() {
            instance7310 = StrippedDarkOakLogBlockType(7310.toShort(), "y")
            instance7311 = StrippedDarkOakLogBlockType(7311.toShort(), "x")
            instance7312 = StrippedDarkOakLogBlockType(7312.toShort(), "z")
        }
    }
}