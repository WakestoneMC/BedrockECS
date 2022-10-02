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
public data class WallBannerBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): WallBannerBlockType {
        val e = WallBannerBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WallBannerBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:wall_banner"

        public override val itemID: ItemID = ItemID(177)

        private lateinit var instance7516: WallBannerBlockType

        private lateinit var instance7517: WallBannerBlockType

        private lateinit var instance7518: WallBannerBlockType

        private lateinit var instance7519: WallBannerBlockType

        private lateinit var instance7520: WallBannerBlockType

        private lateinit var instance7521: WallBannerBlockType

        init {
            init0()
        }

        public override val primary: WallBannerBlockType = instance7516

        public override val allInstances: List<WallBannerBlockType> =
            listOf(instance7516, instance7517, instance7518, instance7519, instance7520, instance7521)

        public fun init0() {
            instance7516 = WallBannerBlockType(7516.toShort(), 0)
            instance7517 = WallBannerBlockType(7517.toShort(), 1)
            instance7518 = WallBannerBlockType(7518.toShort(), 2)
            instance7519 = WallBannerBlockType(7519.toShort(), 3)
            instance7520 = WallBannerBlockType(7520.toShort(), 4)
            instance7521 = WallBannerBlockType(7521.toShort(), 5)
        }
    }
}
