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
public data class GlowingobsidianBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): GlowingobsidianBlockType {
        val e = GlowingobsidianBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: GlowingobsidianBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:glowingobsidian"

        public override val itemID: ItemID = ItemID(246)

        private lateinit var instance4974: GlowingobsidianBlockType

        init {
            init0()
        }

        public override val primary: GlowingobsidianBlockType = instance4974

        public override val allInstances: List<GlowingobsidianBlockType> = listOf(instance4974)

        public fun init0() {
            instance4974 = GlowingobsidianBlockType(4974.toShort())
        }
    }
}
