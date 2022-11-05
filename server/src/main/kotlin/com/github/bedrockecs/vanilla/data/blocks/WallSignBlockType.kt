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
public data class WallSignBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): WallSignBlockType {
        val e = WallSignBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WallSignBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:wall_sign"

        public override val itemID: ItemID = ItemID(68)

        private lateinit var instance7522: WallSignBlockType

        private lateinit var instance7523: WallSignBlockType

        private lateinit var instance7524: WallSignBlockType

        private lateinit var instance7525: WallSignBlockType

        private lateinit var instance7526: WallSignBlockType

        private lateinit var instance7527: WallSignBlockType

        init {
            init0()
        }

        public override val primary: WallSignBlockType = instance7522

        public override val allInstances: List<WallSignBlockType> =
            listOf(instance7522, instance7523, instance7524, instance7525, instance7526, instance7527)

        public fun init0() {
            instance7522 = WallSignBlockType(7522.toShort(), 0)
            instance7523 = WallSignBlockType(7523.toShort(), 1)
            instance7524 = WallSignBlockType(7524.toShort(), 2)
            instance7525 = WallSignBlockType(7525.toShort(), 3)
            instance7526 = WallSignBlockType(7526.toShort(), 4)
            instance7527 = WallSignBlockType(7527.toShort(), 5)
        }
    }
}