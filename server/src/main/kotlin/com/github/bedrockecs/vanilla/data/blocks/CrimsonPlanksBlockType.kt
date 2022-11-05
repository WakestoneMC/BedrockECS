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
public data class CrimsonPlanksBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): CrimsonPlanksBlockType {
        val e = CrimsonPlanksBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CrimsonPlanksBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:crimson_planks"

        public override val itemID: ItemID = ItemID(-242)

        private lateinit var instance3840: CrimsonPlanksBlockType

        init {
            init0()
        }

        public override val primary: CrimsonPlanksBlockType = instance3840

        public override val allInstances: List<CrimsonPlanksBlockType> = listOf(instance3840)

        public fun init0() {
            instance3840 = CrimsonPlanksBlockType(3840.toShort())
        }
    }
}
