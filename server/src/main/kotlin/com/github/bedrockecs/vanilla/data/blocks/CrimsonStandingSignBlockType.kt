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
public data class CrimsonStandingSignBlockType private constructor(
    public override val runtimeID: Short,
    public val groundSignDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(groundSignDirection: Int = this.groundSignDirection):
        CrimsonStandingSignBlockType {
        val e = CrimsonStandingSignBlockType(0.toShort(), groundSignDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CrimsonStandingSignBlockType): Boolean {
        var ret = true
        ret = ret && (this.groundSignDirection == other.groundSignDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:crimson_standing_sign"

        public override val itemID: ItemID = ItemID(-250)

        private lateinit var instance3868: CrimsonStandingSignBlockType

        private lateinit var instance3869: CrimsonStandingSignBlockType

        private lateinit var instance3870: CrimsonStandingSignBlockType

        private lateinit var instance3871: CrimsonStandingSignBlockType

        private lateinit var instance3872: CrimsonStandingSignBlockType

        private lateinit var instance3873: CrimsonStandingSignBlockType

        private lateinit var instance3874: CrimsonStandingSignBlockType

        private lateinit var instance3875: CrimsonStandingSignBlockType

        private lateinit var instance3876: CrimsonStandingSignBlockType

        private lateinit var instance3877: CrimsonStandingSignBlockType

        private lateinit var instance3878: CrimsonStandingSignBlockType

        private lateinit var instance3879: CrimsonStandingSignBlockType

        private lateinit var instance3880: CrimsonStandingSignBlockType

        private lateinit var instance3881: CrimsonStandingSignBlockType

        private lateinit var instance3882: CrimsonStandingSignBlockType

        private lateinit var instance3883: CrimsonStandingSignBlockType

        init {
            init0()
        }

        public override val primary: CrimsonStandingSignBlockType = instance3868

        public override val allInstances: List<CrimsonStandingSignBlockType> =
            listOf(instance3868, instance3869, instance3870, instance3871, instance3872, instance3873, instance3874, instance3875, instance3876, instance3877, instance3878, instance3879, instance3880, instance3881, instance3882, instance3883)

        public fun init0() {
            instance3868 = CrimsonStandingSignBlockType(3868.toShort(), 0)
            instance3869 = CrimsonStandingSignBlockType(3869.toShort(), 1)
            instance3870 = CrimsonStandingSignBlockType(3870.toShort(), 2)
            instance3871 = CrimsonStandingSignBlockType(3871.toShort(), 3)
            instance3872 = CrimsonStandingSignBlockType(3872.toShort(), 4)
            instance3873 = CrimsonStandingSignBlockType(3873.toShort(), 5)
            instance3874 = CrimsonStandingSignBlockType(3874.toShort(), 6)
            instance3875 = CrimsonStandingSignBlockType(3875.toShort(), 7)
            instance3876 = CrimsonStandingSignBlockType(3876.toShort(), 8)
            instance3877 = CrimsonStandingSignBlockType(3877.toShort(), 9)
            instance3878 = CrimsonStandingSignBlockType(3878.toShort(), 10)
            instance3879 = CrimsonStandingSignBlockType(3879.toShort(), 11)
            instance3880 = CrimsonStandingSignBlockType(3880.toShort(), 12)
            instance3881 = CrimsonStandingSignBlockType(3881.toShort(), 13)
            instance3882 = CrimsonStandingSignBlockType(3882.toShort(), 14)
            instance3883 = CrimsonStandingSignBlockType(3883.toShort(), 15)
        }
    }
}
