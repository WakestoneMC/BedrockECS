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
public data class PotatoesBlockType private constructor(
    public override val runtimeID: Short,
    public val growth: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(growth: Int = this.growth): PotatoesBlockType {
        val e = PotatoesBlockType(0.toShort(), growth)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: PotatoesBlockType): Boolean {
        var ret = true
        ret = ret && (this.growth == other.growth)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:potatoes"

        public override val itemID: ItemID = ItemID(142)

        private lateinit var instance6400: PotatoesBlockType

        private lateinit var instance6401: PotatoesBlockType

        private lateinit var instance6402: PotatoesBlockType

        private lateinit var instance6403: PotatoesBlockType

        private lateinit var instance6404: PotatoesBlockType

        private lateinit var instance6405: PotatoesBlockType

        private lateinit var instance6406: PotatoesBlockType

        private lateinit var instance6407: PotatoesBlockType

        init {
            init0()
        }

        public override val primary: PotatoesBlockType = instance6400

        public override val allInstances: List<PotatoesBlockType> =
            listOf(instance6400, instance6401, instance6402, instance6403, instance6404, instance6405, instance6406, instance6407)

        public fun init0() {
            instance6400 = PotatoesBlockType(6400.toShort(), 0)
            instance6401 = PotatoesBlockType(6401.toShort(), 1)
            instance6402 = PotatoesBlockType(6402.toShort(), 2)
            instance6403 = PotatoesBlockType(6403.toShort(), 3)
            instance6404 = PotatoesBlockType(6404.toShort(), 4)
            instance6405 = PotatoesBlockType(6405.toShort(), 5)
            instance6406 = PotatoesBlockType(6406.toShort(), 6)
            instance6407 = PotatoesBlockType(6407.toShort(), 7)
        }
    }
}