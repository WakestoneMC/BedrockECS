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
public data class SculkVeinBlockType private constructor(
    public override val runtimeID: Short,
    public val multiFaceDirectionBits: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(multiFaceDirectionBits: Int = this.multiFaceDirectionBits): SculkVeinBlockType {
        val e = SculkVeinBlockType(0.toShort(), multiFaceDirectionBits)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: SculkVeinBlockType): Boolean {
        var ret = true
        ret = ret && (this.multiFaceDirectionBits == other.multiFaceDirectionBits)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:sculk_vein"

        public override val itemID: ItemID = ItemID(-459)

        private lateinit var instance6756: SculkVeinBlockType

        private lateinit var instance6757: SculkVeinBlockType

        private lateinit var instance6758: SculkVeinBlockType

        private lateinit var instance6759: SculkVeinBlockType

        private lateinit var instance6760: SculkVeinBlockType

        private lateinit var instance6761: SculkVeinBlockType

        private lateinit var instance6762: SculkVeinBlockType

        private lateinit var instance6763: SculkVeinBlockType

        private lateinit var instance6764: SculkVeinBlockType

        private lateinit var instance6765: SculkVeinBlockType

        private lateinit var instance6766: SculkVeinBlockType

        private lateinit var instance6767: SculkVeinBlockType

        private lateinit var instance6768: SculkVeinBlockType

        private lateinit var instance6769: SculkVeinBlockType

        private lateinit var instance6770: SculkVeinBlockType

        private lateinit var instance6771: SculkVeinBlockType

        private lateinit var instance6772: SculkVeinBlockType

        private lateinit var instance6773: SculkVeinBlockType

        private lateinit var instance6774: SculkVeinBlockType

        private lateinit var instance6775: SculkVeinBlockType

        private lateinit var instance6776: SculkVeinBlockType

        private lateinit var instance6777: SculkVeinBlockType

        private lateinit var instance6778: SculkVeinBlockType

        private lateinit var instance6779: SculkVeinBlockType

        private lateinit var instance6780: SculkVeinBlockType

        private lateinit var instance6781: SculkVeinBlockType

        private lateinit var instance6782: SculkVeinBlockType

        private lateinit var instance6783: SculkVeinBlockType

        private lateinit var instance6784: SculkVeinBlockType

        private lateinit var instance6785: SculkVeinBlockType

        private lateinit var instance6786: SculkVeinBlockType

        private lateinit var instance6787: SculkVeinBlockType

        private lateinit var instance6788: SculkVeinBlockType

        private lateinit var instance6789: SculkVeinBlockType

        private lateinit var instance6790: SculkVeinBlockType

        private lateinit var instance6791: SculkVeinBlockType

        private lateinit var instance6792: SculkVeinBlockType

        private lateinit var instance6793: SculkVeinBlockType

        private lateinit var instance6794: SculkVeinBlockType

        private lateinit var instance6795: SculkVeinBlockType

        private lateinit var instance6796: SculkVeinBlockType

        private lateinit var instance6797: SculkVeinBlockType

        private lateinit var instance6798: SculkVeinBlockType

        private lateinit var instance6799: SculkVeinBlockType

        private lateinit var instance6800: SculkVeinBlockType

        private lateinit var instance6801: SculkVeinBlockType

        private lateinit var instance6802: SculkVeinBlockType

        private lateinit var instance6803: SculkVeinBlockType

        private lateinit var instance6804: SculkVeinBlockType

        private lateinit var instance6805: SculkVeinBlockType

        private lateinit var instance6806: SculkVeinBlockType

        private lateinit var instance6807: SculkVeinBlockType

        private lateinit var instance6808: SculkVeinBlockType

        private lateinit var instance6809: SculkVeinBlockType

        private lateinit var instance6810: SculkVeinBlockType

        private lateinit var instance6811: SculkVeinBlockType

        private lateinit var instance6812: SculkVeinBlockType

        private lateinit var instance6813: SculkVeinBlockType

        private lateinit var instance6814: SculkVeinBlockType

        private lateinit var instance6815: SculkVeinBlockType

        private lateinit var instance6816: SculkVeinBlockType

        private lateinit var instance6817: SculkVeinBlockType

        private lateinit var instance6818: SculkVeinBlockType

        private lateinit var instance6819: SculkVeinBlockType

        init {
            init0()
        }

        public override val primary: SculkVeinBlockType = instance6756

        public override val allInstances: List<SculkVeinBlockType> =
            listOf(instance6756, instance6757, instance6758, instance6759, instance6760, instance6761, instance6762, instance6763, instance6764, instance6765, instance6766, instance6767, instance6768, instance6769, instance6770, instance6771, instance6772, instance6773, instance6774, instance6775, instance6776, instance6777, instance6778, instance6779, instance6780, instance6781, instance6782, instance6783, instance6784, instance6785, instance6786, instance6787, instance6788, instance6789, instance6790, instance6791, instance6792, instance6793, instance6794, instance6795, instance6796, instance6797, instance6798, instance6799, instance6800, instance6801, instance6802, instance6803, instance6804, instance6805, instance6806, instance6807, instance6808, instance6809, instance6810, instance6811, instance6812, instance6813, instance6814, instance6815, instance6816, instance6817, instance6818, instance6819)

        public fun init0() {
            instance6756 = SculkVeinBlockType(6756.toShort(), 0)
            instance6757 = SculkVeinBlockType(6757.toShort(), 1)
            instance6758 = SculkVeinBlockType(6758.toShort(), 2)
            instance6759 = SculkVeinBlockType(6759.toShort(), 3)
            instance6760 = SculkVeinBlockType(6760.toShort(), 4)
            instance6761 = SculkVeinBlockType(6761.toShort(), 5)
            instance6762 = SculkVeinBlockType(6762.toShort(), 6)
            instance6763 = SculkVeinBlockType(6763.toShort(), 7)
            instance6764 = SculkVeinBlockType(6764.toShort(), 8)
            instance6765 = SculkVeinBlockType(6765.toShort(), 9)
            instance6766 = SculkVeinBlockType(6766.toShort(), 10)
            instance6767 = SculkVeinBlockType(6767.toShort(), 11)
            instance6768 = SculkVeinBlockType(6768.toShort(), 12)
            instance6769 = SculkVeinBlockType(6769.toShort(), 13)
            instance6770 = SculkVeinBlockType(6770.toShort(), 14)
            instance6771 = SculkVeinBlockType(6771.toShort(), 15)
            instance6772 = SculkVeinBlockType(6772.toShort(), 16)
            instance6773 = SculkVeinBlockType(6773.toShort(), 17)
            instance6774 = SculkVeinBlockType(6774.toShort(), 18)
            instance6775 = SculkVeinBlockType(6775.toShort(), 19)
            instance6776 = SculkVeinBlockType(6776.toShort(), 20)
            instance6777 = SculkVeinBlockType(6777.toShort(), 21)
            instance6778 = SculkVeinBlockType(6778.toShort(), 22)
            instance6779 = SculkVeinBlockType(6779.toShort(), 23)
            instance6780 = SculkVeinBlockType(6780.toShort(), 24)
            instance6781 = SculkVeinBlockType(6781.toShort(), 25)
            instance6782 = SculkVeinBlockType(6782.toShort(), 26)
            instance6783 = SculkVeinBlockType(6783.toShort(), 27)
            instance6784 = SculkVeinBlockType(6784.toShort(), 28)
            instance6785 = SculkVeinBlockType(6785.toShort(), 29)
            instance6786 = SculkVeinBlockType(6786.toShort(), 30)
            instance6787 = SculkVeinBlockType(6787.toShort(), 31)
            instance6788 = SculkVeinBlockType(6788.toShort(), 32)
            instance6789 = SculkVeinBlockType(6789.toShort(), 33)
            instance6790 = SculkVeinBlockType(6790.toShort(), 34)
            instance6791 = SculkVeinBlockType(6791.toShort(), 35)
            instance6792 = SculkVeinBlockType(6792.toShort(), 36)
            instance6793 = SculkVeinBlockType(6793.toShort(), 37)
            instance6794 = SculkVeinBlockType(6794.toShort(), 38)
            instance6795 = SculkVeinBlockType(6795.toShort(), 39)
            instance6796 = SculkVeinBlockType(6796.toShort(), 40)
            instance6797 = SculkVeinBlockType(6797.toShort(), 41)
            instance6798 = SculkVeinBlockType(6798.toShort(), 42)
            instance6799 = SculkVeinBlockType(6799.toShort(), 43)
            instance6800 = SculkVeinBlockType(6800.toShort(), 44)
            instance6801 = SculkVeinBlockType(6801.toShort(), 45)
            instance6802 = SculkVeinBlockType(6802.toShort(), 46)
            instance6803 = SculkVeinBlockType(6803.toShort(), 47)
            instance6804 = SculkVeinBlockType(6804.toShort(), 48)
            instance6805 = SculkVeinBlockType(6805.toShort(), 49)
            instance6806 = SculkVeinBlockType(6806.toShort(), 50)
            instance6807 = SculkVeinBlockType(6807.toShort(), 51)
            instance6808 = SculkVeinBlockType(6808.toShort(), 52)
            instance6809 = SculkVeinBlockType(6809.toShort(), 53)
            instance6810 = SculkVeinBlockType(6810.toShort(), 54)
            instance6811 = SculkVeinBlockType(6811.toShort(), 55)
            instance6812 = SculkVeinBlockType(6812.toShort(), 56)
            instance6813 = SculkVeinBlockType(6813.toShort(), 57)
            instance6814 = SculkVeinBlockType(6814.toShort(), 58)
            instance6815 = SculkVeinBlockType(6815.toShort(), 59)
            instance6816 = SculkVeinBlockType(6816.toShort(), 60)
            instance6817 = SculkVeinBlockType(6817.toShort(), 61)
            instance6818 = SculkVeinBlockType(6818.toShort(), 62)
            instance6819 = SculkVeinBlockType(6819.toShort(), 63)
        }
    }
}
