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
public data class JungleStandingSignBlockType private constructor(
    public override val runtimeID: Short,
    public val groundSignDirection: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(groundSignDirection: Int = this.groundSignDirection):
        JungleStandingSignBlockType {
        val e = JungleStandingSignBlockType(0.toShort(), groundSignDirection)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: JungleStandingSignBlockType): Boolean {
        var ret = true
        ret = ret && (this.groundSignDirection == other.groundSignDirection)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:jungle_standing_sign"

        public override val itemID: ItemID = ItemID(-188)

        private lateinit var instance5294: JungleStandingSignBlockType

        private lateinit var instance5295: JungleStandingSignBlockType

        private lateinit var instance5296: JungleStandingSignBlockType

        private lateinit var instance5297: JungleStandingSignBlockType

        private lateinit var instance5298: JungleStandingSignBlockType

        private lateinit var instance5299: JungleStandingSignBlockType

        private lateinit var instance5300: JungleStandingSignBlockType

        private lateinit var instance5301: JungleStandingSignBlockType

        private lateinit var instance5302: JungleStandingSignBlockType

        private lateinit var instance5303: JungleStandingSignBlockType

        private lateinit var instance5304: JungleStandingSignBlockType

        private lateinit var instance5305: JungleStandingSignBlockType

        private lateinit var instance5306: JungleStandingSignBlockType

        private lateinit var instance5307: JungleStandingSignBlockType

        private lateinit var instance5308: JungleStandingSignBlockType

        private lateinit var instance5309: JungleStandingSignBlockType

        init {
            init0()
        }

        public override val primary: JungleStandingSignBlockType = instance5294

        public override val allInstances: List<JungleStandingSignBlockType> =
            listOf(instance5294, instance5295, instance5296, instance5297, instance5298, instance5299, instance5300, instance5301, instance5302, instance5303, instance5304, instance5305, instance5306, instance5307, instance5308, instance5309)

        public fun init0() {
            instance5294 = JungleStandingSignBlockType(5294.toShort(), 0)
            instance5295 = JungleStandingSignBlockType(5295.toShort(), 1)
            instance5296 = JungleStandingSignBlockType(5296.toShort(), 2)
            instance5297 = JungleStandingSignBlockType(5297.toShort(), 3)
            instance5298 = JungleStandingSignBlockType(5298.toShort(), 4)
            instance5299 = JungleStandingSignBlockType(5299.toShort(), 5)
            instance5300 = JungleStandingSignBlockType(5300.toShort(), 6)
            instance5301 = JungleStandingSignBlockType(5301.toShort(), 7)
            instance5302 = JungleStandingSignBlockType(5302.toShort(), 8)
            instance5303 = JungleStandingSignBlockType(5303.toShort(), 9)
            instance5304 = JungleStandingSignBlockType(5304.toShort(), 10)
            instance5305 = JungleStandingSignBlockType(5305.toShort(), 11)
            instance5306 = JungleStandingSignBlockType(5306.toShort(), 12)
            instance5307 = JungleStandingSignBlockType(5307.toShort(), 13)
            instance5308 = JungleStandingSignBlockType(5308.toShort(), 14)
            instance5309 = JungleStandingSignBlockType(5309.toShort(), 15)
        }
    }
}
