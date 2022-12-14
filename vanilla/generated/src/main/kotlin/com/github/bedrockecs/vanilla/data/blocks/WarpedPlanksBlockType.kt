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
public data class WarpedPlanksBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): WarpedPlanksBlockType {
        val e = WarpedPlanksBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WarpedPlanksBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:warped_planks"

        public override val itemID: ItemID = ItemID(-243)

        private lateinit var instance7596: WarpedPlanksBlockType

        init {
            init0()
        }

        public override val primary: WarpedPlanksBlockType = instance7596

        public override val allInstances: List<WarpedPlanksBlockType> = listOf(instance7596)

        public fun init0() {
            instance7596 = WarpedPlanksBlockType(7596.toShort())
        }
    }
}
