// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class RedstoneItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): RedstoneItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(373)

        public override val itemType: String = "minecraft:redstone"
    }
}
