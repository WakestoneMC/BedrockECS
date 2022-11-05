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
public data class AncientDebrisBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): AncientDebrisBlockType {
        val e = AncientDebrisBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: AncientDebrisBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:ancient_debris"

        public override val itemID: ItemID = ItemID(-271)

        private lateinit var instance143: AncientDebrisBlockType

        init {
            init0()
        }

        public override val primary: AncientDebrisBlockType = instance143

        public override val allInstances: List<AncientDebrisBlockType> = listOf(instance143)

        public fun init0() {
            instance143 = AncientDebrisBlockType(143.toShort())
        }
    }
}