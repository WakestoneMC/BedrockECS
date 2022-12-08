// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Int
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class NetherWartBlockType private constructor(
    public override val runtimeID: Short,
    public val age: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(age: Int = this.age): NetherWartBlockType {
        val e = NetherWartBlockType(0.toShort(), age)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: NetherWartBlockType): Boolean {
        var ret = true
        ret = ret && (this.age == other.age)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:nether_wart"

        public override val itemID: ItemID = ItemID(115)

        private lateinit var instance5701: NetherWartBlockType

        private lateinit var instance5702: NetherWartBlockType

        private lateinit var instance5703: NetherWartBlockType

        private lateinit var instance5704: NetherWartBlockType

        init {
            init0()
        }

        public override val primary: NetherWartBlockType = instance5701

        public override val allInstances: List<NetherWartBlockType> =
            listOf(instance5701, instance5702, instance5703, instance5704)

        public fun init0() {
            instance5701 = NetherWartBlockType(5701.toShort(), 0)
            instance5702 = NetherWartBlockType(5702.toShort(), 1)
            instance5703 = NetherWartBlockType(5703.toShort(), 2)
            instance5704 = NetherWartBlockType(5704.toShort(), 3)
        }
    }
}