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
public data class NetherBrickBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): NetherBrickBlockType {
        val e = NetherBrickBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: NetherBrickBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:nether_brick"

        public override val itemID: ItemID = ItemID(112)

        private lateinit var instance5689: NetherBrickBlockType

        init {
            init0()
        }

        public override val primary: NetherBrickBlockType = instance5689

        public override val allInstances: List<NetherBrickBlockType> = listOf(instance5689)

        public fun init0() {
            instance5689 = NetherBrickBlockType(5689.toShort())
        }
    }
}
