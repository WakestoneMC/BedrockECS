// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class CakeItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): CakeItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(417)

        public override val itemType: String = "minecraft:cake"
    }
}
