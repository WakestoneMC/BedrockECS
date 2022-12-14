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
public data class DeepslateGoldOreBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): DeepslateGoldOreBlockType {
        val e = DeepslateGoldOreBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: DeepslateGoldOreBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:deepslate_gold_ore"

        public override val itemID: ItemID = ItemID(-402)

        private lateinit var instance4282: DeepslateGoldOreBlockType

        init {
            init0()
        }

        public override val primary: DeepslateGoldOreBlockType = instance4282

        public override val allInstances: List<DeepslateGoldOreBlockType> = listOf(instance4282)

        public fun init0() {
            instance4282 = DeepslateGoldOreBlockType(4282.toShort())
        }
    }
}
