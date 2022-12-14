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
public data class HardStainedGlassPaneBlockType private constructor(
    public override val runtimeID: Short,
    public val color: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(color: String = this.color): HardStainedGlassPaneBlockType {
        val e = HardStainedGlassPaneBlockType(0.toShort(), color)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: HardStainedGlassPaneBlockType): Boolean {
        var ret = true
        ret = ret && (this.color == other.color)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:hard_stained_glass_pane"

        public override val itemID: ItemID = ItemID(191)

        private lateinit var instance5068: HardStainedGlassPaneBlockType

        private lateinit var instance5069: HardStainedGlassPaneBlockType

        private lateinit var instance5070: HardStainedGlassPaneBlockType

        private lateinit var instance5071: HardStainedGlassPaneBlockType

        private lateinit var instance5072: HardStainedGlassPaneBlockType

        private lateinit var instance5073: HardStainedGlassPaneBlockType

        private lateinit var instance5074: HardStainedGlassPaneBlockType

        private lateinit var instance5075: HardStainedGlassPaneBlockType

        private lateinit var instance5076: HardStainedGlassPaneBlockType

        private lateinit var instance5077: HardStainedGlassPaneBlockType

        private lateinit var instance5078: HardStainedGlassPaneBlockType

        private lateinit var instance5079: HardStainedGlassPaneBlockType

        private lateinit var instance5080: HardStainedGlassPaneBlockType

        private lateinit var instance5081: HardStainedGlassPaneBlockType

        private lateinit var instance5082: HardStainedGlassPaneBlockType

        private lateinit var instance5083: HardStainedGlassPaneBlockType

        init {
            init0()
        }

        public override val primary: HardStainedGlassPaneBlockType = instance5068

        public override val allInstances: List<HardStainedGlassPaneBlockType> =
            listOf(instance5068, instance5069, instance5070, instance5071, instance5072, instance5073, instance5074, instance5075, instance5076, instance5077, instance5078, instance5079, instance5080, instance5081, instance5082, instance5083)

        public fun init0() {
            instance5068 = HardStainedGlassPaneBlockType(5068.toShort(), "white")
            instance5069 = HardStainedGlassPaneBlockType(5069.toShort(), "orange")
            instance5070 = HardStainedGlassPaneBlockType(5070.toShort(), "magenta")
            instance5071 = HardStainedGlassPaneBlockType(5071.toShort(), "light_blue")
            instance5072 = HardStainedGlassPaneBlockType(5072.toShort(), "yellow")
            instance5073 = HardStainedGlassPaneBlockType(5073.toShort(), "lime")
            instance5074 = HardStainedGlassPaneBlockType(5074.toShort(), "pink")
            instance5075 = HardStainedGlassPaneBlockType(5075.toShort(), "gray")
            instance5076 = HardStainedGlassPaneBlockType(5076.toShort(), "silver")
            instance5077 = HardStainedGlassPaneBlockType(5077.toShort(), "cyan")
            instance5078 = HardStainedGlassPaneBlockType(5078.toShort(), "purple")
            instance5079 = HardStainedGlassPaneBlockType(5079.toShort(), "blue")
            instance5080 = HardStainedGlassPaneBlockType(5080.toShort(), "brown")
            instance5081 = HardStainedGlassPaneBlockType(5081.toShort(), "green")
            instance5082 = HardStainedGlassPaneBlockType(5082.toShort(), "red")
            instance5083 = HardStainedGlassPaneBlockType(5083.toShort(), "black")
        }
    }
}
