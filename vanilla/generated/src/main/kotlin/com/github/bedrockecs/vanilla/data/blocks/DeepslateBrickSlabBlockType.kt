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
public data class DeepslateBrickSlabBlockType private constructor(
    public override val runtimeID: Short,
    public val topSlotBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(topSlotBit: Byte = this.topSlotBit): DeepslateBrickSlabBlockType {
        val e = DeepslateBrickSlabBlockType(0.toShort(), topSlotBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: DeepslateBrickSlabBlockType): Boolean {
        var ret = true
        ret = ret && (this.topSlotBit == other.topSlotBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:deepslate_brick_slab"

        public override val itemID: ItemID = ItemID(-392)

        private lateinit var instance4105: DeepslateBrickSlabBlockType

        private lateinit var instance4106: DeepslateBrickSlabBlockType

        init {
            init0()
        }

        public override val primary: DeepslateBrickSlabBlockType = instance4105

        public override val allInstances: List<DeepslateBrickSlabBlockType> =
            listOf(instance4105, instance4106)

        public fun init0() {
            instance4105 = DeepslateBrickSlabBlockType(4105.toShort(), 0.toByte())
            instance4106 = DeepslateBrickSlabBlockType(4106.toShort(), 1.toByte())
        }
    }
}