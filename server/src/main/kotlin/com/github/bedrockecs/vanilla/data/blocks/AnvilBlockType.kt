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
public data class AnvilBlockType private constructor(
    public override val runtimeID: Short,
    public val damage: String,
    public val direction: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(damage: String = this.damage, direction: Int = this.direction): AnvilBlockType {
        val e = AnvilBlockType(0.toShort(), damage, direction)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: AnvilBlockType): Boolean {
        var ret = true
        ret = ret && (this.damage == other.damage)
        ret = ret && (this.direction == other.direction)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:anvil"

        public override val itemID: ItemID = ItemID(145)

        private lateinit var instance152: AnvilBlockType

        private lateinit var instance153: AnvilBlockType

        private lateinit var instance154: AnvilBlockType

        private lateinit var instance155: AnvilBlockType

        private lateinit var instance156: AnvilBlockType

        private lateinit var instance157: AnvilBlockType

        private lateinit var instance158: AnvilBlockType

        private lateinit var instance159: AnvilBlockType

        private lateinit var instance160: AnvilBlockType

        private lateinit var instance161: AnvilBlockType

        private lateinit var instance162: AnvilBlockType

        private lateinit var instance163: AnvilBlockType

        private lateinit var instance164: AnvilBlockType

        private lateinit var instance165: AnvilBlockType

        private lateinit var instance166: AnvilBlockType

        private lateinit var instance167: AnvilBlockType

        init {
            init0()
        }

        public override val primary: AnvilBlockType = instance152

        public override val allInstances: List<AnvilBlockType> =
            listOf(instance152, instance153, instance154, instance155, instance156, instance157, instance158, instance159, instance160, instance161, instance162, instance163, instance164, instance165, instance166, instance167)

        public fun init0() {
            instance152 = AnvilBlockType(152.toShort(), "undamaged", 0)
            instance153 = AnvilBlockType(153.toShort(), "undamaged", 1)
            instance154 = AnvilBlockType(154.toShort(), "undamaged", 2)
            instance155 = AnvilBlockType(155.toShort(), "undamaged", 3)
            instance156 = AnvilBlockType(156.toShort(), "slightly_damaged", 0)
            instance157 = AnvilBlockType(157.toShort(), "slightly_damaged", 1)
            instance158 = AnvilBlockType(158.toShort(), "slightly_damaged", 2)
            instance159 = AnvilBlockType(159.toShort(), "slightly_damaged", 3)
            instance160 = AnvilBlockType(160.toShort(), "very_damaged", 0)
            instance161 = AnvilBlockType(161.toShort(), "very_damaged", 1)
            instance162 = AnvilBlockType(162.toShort(), "very_damaged", 2)
            instance163 = AnvilBlockType(163.toShort(), "very_damaged", 3)
            instance164 = AnvilBlockType(164.toShort(), "broken", 0)
            instance165 = AnvilBlockType(165.toShort(), "broken", 1)
            instance166 = AnvilBlockType(166.toShort(), "broken", 2)
            instance167 = AnvilBlockType(167.toShort(), "broken", 3)
        }
    }
}