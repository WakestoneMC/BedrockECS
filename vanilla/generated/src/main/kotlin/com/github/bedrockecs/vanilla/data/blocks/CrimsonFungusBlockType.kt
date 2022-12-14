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
public data class CrimsonFungusBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): CrimsonFungusBlockType {
        val e = CrimsonFungusBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CrimsonFungusBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:crimson_fungus"

        public override val itemID: ItemID = ItemID(-228)

        private lateinit var instance3835: CrimsonFungusBlockType

        init {
            init0()
        }

        public override val primary: CrimsonFungusBlockType = instance3835

        public override val allInstances: List<CrimsonFungusBlockType> = listOf(instance3835)

        public fun init0() {
            instance3835 = CrimsonFungusBlockType(3835.toShort())
        }
    }
}
