// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Byte
import kotlin.Int
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class SoulCampfireBlockType private constructor(
    public override val runtimeID: Short,
    public val direction: Int,
    public val extinguished: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(direction: Int = this.direction, extinguished: Byte = this.extinguished):
        SoulCampfireBlockType {
        val e = SoulCampfireBlockType(0.toShort(), direction, extinguished)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: SoulCampfireBlockType): Boolean {
        var ret = true
        ret = ret && (this.direction == other.direction)
        ret = ret && (this.extinguished == other.extinguished)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:soul_campfire"

        public override val itemID: ItemID = ItemID(-290)

        private lateinit var instance6926: SoulCampfireBlockType

        private lateinit var instance6927: SoulCampfireBlockType

        private lateinit var instance6928: SoulCampfireBlockType

        private lateinit var instance6929: SoulCampfireBlockType

        private lateinit var instance6930: SoulCampfireBlockType

        private lateinit var instance6931: SoulCampfireBlockType

        private lateinit var instance6932: SoulCampfireBlockType

        private lateinit var instance6933: SoulCampfireBlockType

        init {
            init0()
        }

        public override val primary: SoulCampfireBlockType = instance6926

        public override val allInstances: List<SoulCampfireBlockType> =
            listOf(instance6926, instance6927, instance6928, instance6929, instance6930, instance6931, instance6932, instance6933)

        public fun init0() {
            instance6926 = SoulCampfireBlockType(6926.toShort(), 0, 0.toByte())
            instance6927 = SoulCampfireBlockType(6927.toShort(), 1, 0.toByte())
            instance6928 = SoulCampfireBlockType(6928.toShort(), 2, 0.toByte())
            instance6929 = SoulCampfireBlockType(6929.toShort(), 3, 0.toByte())
            instance6930 = SoulCampfireBlockType(6930.toShort(), 0, 1.toByte())
            instance6931 = SoulCampfireBlockType(6931.toShort(), 1, 1.toByte())
            instance6932 = SoulCampfireBlockType(6932.toShort(), 2, 1.toByte())
            instance6933 = SoulCampfireBlockType(6933.toShort(), 3, 1.toByte())
        }
    }
}