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
public data class AirBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): AirBlockType {
        val e = AirBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: AirBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:air"

        public override val itemID: ItemID = ItemID(-158)

        private lateinit var instance134: AirBlockType

        init {
            init0()
        }

        public override val primary: AirBlockType = instance134

        public override val allInstances: List<AirBlockType> = listOf(instance134)

        public fun init0() {
            instance134 = AirBlockType(134.toShort())
        }
    }
}
