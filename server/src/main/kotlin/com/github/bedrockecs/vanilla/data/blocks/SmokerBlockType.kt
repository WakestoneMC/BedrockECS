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
public data class SmokerBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection): SmokerBlockType {
        val e = SmokerBlockType(0.toShort(), facingDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: SmokerBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:smoker"

        public override val itemID: ItemID = ItemID(-198)

        private lateinit var instance6877: SmokerBlockType

        private lateinit var instance6878: SmokerBlockType

        private lateinit var instance6879: SmokerBlockType

        private lateinit var instance6880: SmokerBlockType

        private lateinit var instance6881: SmokerBlockType

        private lateinit var instance6882: SmokerBlockType

        init {
            init0()
        }

        public override val primary: SmokerBlockType = instance6877

        public override val allInstances: List<SmokerBlockType> =
            listOf(instance6877, instance6878, instance6879, instance6880, instance6881, instance6882)

        public fun init0() {
            instance6877 = SmokerBlockType(6877.toShort(), 0)
            instance6878 = SmokerBlockType(6878.toShort(), 1)
            instance6879 = SmokerBlockType(6879.toShort(), 2)
            instance6880 = SmokerBlockType(6880.toShort(), 3)
            instance6881 = SmokerBlockType(6881.toShort(), 4)
            instance6882 = SmokerBlockType(6882.toShort(), 5)
        }
    }
}