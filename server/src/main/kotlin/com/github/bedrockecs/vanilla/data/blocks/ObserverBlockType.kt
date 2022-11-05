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
public data class ObserverBlockType private constructor(
    public override val runtimeID: Short,
    public val facingDirection: Int,
    public val poweredBit: Byte
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(facingDirection: Int = this.facingDirection, poweredBit: Byte = this.poweredBit):
        ObserverBlockType {
        val e = ObserverBlockType(0.toShort(), facingDirection, poweredBit)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: ObserverBlockType): Boolean {
        var ret = true
        ret = ret && (this.facingDirection == other.facingDirection)
        ret = ret && (this.poweredBit == other.poweredBit)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:observer"

        public override val itemID: ItemID = ItemID(251)

        private lateinit var instance5726: ObserverBlockType

        private lateinit var instance5727: ObserverBlockType

        private lateinit var instance5728: ObserverBlockType

        private lateinit var instance5729: ObserverBlockType

        private lateinit var instance5730: ObserverBlockType

        private lateinit var instance5731: ObserverBlockType

        private lateinit var instance5732: ObserverBlockType

        private lateinit var instance5733: ObserverBlockType

        private lateinit var instance5734: ObserverBlockType

        private lateinit var instance5735: ObserverBlockType

        private lateinit var instance5736: ObserverBlockType

        private lateinit var instance5737: ObserverBlockType

        init {
            init0()
        }

        public override val primary: ObserverBlockType = instance5726

        public override val allInstances: List<ObserverBlockType> =
            listOf(instance5726, instance5727, instance5728, instance5729, instance5730, instance5731, instance5732, instance5733, instance5734, instance5735, instance5736, instance5737)

        public fun init0() {
            instance5726 = ObserverBlockType(5726.toShort(), 0, 0.toByte())
            instance5727 = ObserverBlockType(5727.toShort(), 1, 0.toByte())
            instance5728 = ObserverBlockType(5728.toShort(), 2, 0.toByte())
            instance5729 = ObserverBlockType(5729.toShort(), 3, 0.toByte())
            instance5730 = ObserverBlockType(5730.toShort(), 4, 0.toByte())
            instance5731 = ObserverBlockType(5731.toShort(), 5, 0.toByte())
            instance5732 = ObserverBlockType(5732.toShort(), 0, 1.toByte())
            instance5733 = ObserverBlockType(5733.toShort(), 1, 1.toByte())
            instance5734 = ObserverBlockType(5734.toShort(), 2, 1.toByte())
            instance5735 = ObserverBlockType(5735.toShort(), 3, 1.toByte())
            instance5736 = ObserverBlockType(5736.toShort(), 4, 1.toByte())
            instance5737 = ObserverBlockType(5737.toShort(), 5, 1.toByte())
        }
    }
}