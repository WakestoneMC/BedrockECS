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
public data class CrimsonStemBlockType private constructor(
    public override val runtimeID: Short,
    public val pillarAxis: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(pillarAxis: String = this.pillarAxis): CrimsonStemBlockType {
        val e = CrimsonStemBlockType(0.toShort(), pillarAxis)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CrimsonStemBlockType): Boolean {
        var ret = true
        ret = ret && (this.pillarAxis == other.pillarAxis)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:crimson_stem"

        public override val itemID: ItemID = ItemID(-225)

        private lateinit var instance3884: CrimsonStemBlockType

        private lateinit var instance3885: CrimsonStemBlockType

        private lateinit var instance3886: CrimsonStemBlockType

        init {
            init0()
        }

        public override val primary: CrimsonStemBlockType = instance3884

        public override val allInstances: List<CrimsonStemBlockType> =
            listOf(instance3884, instance3885, instance3886)

        public fun init0() {
            instance3884 = CrimsonStemBlockType(3884.toShort(), "y")
            instance3885 = CrimsonStemBlockType(3885.toShort(), "x")
            instance3886 = CrimsonStemBlockType(3886.toShort(), "z")
        }
    }
}
