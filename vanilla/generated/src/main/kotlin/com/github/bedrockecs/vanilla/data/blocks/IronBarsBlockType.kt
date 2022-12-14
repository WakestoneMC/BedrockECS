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
public data class IronBarsBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): IronBarsBlockType {
        val e = IronBarsBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: IronBarsBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:iron_bars"

        public override val itemID: ItemID = ItemID(101)

        private lateinit var instance5134: IronBarsBlockType

        init {
            init0()
        }

        public override val primary: IronBarsBlockType = instance5134

        public override val allInstances: List<IronBarsBlockType> = listOf(instance5134)

        public fun init0() {
            instance5134 = IronBarsBlockType(5134.toShort())
        }
    }
}
