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
public data class StructureVoidBlockType private constructor(
    public override val runtimeID: Short,
    public val structureVoidType: String
) : VanillaBlockType {
    public override val blockType: String = Companion.blockType

    public fun with(structureVoidType: String = this.structureVoidType): StructureVoidBlockType {
        val e = StructureVoidBlockType(0.toShort(), structureVoidType)
        return allInstances.find { it.compareVariantProperties(e) }!!
    }

    @Suppress("UNUSED_PARAMETER")
    public fun compareVariantProperties(other: StructureVoidBlockType): Boolean {
        var ret = true
        ret = ret && (this.structureVoidType == other.structureVoidType)
        return ret
    }

    public companion object : VanillaBlockType.ICompanion {
        public override val blockType: String = "minecraft:structure_void"

        public override val itemID: ItemID = ItemID(217)

        private lateinit var instance7334: StructureVoidBlockType

        private lateinit var instance7335: StructureVoidBlockType

        init {
            init0()
        }

        public override val primary: StructureVoidBlockType = instance7334

        public override val allInstances: List<StructureVoidBlockType> =
            listOf(instance7334, instance7335)

        public fun init0() {
            instance7334 = StructureVoidBlockType(7334.toShort(), "void")
            instance7335 = StructureVoidBlockType(7335.toShort(), "air")
        }
    }
}