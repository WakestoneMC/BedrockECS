// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Byte
import kotlin.Int
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class GlowFrameBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int,
    public val itemFrameMapBit: Byte,
    public val itemFramePhotoBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(
        facingDirection: Int = this.facingDirection,
        itemFrameMapBit: Byte = this.itemFrameMapBit,
        itemFramePhotoBit: Byte = this.itemFramePhotoBit
    ): GlowFrameBlockType {
        val e = GlowFrameBlockType(0.toShort(), facingDirection, itemFrameMapBit, itemFramePhotoBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: GlowFrameBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        ret = ret && (this.itemFrameMapBit == other.itemFrameMapBit)
        ret = ret && (this.itemFramePhotoBit == other.itemFramePhotoBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:glow_frame"

        public override val itemID: ItemID = ItemID(-339)

        private lateinit var instance4886: GlowFrameBlockType

        private lateinit var instance4887: GlowFrameBlockType

        private lateinit var instance4888: GlowFrameBlockType

        private lateinit var instance4889: GlowFrameBlockType

        private lateinit var instance4890: GlowFrameBlockType

        private lateinit var instance4891: GlowFrameBlockType

        private lateinit var instance4892: GlowFrameBlockType

        private lateinit var instance4893: GlowFrameBlockType

        private lateinit var instance4894: GlowFrameBlockType

        private lateinit var instance4895: GlowFrameBlockType

        private lateinit var instance4896: GlowFrameBlockType

        private lateinit var instance4897: GlowFrameBlockType

        private lateinit var instance4898: GlowFrameBlockType

        private lateinit var instance4899: GlowFrameBlockType

        private lateinit var instance4900: GlowFrameBlockType

        private lateinit var instance4901: GlowFrameBlockType

        private lateinit var instance4902: GlowFrameBlockType

        private lateinit var instance4903: GlowFrameBlockType

        private lateinit var instance4904: GlowFrameBlockType

        private lateinit var instance4905: GlowFrameBlockType

        private lateinit var instance4906: GlowFrameBlockType

        private lateinit var instance4907: GlowFrameBlockType

        private lateinit var instance4908: GlowFrameBlockType

        private lateinit var instance4909: GlowFrameBlockType

        init {
            init0()
        }

        public override val primary: GlowFrameBlockType = instance4886

        public override val allInstances: List<GlowFrameBlockType> =
            listOf(instance4886, instance4887, instance4888, instance4889, instance4890, instance4891, instance4892, instance4893, instance4894, instance4895, instance4896, instance4897, instance4898, instance4899, instance4900, instance4901, instance4902, instance4903, instance4904, instance4905, instance4906, instance4907, instance4908, instance4909)

        public fun init0() {
            instance4886 = GlowFrameBlockType(4886.toShort(), 0, 0.toByte(), 0.toByte())
            instance4887 = GlowFrameBlockType(4887.toShort(), 1, 0.toByte(), 0.toByte())
            instance4888 = GlowFrameBlockType(4888.toShort(), 2, 0.toByte(), 0.toByte())
            instance4889 = GlowFrameBlockType(4889.toShort(), 3, 0.toByte(), 0.toByte())
            instance4890 = GlowFrameBlockType(4890.toShort(), 4, 0.toByte(), 0.toByte())
            instance4891 = GlowFrameBlockType(4891.toShort(), 5, 0.toByte(), 0.toByte())
            instance4892 = GlowFrameBlockType(4892.toShort(), 0, 1.toByte(), 0.toByte())
            instance4893 = GlowFrameBlockType(4893.toShort(), 1, 1.toByte(), 0.toByte())
            instance4894 = GlowFrameBlockType(4894.toShort(), 2, 1.toByte(), 0.toByte())
            instance4895 = GlowFrameBlockType(4895.toShort(), 3, 1.toByte(), 0.toByte())
            instance4896 = GlowFrameBlockType(4896.toShort(), 4, 1.toByte(), 0.toByte())
            instance4897 = GlowFrameBlockType(4897.toShort(), 5, 1.toByte(), 0.toByte())
            instance4898 = GlowFrameBlockType(4898.toShort(), 0, 0.toByte(), 1.toByte())
            instance4899 = GlowFrameBlockType(4899.toShort(), 1, 0.toByte(), 1.toByte())
            instance4900 = GlowFrameBlockType(4900.toShort(), 2, 0.toByte(), 1.toByte())
            instance4901 = GlowFrameBlockType(4901.toShort(), 3, 0.toByte(), 1.toByte())
            instance4902 = GlowFrameBlockType(4902.toShort(), 4, 0.toByte(), 1.toByte())
            instance4903 = GlowFrameBlockType(4903.toShort(), 5, 0.toByte(), 1.toByte())
            instance4904 = GlowFrameBlockType(4904.toShort(), 0, 1.toByte(), 1.toByte())
            instance4905 = GlowFrameBlockType(4905.toShort(), 1, 1.toByte(), 1.toByte())
            instance4906 = GlowFrameBlockType(4906.toShort(), 2, 1.toByte(), 1.toByte())
            instance4907 = GlowFrameBlockType(4907.toShort(), 3, 1.toByte(), 1.toByte())
            instance4908 = GlowFrameBlockType(4908.toShort(), 4, 1.toByte(), 1.toByte())
            instance4909 = GlowFrameBlockType(4909.toShort(), 5, 1.toByte(), 1.toByte())
        }
    }
}
