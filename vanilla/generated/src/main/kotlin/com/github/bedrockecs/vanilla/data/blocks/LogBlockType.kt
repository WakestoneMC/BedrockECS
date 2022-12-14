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
public data class LogBlockType private constructor(
    public override val runtimeID: Short,
    public val oldLogType: String,
    public val pillarAxis: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(oldLogType: String = this.oldLogType, pillarAxis: String = this.pillarAxis):
        LogBlockType {
        val e = LogBlockType(0.toShort(), oldLogType, pillarAxis)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: LogBlockType): Boolean {
        var ret = true
        ret = ret && (this.oldLogType == other.oldLogType)
        ret = ret && (this.pillarAxis == other.pillarAxis)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:log"

        public override val itemID: ItemID = ItemID(17)

        private lateinit var instance5565: LogBlockType

        private lateinit var instance5566: LogBlockType

        private lateinit var instance5567: LogBlockType

        private lateinit var instance5568: LogBlockType

        private lateinit var instance5569: LogBlockType

        private lateinit var instance5570: LogBlockType

        private lateinit var instance5571: LogBlockType

        private lateinit var instance5572: LogBlockType

        private lateinit var instance5573: LogBlockType

        private lateinit var instance5574: LogBlockType

        private lateinit var instance5575: LogBlockType

        private lateinit var instance5576: LogBlockType

        init {
            init0()
        }

        public override val primary: LogBlockType = instance5565

        public override val allInstances: List<LogBlockType> =
            listOf(instance5565, instance5566, instance5567, instance5568, instance5569, instance5570, instance5571, instance5572, instance5573, instance5574, instance5575, instance5576)

        public fun init0() {
            instance5565 = LogBlockType(5565.toShort(), "oak", "y")
            instance5566 = LogBlockType(5566.toShort(), "spruce", "y")
            instance5567 = LogBlockType(5567.toShort(), "birch", "y")
            instance5568 = LogBlockType(5568.toShort(), "jungle", "y")
            instance5569 = LogBlockType(5569.toShort(), "oak", "x")
            instance5570 = LogBlockType(5570.toShort(), "spruce", "x")
            instance5571 = LogBlockType(5571.toShort(), "birch", "x")
            instance5572 = LogBlockType(5572.toShort(), "jungle", "x")
            instance5573 = LogBlockType(5573.toShort(), "oak", "z")
            instance5574 = LogBlockType(5574.toShort(), "spruce", "z")
            instance5575 = LogBlockType(5575.toShort(), "birch", "z")
            instance5576 = LogBlockType(5576.toShort(), "jungle", "z")
        }
    }
}
