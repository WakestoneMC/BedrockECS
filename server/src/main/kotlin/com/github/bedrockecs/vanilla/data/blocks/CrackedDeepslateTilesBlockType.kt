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
public data class CrackedDeepslateTilesBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): CrackedDeepslateTilesBlockType {
        val e = CrackedDeepslateTilesBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CrackedDeepslateTilesBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:cracked_deepslate_tiles"

        public override val itemID: ItemID = ItemID(-409)

        private lateinit var instance3768: CrackedDeepslateTilesBlockType

        init {
            init0()
        }

        public override val primary: CrackedDeepslateTilesBlockType = instance3768

        public override val allInstances: List<CrackedDeepslateTilesBlockType> = listOf(instance3768)

        public fun init0() {
            instance3768 = CrackedDeepslateTilesBlockType(3768.toShort())
        }
    }
}
