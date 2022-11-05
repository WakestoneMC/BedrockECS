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
public data class UnknownBlockType private constructor(
    public override val runtimeID: Short
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(): UnknownBlockType {
        val e = UnknownBlockType(0.toShort())
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: UnknownBlockType): Boolean {
        var ret = true
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:unknown"

        public override val itemID: ItemID = ItemID(-305)

        private lateinit var instance7460: UnknownBlockType

        init {
            init0()
        }

        public override val primary: UnknownBlockType = instance7460

        public override val allInstances: List<UnknownBlockType> = listOf(instance7460)

        public fun init0() {
            instance7460 = UnknownBlockType(7460.toShort())
        }
    }
}