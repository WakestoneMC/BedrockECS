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
public data class SaplingBlockType private constructor(
    public override val runtimeID: Short,
    public val ageBit: Byte,
    public val saplingType: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(ageBit: Byte = this.ageBit, saplingType: String = this.saplingType):
        SaplingBlockType {
        val e = SaplingBlockType(0.toShort(), ageBit, saplingType)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: SaplingBlockType): Boolean {
        var ret = true
        ret = ret && (this.ageBit == other.ageBit)
        ret = ret && (this.saplingType == other.saplingType)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:sapling"

        public override val itemID: ItemID = ItemID(6)

        private lateinit var instance6721: SaplingBlockType

        private lateinit var instance6722: SaplingBlockType

        private lateinit var instance6723: SaplingBlockType

        private lateinit var instance6724: SaplingBlockType

        private lateinit var instance6725: SaplingBlockType

        private lateinit var instance6726: SaplingBlockType

        private lateinit var instance6727: SaplingBlockType

        private lateinit var instance6728: SaplingBlockType

        private lateinit var instance6729: SaplingBlockType

        private lateinit var instance6730: SaplingBlockType

        private lateinit var instance6731: SaplingBlockType

        private lateinit var instance6732: SaplingBlockType

        init {
            init0()
        }

        public override val primary: SaplingBlockType = instance6721

        public override val allInstances: List<SaplingBlockType> =
            listOf(instance6721, instance6722, instance6723, instance6724, instance6725, instance6726, instance6727, instance6728, instance6729, instance6730, instance6731, instance6732)

        public fun init0() {
            instance6721 = SaplingBlockType(6721.toShort(), 0.toByte(), "oak")
            instance6722 = SaplingBlockType(6722.toShort(), 0.toByte(), "spruce")
            instance6723 = SaplingBlockType(6723.toShort(), 0.toByte(), "birch")
            instance6724 = SaplingBlockType(6724.toShort(), 0.toByte(), "jungle")
            instance6725 = SaplingBlockType(6725.toShort(), 0.toByte(), "acacia")
            instance6726 = SaplingBlockType(6726.toShort(), 0.toByte(), "dark_oak")
            instance6727 = SaplingBlockType(6727.toShort(), 1.toByte(), "oak")
            instance6728 = SaplingBlockType(6728.toShort(), 1.toByte(), "spruce")
            instance6729 = SaplingBlockType(6729.toShort(), 1.toByte(), "birch")
            instance6730 = SaplingBlockType(6730.toShort(), 1.toByte(), "jungle")
            instance6731 = SaplingBlockType(6731.toShort(), 1.toByte(), "acacia")
            instance6732 = SaplingBlockType(6732.toShort(), 1.toByte(), "dark_oak")
        }
    }
}
