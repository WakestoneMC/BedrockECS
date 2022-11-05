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
public data class SculkBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): SculkBlockType {
        val e = SculkBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: SculkBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:sculk"

        public override val itemID: ItemID = ItemID(-458)

        private lateinit var instance6749: SculkBlockType

        init {
            init0()
        }

        public override val primary: SculkBlockType = instance6749

        public override val allInstances: List<SculkBlockType> = listOf(instance6749)

        public fun init0() {
            instance6749 = SculkBlockType(6749.toShort())
        }
    }
}