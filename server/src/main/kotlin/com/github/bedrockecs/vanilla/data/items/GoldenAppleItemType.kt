// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class GoldenAppleItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): GoldenAppleItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(258)

        public override val itemType: String = "minecraft:golden_apple"
    }
}
