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
public data class ChorusPlantBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): ChorusPlantBlockType {
        val e = ChorusPlantBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: ChorusPlantBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:chorus_plant"

        public override val itemID: ItemID = ItemID(240)

        private lateinit var instance1138: ChorusPlantBlockType

        init {
            init0()
        }

        public override val primary: ChorusPlantBlockType = instance1138

        public override val allInstances: List<ChorusPlantBlockType> = listOf(instance1138)

        public fun init0() {
            instance1138 = ChorusPlantBlockType(1138.toShort())
        }
    }
}
