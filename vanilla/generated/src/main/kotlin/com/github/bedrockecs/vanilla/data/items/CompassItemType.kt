// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class CompassItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): CompassItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(391)

        public override val itemType: String = "minecraft:compass"
    }
}
