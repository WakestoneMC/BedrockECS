// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class PoppedChorusFruitItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): PoppedChorusFruitItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(559)

        public override val itemType: String = "minecraft:popped_chorus_fruit"
    }
}
