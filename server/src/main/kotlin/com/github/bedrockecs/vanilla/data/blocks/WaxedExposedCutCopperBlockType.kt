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
public data class WaxedExposedCutCopperBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): WaxedExposedCutCopperBlockType {
        val e = WaxedExposedCutCopperBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WaxedExposedCutCopperBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:waxed_exposed_cut_copper"

        public override val itemID: ItemID = ItemID(-352)

        private lateinit var instance7698: WaxedExposedCutCopperBlockType

        init {
            init0()
        }

        public override val primary: WaxedExposedCutCopperBlockType = instance7698

        public override val allInstances: List<WaxedExposedCutCopperBlockType> = listOf(instance7698)

        public fun init0() {
            instance7698 = WaxedExposedCutCopperBlockType(7698.toShort())
        }
    }
}
