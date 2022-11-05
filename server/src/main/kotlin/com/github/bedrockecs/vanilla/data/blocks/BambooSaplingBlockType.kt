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
public data class BambooSaplingBlockType private constructor(
    public override val runtimeID: Short,
    public val ageBit: Byte,
    public val saplingType: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(ageBit: Byte = this.ageBit, saplingType: String = this.saplingType):
        BambooSaplingBlockType {
        val e = BambooSaplingBlockType(0.toShort(), ageBit, saplingType)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: BambooSaplingBlockType): Boolean {
        var ret = true
        ret = ret && (this.ageBit == other.ageBit)
        ret = ret && (this.saplingType == other.saplingType)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:bamboo_sapling"

        public override val itemID: ItemID = ItemID(-164)

        private lateinit var instance189: BambooSaplingBlockType

        private lateinit var instance190: BambooSaplingBlockType

        private lateinit var instance191: BambooSaplingBlockType

        private lateinit var instance192: BambooSaplingBlockType

        private lateinit var instance193: BambooSaplingBlockType

        private lateinit var instance194: BambooSaplingBlockType

        private lateinit var instance195: BambooSaplingBlockType

        private lateinit var instance196: BambooSaplingBlockType

        private lateinit var instance197: BambooSaplingBlockType

        private lateinit var instance198: BambooSaplingBlockType

        private lateinit var instance199: BambooSaplingBlockType

        private lateinit var instance200: BambooSaplingBlockType

        init {
            init0()
        }

        public override val primary: BambooSaplingBlockType = instance189

        public override val allInstances: List<BambooSaplingBlockType> =
            listOf(instance189, instance190, instance191, instance192, instance193, instance194, instance195, instance196, instance197, instance198, instance199, instance200)

        public fun init0() {
            instance189 = BambooSaplingBlockType(189.toShort(), 0.toByte(), "oak")
            instance190 = BambooSaplingBlockType(190.toShort(), 1.toByte(), "oak")
            instance191 = BambooSaplingBlockType(191.toShort(), 0.toByte(), "spruce")
            instance192 = BambooSaplingBlockType(192.toShort(), 1.toByte(), "spruce")
            instance193 = BambooSaplingBlockType(193.toShort(), 0.toByte(), "birch")
            instance194 = BambooSaplingBlockType(194.toShort(), 1.toByte(), "birch")
            instance195 = BambooSaplingBlockType(195.toShort(), 0.toByte(), "jungle")
            instance196 = BambooSaplingBlockType(196.toShort(), 1.toByte(), "jungle")
            instance197 = BambooSaplingBlockType(197.toShort(), 0.toByte(), "acacia")
            instance198 = BambooSaplingBlockType(198.toShort(), 1.toByte(), "acacia")
            instance199 = BambooSaplingBlockType(199.toShort(), 0.toByte(), "dark_oak")
            instance200 = BambooSaplingBlockType(200.toShort(), 1.toByte(), "dark_oak")
        }
    }
}
