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
public data class StrippedOakLogBlockType private constructor(
    public override val runtimeID: Short,
    public val pillarAxis: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(pillarAxis: String = this.pillarAxis): StrippedOakLogBlockType {
        val e = StrippedOakLogBlockType(0.toShort(), pillarAxis)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: StrippedOakLogBlockType): Boolean {
        var ret = true
        ret = ret && (this.pillarAxis == other.pillarAxis)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:stripped_oak_log"

        public override val itemID: ItemID = ItemID(-10)

        private lateinit var instance7316: StrippedOakLogBlockType

        private lateinit var instance7317: StrippedOakLogBlockType

        private lateinit var instance7318: StrippedOakLogBlockType

        init {
            init0()
        }

        public override val primary: StrippedOakLogBlockType = instance7316

        public override val allInstances: List<StrippedOakLogBlockType> =
            listOf(instance7316, instance7317, instance7318)

        public fun init0() {
            instance7316 = StrippedOakLogBlockType(7316.toShort(), "y")
            instance7317 = StrippedOakLogBlockType(7317.toShort(), "x")
            instance7318 = StrippedOakLogBlockType(7318.toShort(), "z")
        }
    }
}
