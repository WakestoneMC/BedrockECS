// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.blocks

import com.github.bedrockecs.vanilla.`data`.items.ItemID
import kotlinx.serialization.Serializable
import kotlin.Boolean
import kotlin.Short
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Serializable
public data class RedSandstoneBlockType private constructor(
    public override val runtimeID: Short,
    public val sandStoneType: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(sandStoneType: String = this.sandStoneType): RedSandstoneBlockType {
        val e = RedSandstoneBlockType(0.toShort(), sandStoneType)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: RedSandstoneBlockType): Boolean {
        var ret = true
        ret = ret && (this.sandStoneType == other.sandStoneType)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:red_sandstone"

        public override val itemID: ItemID = ItemID(179)

        private lateinit var instance6636: RedSandstoneBlockType

        private lateinit var instance6637: RedSandstoneBlockType

        private lateinit var instance6638: RedSandstoneBlockType

        private lateinit var instance6639: RedSandstoneBlockType

        init {
            init0()
        }

        public override val primary: RedSandstoneBlockType = instance6636

        public override val allInstances: List<RedSandstoneBlockType> =
            listOf(instance6636, instance6637, instance6638, instance6639)

        public fun init0() {
            instance6636 = RedSandstoneBlockType(6636.toShort(), "default")
            instance6637 = RedSandstoneBlockType(6637.toShort(), "heiroglyphs")
            instance6638 = RedSandstoneBlockType(6638.toShort(), "cut")
            instance6639 = RedSandstoneBlockType(6639.toShort(), "smooth")
        }
    }
}