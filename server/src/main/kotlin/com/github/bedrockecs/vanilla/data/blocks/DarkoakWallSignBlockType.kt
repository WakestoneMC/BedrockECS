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
public data class DarkoakWallSignBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): DarkoakWallSignBlockType {
        val e = DarkoakWallSignBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: DarkoakWallSignBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:darkoak_wall_sign"

        public override val itemID: ItemID = ItemID(-193)

        private lateinit var instance4061: DarkoakWallSignBlockType

        private lateinit var instance4062: DarkoakWallSignBlockType

        private lateinit var instance4063: DarkoakWallSignBlockType

        private lateinit var instance4064: DarkoakWallSignBlockType

        private lateinit var instance4065: DarkoakWallSignBlockType

        private lateinit var instance4066: DarkoakWallSignBlockType

        init {
            init0()
        }

        public override val primary: DarkoakWallSignBlockType = instance4061

        public override val allInstances: List<DarkoakWallSignBlockType> =
            listOf(instance4061, instance4062, instance4063, instance4064, instance4065, instance4066)

        public fun init0() {
            instance4061 = DarkoakWallSignBlockType(4061.toShort(), 0)
            instance4062 = DarkoakWallSignBlockType(4062.toShort(), 1)
            instance4063 = DarkoakWallSignBlockType(4063.toShort(), 2)
            instance4064 = DarkoakWallSignBlockType(4064.toShort(), 3)
            instance4065 = DarkoakWallSignBlockType(4065.toShort(), 4)
            instance4066 = DarkoakWallSignBlockType(4066.toShort(), 5)
        }
    }
}
