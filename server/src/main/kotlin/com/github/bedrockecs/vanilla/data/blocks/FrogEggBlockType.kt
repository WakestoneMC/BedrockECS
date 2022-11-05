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
public data class FrogEggBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): FrogEggBlockType {
        val e = FrogEggBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: FrogEggBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:frog_egg"

        public override val itemID: ItemID = ItemID(-468)

        private lateinit var instance4872: FrogEggBlockType

        init {
            init0()
        }

        public override val primary: FrogEggBlockType = instance4872

        public override val allInstances: List<FrogEggBlockType> = listOf(instance4872)

        public fun init0() {
            instance4872 = FrogEggBlockType(4872.toShort())
        }
    }
}