// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class OrangeDyeItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): OrangeDyeItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(409)

        public override val itemType: String = "minecraft:orange_dye"
    }
}
