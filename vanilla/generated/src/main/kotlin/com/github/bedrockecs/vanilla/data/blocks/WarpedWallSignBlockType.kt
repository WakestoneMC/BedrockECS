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
public data class WarpedWallSignBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): WarpedWallSignBlockType {
        val e = WarpedWallSignBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WarpedWallSignBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:warped_wall_sign"

        public override val itemID: ItemID = ItemID(-253)

        private lateinit var instance7659: WarpedWallSignBlockType

        private lateinit var instance7660: WarpedWallSignBlockType

        private lateinit var instance7661: WarpedWallSignBlockType

        private lateinit var instance7662: WarpedWallSignBlockType

        private lateinit var instance7663: WarpedWallSignBlockType

        private lateinit var instance7664: WarpedWallSignBlockType

        init {
            init0()
        }

        public override val primary: WarpedWallSignBlockType = instance7659

        public override val allInstances: List<WarpedWallSignBlockType> =
            listOf(instance7659, instance7660, instance7661, instance7662, instance7663, instance7664)

        public fun init0() {
            instance7659 = WarpedWallSignBlockType(7659.toShort(), 0)
            instance7660 = WarpedWallSignBlockType(7660.toShort(), 1)
            instance7661 = WarpedWallSignBlockType(7661.toShort(), 2)
            instance7662 = WarpedWallSignBlockType(7662.toShort(), 3)
            instance7663 = WarpedWallSignBlockType(7663.toShort(), 4)
            instance7664 = WarpedWallSignBlockType(7664.toShort(), 5)
        }
    }
}
