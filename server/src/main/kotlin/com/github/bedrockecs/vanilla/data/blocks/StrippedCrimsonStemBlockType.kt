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
public data class StrippedCrimsonStemBlockType private constructor(
    public override val runtimeID: Short,
    public val pillarAxis: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(pillarAxis: String = this.pillarAxis): StrippedCrimsonStemBlockType {
        val e = StrippedCrimsonStemBlockType(0.toShort(), pillarAxis)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: StrippedCrimsonStemBlockType): Boolean {
        var ret = true
        ret = ret && (this.pillarAxis == other.pillarAxis)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:stripped_crimson_stem"

        public override val itemID: ItemID = ItemID(-240)

        private lateinit var instance7307: StrippedCrimsonStemBlockType

        private lateinit var instance7308: StrippedCrimsonStemBlockType

        private lateinit var instance7309: StrippedCrimsonStemBlockType

        init {
            init0()
        }

        public override val primary: StrippedCrimsonStemBlockType = instance7307

        public override val allInstances: List<StrippedCrimsonStemBlockType> =
            listOf(instance7307, instance7308, instance7309)

        public fun init0() {
            instance7307 = StrippedCrimsonStemBlockType(7307.toShort(), "y")
            instance7308 = StrippedCrimsonStemBlockType(7308.toShort(), "x")
            instance7309 = StrippedCrimsonStemBlockType(7309.toShort(), "z")
        }
    }
}
