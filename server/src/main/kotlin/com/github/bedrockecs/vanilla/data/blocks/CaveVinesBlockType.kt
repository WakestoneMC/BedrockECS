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
public data class CaveVinesBlockType private constructor(
    public override val runtimeID: Short,
    public val growingPlantAge: Int
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(growingPlantAge: Int = this.growingPlantAge): CaveVinesBlockType {
        val e = CaveVinesBlockType(0.toShort(), growingPlantAge)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: CaveVinesBlockType): Boolean {
        var ret = true
        ret = ret && (this.growingPlantAge == other.growingPlantAge)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:cave_vines"

        public override val itemID: ItemID = ItemID(-322)

        private lateinit var instance1013: CaveVinesBlockType

        private lateinit var instance1014: CaveVinesBlockType

        private lateinit var instance1015: CaveVinesBlockType

        private lateinit var instance1016: CaveVinesBlockType

        private lateinit var instance1017: CaveVinesBlockType

        private lateinit var instance1018: CaveVinesBlockType

        private lateinit var instance1019: CaveVinesBlockType

        private lateinit var instance1020: CaveVinesBlockType

        private lateinit var instance1021: CaveVinesBlockType

        private lateinit var instance1022: CaveVinesBlockType

        private lateinit var instance1023: CaveVinesBlockType

        private lateinit var instance1024: CaveVinesBlockType

        private lateinit var instance1025: CaveVinesBlockType

        private lateinit var instance1026: CaveVinesBlockType

        private lateinit var instance1027: CaveVinesBlockType

        private lateinit var instance1028: CaveVinesBlockType

        private lateinit var instance1029: CaveVinesBlockType

        private lateinit var instance1030: CaveVinesBlockType

        private lateinit var instance1031: CaveVinesBlockType

        private lateinit var instance1032: CaveVinesBlockType

        private lateinit var instance1033: CaveVinesBlockType

        private lateinit var instance1034: CaveVinesBlockType

        private lateinit var instance1035: CaveVinesBlockType

        private lateinit var instance1036: CaveVinesBlockType

        private lateinit var instance1037: CaveVinesBlockType

        private lateinit var instance1038: CaveVinesBlockType

        init {
            init0()
        }

        public override val primary: CaveVinesBlockType = instance1013

        public override val allInstances: List<CaveVinesBlockType> =
            listOf(instance1013, instance1014, instance1015, instance1016, instance1017, instance1018, instance1019, instance1020, instance1021, instance1022, instance1023, instance1024, instance1025, instance1026, instance1027, instance1028, instance1029, instance1030, instance1031, instance1032, instance1033, instance1034, instance1035, instance1036, instance1037, instance1038)

        public fun init0() {
            instance1013 = CaveVinesBlockType(1013.toShort(), 0)
            instance1014 = CaveVinesBlockType(1014.toShort(), 1)
            instance1015 = CaveVinesBlockType(1015.toShort(), 2)
            instance1016 = CaveVinesBlockType(1016.toShort(), 3)
            instance1017 = CaveVinesBlockType(1017.toShort(), 4)
            instance1018 = CaveVinesBlockType(1018.toShort(), 5)
            instance1019 = CaveVinesBlockType(1019.toShort(), 6)
            instance1020 = CaveVinesBlockType(1020.toShort(), 7)
            instance1021 = CaveVinesBlockType(1021.toShort(), 8)
            instance1022 = CaveVinesBlockType(1022.toShort(), 9)
            instance1023 = CaveVinesBlockType(1023.toShort(), 10)
            instance1024 = CaveVinesBlockType(1024.toShort(), 11)
            instance1025 = CaveVinesBlockType(1025.toShort(), 12)
            instance1026 = CaveVinesBlockType(1026.toShort(), 13)
            instance1027 = CaveVinesBlockType(1027.toShort(), 14)
            instance1028 = CaveVinesBlockType(1028.toShort(), 15)
            instance1029 = CaveVinesBlockType(1029.toShort(), 16)
            instance1030 = CaveVinesBlockType(1030.toShort(), 17)
            instance1031 = CaveVinesBlockType(1031.toShort(), 18)
            instance1032 = CaveVinesBlockType(1032.toShort(), 19)
            instance1033 = CaveVinesBlockType(1033.toShort(), 20)
            instance1034 = CaveVinesBlockType(1034.toShort(), 21)
            instance1035 = CaveVinesBlockType(1035.toShort(), 22)
            instance1036 = CaveVinesBlockType(1036.toShort(), 23)
            instance1037 = CaveVinesBlockType(1037.toShort(), 24)
            instance1038 = CaveVinesBlockType(1038.toShort(), 25)
        }
    }
}
