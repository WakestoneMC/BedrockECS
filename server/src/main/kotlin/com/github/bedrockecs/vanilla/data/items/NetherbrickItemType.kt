// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class NetherbrickItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): NetherbrickItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(523)

        public override val itemType: String = "minecraft:netherbrick"
    }
}
