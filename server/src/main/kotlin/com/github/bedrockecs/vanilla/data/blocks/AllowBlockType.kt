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
public data class AllowBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): AllowBlockType {
        val e = AllowBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: AllowBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:allow"

        public override val itemID: ItemID = ItemID(210)

        private lateinit var instance135: AllowBlockType

        init {
            init0()
        }

        public override val primary: AllowBlockType = instance135

        public override val allInstances: List<AllowBlockType> = listOf(instance135)

        public fun init0() {
            instance135 = AllowBlockType(135.toShort())
        }
    }
}
