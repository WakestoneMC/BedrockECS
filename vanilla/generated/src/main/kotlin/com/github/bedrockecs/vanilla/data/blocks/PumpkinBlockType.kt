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
public data class PumpkinBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(direction: Int = this.direction): PumpkinBlockType {
        val e = PumpkinBlockType(0.toShort(), direction)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PumpkinBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:pumpkin"

        public override val itemID: ItemID = ItemID(86)

        private lateinit var instance6460: PumpkinBlockType

        private lateinit var instance6461: PumpkinBlockType

        private lateinit var instance6462: PumpkinBlockType

        private lateinit var instance6463: PumpkinBlockType

        init {
            init0()
        }

        public override val primary: PumpkinBlockType = instance6460

        public override val allInstances: List<PumpkinBlockType> =
            listOf(instance6460, instance6461, instance6462, instance6463)

        public fun init0() {
            instance6460 = PumpkinBlockType(6460.toShort(), 0)
            instance6461 = PumpkinBlockType(6461.toShort(), 1)
            instance6462 = PumpkinBlockType(6462.toShort(), 2)
            instance6463 = PumpkinBlockType(6463.toShort(), 3)
        }
    }
}