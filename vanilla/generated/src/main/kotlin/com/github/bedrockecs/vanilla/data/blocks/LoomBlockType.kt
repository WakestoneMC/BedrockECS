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
public data class LoomBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(direction: Int = this.direction): LoomBlockType {
        val e = LoomBlockType(0.toShort(), direction)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: LoomBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:loom"

        public override val itemID: ItemID = ItemID(-204)

        private lateinit var instance5583: LoomBlockType

        private lateinit var instance5584: LoomBlockType

        private lateinit var instance5585: LoomBlockType

        private lateinit var instance5586: LoomBlockType

        init {
            init0()
        }

        public override val primary: LoomBlockType = instance5583

        public override val allInstances: List<LoomBlockType> =
            listOf(instance5583, instance5584, instance5585, instance5586)

        public fun init0() {
            instance5583 = LoomBlockType(5583.toShort(), 0)
            instance5584 = LoomBlockType(5584.toShort(), 1)
            instance5585 = LoomBlockType(5585.toShort(), 2)
            instance5586 = LoomBlockType(5586.toShort(), 3)
        }
    }
}
