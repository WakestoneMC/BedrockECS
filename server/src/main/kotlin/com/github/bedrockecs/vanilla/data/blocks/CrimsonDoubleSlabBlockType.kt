// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Byte
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class CrimsonDoubleSlabBlockType private constructor(
    public override val runtimeID: Short,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(topSlotBit: Byte = this.topSlotBit): CrimsonDoubleSlabBlockType {
        val e = CrimsonDoubleSlabBlockType(0.toShort(), topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CrimsonDoubleSlabBlockType): Boolean {
        var ret = true
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:crimson_double_slab"

        public override val itemID: ItemID = ItemID(-266)

        private lateinit var instance3816: CrimsonDoubleSlabBlockType

        private lateinit var instance3817: CrimsonDoubleSlabBlockType

        init {
            init0()
        }

        public override val primary: CrimsonDoubleSlabBlockType = instance3816

        public override val allInstances: List<CrimsonDoubleSlabBlockType> =
            listOf(instance3816, instance3817)

        public fun init0() {
            instance3816 = CrimsonDoubleSlabBlockType(3816.toShort(), 0.toByte())
            instance3817 = CrimsonDoubleSlabBlockType(3817.toShort(), 1.toByte())
        }
    }
}
