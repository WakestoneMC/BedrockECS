// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class ChainItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): ChainItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(619)

        public override val itemType: String = "minecraft:chain"
    }
}