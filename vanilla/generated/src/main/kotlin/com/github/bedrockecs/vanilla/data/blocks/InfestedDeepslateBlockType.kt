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
public data class InfestedDeepslateBlockType private constructor(
    public override val runtimeID: Short,
    public val pillarAxis: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(pillarAxis: String = this.pillarAxis): InfestedDeepslateBlockType {
        val e = InfestedDeepslateBlockType(0.toShort(), pillarAxis)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: InfestedDeepslateBlockType): Boolean {
        var ret = true
        ret = ret && (this.pillarAxis == other.pillarAxis)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:infested_deepslate"

        public override val itemID: ItemID = ItemID(-454)

        private lateinit var instance5128: InfestedDeepslateBlockType

        private lateinit var instance5129: InfestedDeepslateBlockType

        private lateinit var instance5130: InfestedDeepslateBlockType

        init {
            init0()
        }

        public override val primary: InfestedDeepslateBlockType = instance5128

        public override val allInstances: List<InfestedDeepslateBlockType> =
            listOf(instance5128, instance5129, instance5130)

        public fun init0() {
            instance5128 = InfestedDeepslateBlockType(5128.toShort(), "y")
            instance5129 = InfestedDeepslateBlockType(5129.toShort(), "x")
            instance5130 = InfestedDeepslateBlockType(5130.toShort(), "z")
        }
    }
}
