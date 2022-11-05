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
public data class SweetBerryBushBlockType private constructor(
    public override val runtimeID: Short,
    public val growth: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(growth: Int = this.growth): SweetBerryBushBlockType {
        val e = SweetBerryBushBlockType(0.toShort(), growth)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: SweetBerryBushBlockType): Boolean {
        var ret = true
        ret = ret && (this.growth == other.growth)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:sweet_berry_bush"

        public override val itemID: ItemID = ItemID(-207)

        private lateinit var instance7336: SweetBerryBushBlockType

        private lateinit var instance7337: SweetBerryBushBlockType

        private lateinit var instance7338: SweetBerryBushBlockType

        private lateinit var instance7339: SweetBerryBushBlockType

        private lateinit var instance7340: SweetBerryBushBlockType

        private lateinit var instance7341: SweetBerryBushBlockType

        private lateinit var instance7342: SweetBerryBushBlockType

        private lateinit var instance7343: SweetBerryBushBlockType

        init {
            init0()
        }

        public override val primary: SweetBerryBushBlockType = instance7336

        public override val allInstances: List<SweetBerryBushBlockType> =
            listOf(instance7336, instance7337, instance7338, instance7339, instance7340, instance7341, instance7342, instance7343)

        public fun init0() {
            instance7336 = SweetBerryBushBlockType(7336.toShort(), 0)
            instance7337 = SweetBerryBushBlockType(7337.toShort(), 1)
            instance7338 = SweetBerryBushBlockType(7338.toShort(), 2)
            instance7339 = SweetBerryBushBlockType(7339.toShort(), 3)
            instance7340 = SweetBerryBushBlockType(7340.toShort(), 4)
            instance7341 = SweetBerryBushBlockType(7341.toShort(), 5)
            instance7342 = SweetBerryBushBlockType(7342.toShort(), 6)
            instance7343 = SweetBerryBushBlockType(7343.toShort(), 7)
        }
    }
}
