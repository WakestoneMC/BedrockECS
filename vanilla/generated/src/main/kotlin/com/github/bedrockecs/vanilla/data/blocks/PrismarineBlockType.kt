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
public data class PrismarineBlockType private constructor(
    public override val runtimeID: Short,
    public val prismarineBlockType: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(prismarineBlockType: String = this.prismarineBlockType): PrismarineBlockType {
        val e = PrismarineBlockType(0.toShort(), prismarineBlockType)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PrismarineBlockType): Boolean {
        var ret = true
        ret = ret && (this.prismarineBlockType == other.prismarineBlockType)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:prismarine"

        public override val itemID: ItemID = ItemID(168)

        private lateinit var instance6441: PrismarineBlockType

        private lateinit var instance6442: PrismarineBlockType

        private lateinit var instance6443: PrismarineBlockType

        init {
            init0()
        }

        public override val primary: PrismarineBlockType = instance6441

        public override val allInstances: List<PrismarineBlockType> =
            listOf(instance6441, instance6442, instance6443)

        public fun init0() {
            instance6441 = PrismarineBlockType(6441.toShort(), "default")
            instance6442 = PrismarineBlockType(6442.toShort(), "dark")
            instance6443 = PrismarineBlockType(6443.toShort(), "bricks")
        }
    }
}
