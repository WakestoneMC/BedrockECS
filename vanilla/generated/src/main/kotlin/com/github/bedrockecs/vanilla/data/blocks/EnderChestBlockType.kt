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
public data class EnderChestBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): EnderChestBlockType {
        val e = EnderChestBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: EnderChestBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:ender_chest"

        public override val itemID: ItemID = ItemID(130)

        private lateinit var instance4746: EnderChestBlockType

        private lateinit var instance4747: EnderChestBlockType

        private lateinit var instance4748: EnderChestBlockType

        private lateinit var instance4749: EnderChestBlockType

        private lateinit var instance4750: EnderChestBlockType

        private lateinit var instance4751: EnderChestBlockType

        init {
            init0()
        }

        public override val primary: EnderChestBlockType = instance4746

        public override val allInstances: List<EnderChestBlockType> =
            listOf(instance4746, instance4747, instance4748, instance4749, instance4750, instance4751)

        public fun init0() {
            instance4746 = EnderChestBlockType(4746.toShort(), 0)
            instance4747 = EnderChestBlockType(4747.toShort(), 1)
            instance4748 = EnderChestBlockType(4748.toShort(), 2)
            instance4749 = EnderChestBlockType(4749.toShort(), 3)
            instance4750 = EnderChestBlockType(4750.toShort(), 4)
            instance4751 = EnderChestBlockType(4751.toShort(), 5)
        }
    }
}
