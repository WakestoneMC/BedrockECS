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
public data class AcaciaStandingSignBlockType private constructor(
    public override val runtimeID: Short,
    public val groundSignDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(groundSignDirection: Int = this.groundSignDirection):
        AcaciaStandingSignBlockType {
        val e = AcaciaStandingSignBlockType(0.toShort(), groundSignDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: AcaciaStandingSignBlockType): Boolean {
        var ret = true
        ret = ret && (this.groundSignDirection == other.groundSignDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:acacia_standing_sign"

        public override val itemID: ItemID = ItemID(-190)

        private lateinit var instance84: AcaciaStandingSignBlockType

        private lateinit var instance85: AcaciaStandingSignBlockType

        private lateinit var instance86: AcaciaStandingSignBlockType

        private lateinit var instance87: AcaciaStandingSignBlockType

        private lateinit var instance88: AcaciaStandingSignBlockType

        private lateinit var instance89: AcaciaStandingSignBlockType

        private lateinit var instance90: AcaciaStandingSignBlockType

        private lateinit var instance91: AcaciaStandingSignBlockType

        private lateinit var instance92: AcaciaStandingSignBlockType

        private lateinit var instance93: AcaciaStandingSignBlockType

        private lateinit var instance94: AcaciaStandingSignBlockType

        private lateinit var instance95: AcaciaStandingSignBlockType

        private lateinit var instance96: AcaciaStandingSignBlockType

        private lateinit var instance97: AcaciaStandingSignBlockType

        private lateinit var instance98: AcaciaStandingSignBlockType

        private lateinit var instance99: AcaciaStandingSignBlockType

        init {
            init0()
        }

        public override val primary: AcaciaStandingSignBlockType = instance84

        public override val allInstances: List<AcaciaStandingSignBlockType> =
            listOf(instance84, instance85, instance86, instance87, instance88, instance89, instance90, instance91, instance92, instance93, instance94, instance95, instance96, instance97, instance98, instance99)

        public fun init0() {
            instance84 = AcaciaStandingSignBlockType(84.toShort(), 0)
            instance85 = AcaciaStandingSignBlockType(85.toShort(), 1)
            instance86 = AcaciaStandingSignBlockType(86.toShort(), 2)
            instance87 = AcaciaStandingSignBlockType(87.toShort(), 3)
            instance88 = AcaciaStandingSignBlockType(88.toShort(), 4)
            instance89 = AcaciaStandingSignBlockType(89.toShort(), 5)
            instance90 = AcaciaStandingSignBlockType(90.toShort(), 6)
            instance91 = AcaciaStandingSignBlockType(91.toShort(), 7)
            instance92 = AcaciaStandingSignBlockType(92.toShort(), 8)
            instance93 = AcaciaStandingSignBlockType(93.toShort(), 9)
            instance94 = AcaciaStandingSignBlockType(94.toShort(), 10)
            instance95 = AcaciaStandingSignBlockType(95.toShort(), 11)
            instance96 = AcaciaStandingSignBlockType(96.toShort(), 12)
            instance97 = AcaciaStandingSignBlockType(97.toShort(), 13)
            instance98 = AcaciaStandingSignBlockType(98.toShort(), 14)
            instance99 = AcaciaStandingSignBlockType(99.toShort(), 15)
        }
    }
}