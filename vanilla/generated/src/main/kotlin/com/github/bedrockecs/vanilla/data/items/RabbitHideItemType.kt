// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class RabbitHideItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): RabbitHideItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(529)

        public override val itemType: String = "minecraft:rabbit_hide"
    }
}
