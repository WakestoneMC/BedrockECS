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
public data class WeepingVinesBlockType private constructor(
    public override val runtimeID: Short,
    public val weepingVinesAge: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(weepingVinesAge: Int = this.weepingVinesAge): WeepingVinesBlockType {
        val e = WeepingVinesBlockType(0.toShort(), weepingVinesAge)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: WeepingVinesBlockType): Boolean {
        var ret = true
        ret = ret && (this.weepingVinesAge == other.weepingVinesAge)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:weeping_vines"

        public override val itemID: ItemID = ItemID(-231)

        private lateinit var instance7754: WeepingVinesBlockType

        private lateinit var instance7755: WeepingVinesBlockType

        private lateinit var instance7756: WeepingVinesBlockType

        private lateinit var instance7757: WeepingVinesBlockType

        private lateinit var instance7758: WeepingVinesBlockType

        private lateinit var instance7759: WeepingVinesBlockType

        private lateinit var instance7760: WeepingVinesBlockType

        private lateinit var instance7761: WeepingVinesBlockType

        private lateinit var instance7762: WeepingVinesBlockType

        private lateinit var instance7763: WeepingVinesBlockType

        private lateinit var instance7764: WeepingVinesBlockType

        private lateinit var instance7765: WeepingVinesBlockType

        private lateinit var instance7766: WeepingVinesBlockType

        private lateinit var instance7767: WeepingVinesBlockType

        private lateinit var instance7768: WeepingVinesBlockType

        private lateinit var instance7769: WeepingVinesBlockType

        private lateinit var instance7770: WeepingVinesBlockType

        private lateinit var instance7771: WeepingVinesBlockType

        private lateinit var instance7772: WeepingVinesBlockType

        private lateinit var instance7773: WeepingVinesBlockType

        private lateinit var instance7774: WeepingVinesBlockType

        private lateinit var instance7775: WeepingVinesBlockType

        private lateinit var instance7776: WeepingVinesBlockType

        private lateinit var instance7777: WeepingVinesBlockType

        private lateinit var instance7778: WeepingVinesBlockType

        private lateinit var instance7779: WeepingVinesBlockType

        init {
            init0()
        }

        public override val primary: WeepingVinesBlockType = instance7754

        public override val allInstances: List<WeepingVinesBlockType> =
            listOf(instance7754, instance7755, instance7756, instance7757, instance7758, instance7759, instance7760, instance7761, instance7762, instance7763, instance7764, instance7765, instance7766, instance7767, instance7768, instance7769, instance7770, instance7771, instance7772, instance7773, instance7774, instance7775, instance7776, instance7777, instance7778, instance7779)

        public fun init0() {
            instance7754 = WeepingVinesBlockType(7754.toShort(), 0)
            instance7755 = WeepingVinesBlockType(7755.toShort(), 1)
            instance7756 = WeepingVinesBlockType(7756.toShort(), 2)
            instance7757 = WeepingVinesBlockType(7757.toShort(), 3)
            instance7758 = WeepingVinesBlockType(7758.toShort(), 4)
            instance7759 = WeepingVinesBlockType(7759.toShort(), 5)
            instance7760 = WeepingVinesBlockType(7760.toShort(), 6)
            instance7761 = WeepingVinesBlockType(7761.toShort(), 7)
            instance7762 = WeepingVinesBlockType(7762.toShort(), 8)
            instance7763 = WeepingVinesBlockType(7763.toShort(), 9)
            instance7764 = WeepingVinesBlockType(7764.toShort(), 10)
            instance7765 = WeepingVinesBlockType(7765.toShort(), 11)
            instance7766 = WeepingVinesBlockType(7766.toShort(), 12)
            instance7767 = WeepingVinesBlockType(7767.toShort(), 13)
            instance7768 = WeepingVinesBlockType(7768.toShort(), 14)
            instance7769 = WeepingVinesBlockType(7769.toShort(), 15)
            instance7770 = WeepingVinesBlockType(7770.toShort(), 16)
            instance7771 = WeepingVinesBlockType(7771.toShort(), 17)
            instance7772 = WeepingVinesBlockType(7772.toShort(), 18)
            instance7773 = WeepingVinesBlockType(7773.toShort(), 19)
            instance7774 = WeepingVinesBlockType(7774.toShort(), 20)
            instance7775 = WeepingVinesBlockType(7775.toShort(), 21)
            instance7776 = WeepingVinesBlockType(7776.toShort(), 22)
            instance7777 = WeepingVinesBlockType(7777.toShort(), 23)
            instance7778 = WeepingVinesBlockType(7778.toShort(), 24)
            instance7779 = WeepingVinesBlockType(7779.toShort(), 25)
        }
    }
}
