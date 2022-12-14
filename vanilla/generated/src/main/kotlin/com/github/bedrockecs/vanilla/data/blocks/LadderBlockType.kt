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
public data class LadderBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): LadderBlockType {
        val e = LadderBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: LadderBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:ladder"

        public override val itemID: ItemID = ItemID(65)

        private lateinit var instance5358: LadderBlockType

        private lateinit var instance5359: LadderBlockType

        private lateinit var instance5360: LadderBlockType

        private lateinit var instance5361: LadderBlockType

        private lateinit var instance5362: LadderBlockType

        private lateinit var instance5363: LadderBlockType

        init {
            init0()
        }

        public override val primary: LadderBlockType = instance5358

        public override val allInstances: List<LadderBlockType> =
            listOf(instance5358, instance5359, instance5360, instance5361, instance5362, instance5363)

        public fun init0() {
            instance5358 = LadderBlockType(5358.toShort(), 0)
            instance5359 = LadderBlockType(5359.toShort(), 1)
            instance5360 = LadderBlockType(5360.toShort(), 2)
            instance5361 = LadderBlockType(5361.toShort(), 3)
            instance5362 = LadderBlockType(5362.toShort(), 4)
            instance5363 = LadderBlockType(5363.toShort(), 5)
        }
    }
}
