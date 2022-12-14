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
public data class Log2BlockType private constructor(
    public override val runtimeID: Short,
    public val newLogType: String,
    public val pillarAxis: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(newLogType: String = this.newLogType, pillarAxis: String = this.pillarAxis):
        Log2BlockType {
        val e = Log2BlockType(0.toShort(), newLogType, pillarAxis)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: Log2BlockType): Boolean {
        var ret = true
        ret = ret && (this.newLogType == other.newLogType)
        ret = ret && (this.pillarAxis == other.pillarAxis)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:log2"

        public override val itemID: ItemID = ItemID(162)

        private lateinit var instance5577: Log2BlockType

        private lateinit var instance5578: Log2BlockType

        private lateinit var instance5579: Log2BlockType

        private lateinit var instance5580: Log2BlockType

        private lateinit var instance5581: Log2BlockType

        private lateinit var instance5582: Log2BlockType

        init {
            init0()
        }

        public override val primary: Log2BlockType = instance5577

        public override val allInstances: List<Log2BlockType> =
            listOf(instance5577, instance5578, instance5579, instance5580, instance5581, instance5582)

        public fun init0() {
            instance5577 = Log2BlockType(5577.toShort(), "acacia", "y")
            instance5578 = Log2BlockType(5578.toShort(), "dark_oak", "y")
            instance5579 = Log2BlockType(5579.toShort(), "acacia", "x")
            instance5580 = Log2BlockType(5580.toShort(), "dark_oak", "x")
            instance5581 = Log2BlockType(5581.toShort(), "acacia", "z")
            instance5582 = Log2BlockType(5582.toShort(), "dark_oak", "z")
        }
    }
}
