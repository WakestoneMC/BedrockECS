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
public data class CryingObsidianBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): CryingObsidianBlockType {
        val e = CryingObsidianBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CryingObsidianBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:crying_obsidian"

        public override val itemID: ItemID = ItemID(-289)

        private lateinit var instance3909: CryingObsidianBlockType

        init {
            init0()
        }

        public override val primary: CryingObsidianBlockType = instance3909

        public override val allInstances: List<CryingObsidianBlockType> = listOf(instance3909)

        public fun init0() {
            instance3909 = CryingObsidianBlockType(3909.toShort())
        }
    }
}
