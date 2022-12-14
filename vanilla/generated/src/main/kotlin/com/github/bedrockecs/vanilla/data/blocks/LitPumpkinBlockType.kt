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
public data class LitPumpkinBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(direction: Int = this.direction): LitPumpkinBlockType {
        val e = LitPumpkinBlockType(0.toShort(), direction)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: LitPumpkinBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:lit_pumpkin"

        public override val itemID: ItemID = ItemID(91)

        private lateinit var instance5552: LitPumpkinBlockType

        private lateinit var instance5553: LitPumpkinBlockType

        private lateinit var instance5554: LitPumpkinBlockType

        private lateinit var instance5555: LitPumpkinBlockType

        init {
            init0()
        }

        public override val primary: LitPumpkinBlockType = instance5552

        public override val allInstances: List<LitPumpkinBlockType> =
            listOf(instance5552, instance5553, instance5554, instance5555)

        public fun init0() {
            instance5552 = LitPumpkinBlockType(5552.toShort(), 0)
            instance5553 = LitPumpkinBlockType(5553.toShort(), 1)
            instance5554 = LitPumpkinBlockType(5554.toShort(), 2)
            instance5555 = LitPumpkinBlockType(5555.toShort(), 3)
        }
    }
}
