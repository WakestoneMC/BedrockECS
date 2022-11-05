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
public data class WarpedNyliumBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): WarpedNyliumBlockType {
        val e = WarpedNyliumBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WarpedNyliumBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:warped_nylium"

        public override val itemID: ItemID = ItemID(-233)

        private lateinit var instance7595: WarpedNyliumBlockType

        init {
            init0()
        }

        public override val primary: WarpedNyliumBlockType = instance7595

        public override val allInstances: List<WarpedNyliumBlockType> = listOf(instance7595)

        public fun init0() {
            instance7595 = WarpedNyliumBlockType(7595.toShort())
        }
    }
}
