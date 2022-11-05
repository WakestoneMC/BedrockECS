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
public data class StonecutterBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): StonecutterBlockType {
        val e = StonecutterBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: StonecutterBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:stonecutter"

        public override val itemID: ItemID = ItemID(245)

        private lateinit var instance7291: StonecutterBlockType

        init {
            init0()
        }

        public override val primary: StonecutterBlockType = instance7291

        public override val allInstances: List<StonecutterBlockType> = listOf(instance7291)

        public fun init0() {
            instance7291 = StonecutterBlockType(7291.toShort())
        }
    }
}