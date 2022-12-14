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
public data class MonsterEggBlockType private constructor(
    public override val runtimeID: Short,
    public val monsterEggStoneType: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(monsterEggStoneType: String = this.monsterEggStoneType): MonsterEggBlockType {
        val e = MonsterEggBlockType(0.toShort(), monsterEggStoneType)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: MonsterEggBlockType): Boolean {
        var ret = true
        ret = ret && (this.monsterEggStoneType == other.monsterEggStoneType)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:monster_egg"

        public override val itemID: ItemID = ItemID(97)

        private lateinit var instance5660: MonsterEggBlockType

        private lateinit var instance5661: MonsterEggBlockType

        private lateinit var instance5662: MonsterEggBlockType

        private lateinit var instance5663: MonsterEggBlockType

        private lateinit var instance5664: MonsterEggBlockType

        private lateinit var instance5665: MonsterEggBlockType

        init {
            init0()
        }

        public override val primary: MonsterEggBlockType = instance5660

        public override val allInstances: List<MonsterEggBlockType> =
            listOf(instance5660, instance5661, instance5662, instance5663, instance5664, instance5665)

        public fun init0() {
            instance5660 = MonsterEggBlockType(5660.toShort(), "stone")
            instance5661 = MonsterEggBlockType(5661.toShort(), "cobblestone")
            instance5662 = MonsterEggBlockType(5662.toShort(), "stone_brick")
            instance5663 = MonsterEggBlockType(5663.toShort(), "mossy_stone_brick")
            instance5664 = MonsterEggBlockType(5664.toShort(), "cracked_stone_brick")
            instance5665 = MonsterEggBlockType(5665.toShort(), "chiseled_stone_brick")
        }
    }
}
