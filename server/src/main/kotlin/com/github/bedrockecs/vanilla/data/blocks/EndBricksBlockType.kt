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
public data class EndBricksBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): EndBricksBlockType {
        val e = EndBricksBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: EndBricksBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:end_bricks"

        public override val itemID: ItemID = ItemID(206)

        private lateinit var instance4728: EndBricksBlockType

        init {
            init0()
        }

        public override val primary: EndBricksBlockType = instance4728

        public override val allInstances: List<EndBricksBlockType> = listOf(instance4728)

        public fun init0() {
            instance4728 = EndBricksBlockType(4728.toShort())
        }
    }
}
