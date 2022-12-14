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
public data class SeaLanternBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): SeaLanternBlockType {
        val e = SeaLanternBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: SeaLanternBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:seaLantern"

        public override val itemID: ItemID = ItemID(169)

        private lateinit var instance6831: SeaLanternBlockType

        init {
            init0()
        }

        public override val primary: SeaLanternBlockType = instance6831

        public override val allInstances: List<SeaLanternBlockType> = listOf(instance6831)

        public fun init0() {
            instance6831 = SeaLanternBlockType(6831.toShort())
        }
    }
}
