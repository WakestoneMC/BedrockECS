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
public data class PolishedBlackstoneBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): PolishedBlackstoneBlockType {
        val e = PolishedBlackstoneBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PolishedBlackstoneBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:polished_blackstone"

        public override val itemID: ItemID = ItemID(-291)

        private lateinit var instance5828: PolishedBlackstoneBlockType

        init {
            init0()
        }

        public override val primary: PolishedBlackstoneBlockType = instance5828

        public override val allInstances: List<PolishedBlackstoneBlockType> = listOf(instance5828)

        public fun init0() {
            instance5828 = PolishedBlackstoneBlockType(5828.toShort())
        }
    }
}