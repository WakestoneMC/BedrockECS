// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class ChainmailHelmetItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): ChainmailHelmetItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(339)

        public override val itemType: String = "minecraft:chainmail_helmet"
    }
}
