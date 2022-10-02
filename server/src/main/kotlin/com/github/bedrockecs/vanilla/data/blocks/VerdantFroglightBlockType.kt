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
public data class VerdantFroglightBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): VerdantFroglightBlockType {
        val e = VerdantFroglightBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: VerdantFroglightBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:verdant_froglight"

        public override val itemID: ItemID = ItemID(-470)

        private lateinit var instance7499: VerdantFroglightBlockType

        init {
            init0()
        }

        public override val primary: VerdantFroglightBlockType = instance7499

        public override val allInstances: List<VerdantFroglightBlockType> = listOf(instance7499)

        public fun init0() {
            instance7499 = VerdantFroglightBlockType(7499.toShort())
        }
    }
}
