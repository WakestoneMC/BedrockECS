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
public data class NetherWartBlockBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): NetherWartBlockBlockType {
        val e = NetherWartBlockBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: NetherWartBlockBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:nether_wart_block"

        public override val itemID: ItemID = ItemID(214)

        private lateinit var instance5705: NetherWartBlockBlockType

        init {
            init0()
        }

        public override val primary: NetherWartBlockBlockType = instance5705

        public override val allInstances: List<NetherWartBlockBlockType> = listOf(instance5705)

        public fun init0() {
            instance5705 = NetherWartBlockBlockType(5705.toShort())
        }
    }
}