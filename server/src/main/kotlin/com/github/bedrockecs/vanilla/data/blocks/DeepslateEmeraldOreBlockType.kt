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
public data class DeepslateEmeraldOreBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): DeepslateEmeraldOreBlockType {
        val e = DeepslateEmeraldOreBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: DeepslateEmeraldOreBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:deepslate_emerald_ore"

        public override val itemID: ItemID = ItemID(-407)

        private lateinit var instance4281: DeepslateEmeraldOreBlockType

        init {
            init0()
        }

        public override val primary: DeepslateEmeraldOreBlockType = instance4281

        public override val allInstances: List<DeepslateEmeraldOreBlockType> = listOf(instance4281)

        public fun init0() {
            instance4281 = DeepslateEmeraldOreBlockType(4281.toShort())
        }
    }
}
