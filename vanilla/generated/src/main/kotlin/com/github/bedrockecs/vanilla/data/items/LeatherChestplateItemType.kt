// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class LeatherChestplateItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): LeatherChestplateItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(336)

        public override val itemType: String = "minecraft:leather_chestplate"
    }
}