// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class CookedPorkchopItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): CookedPorkchopItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(263)

        public override val itemType: String = "minecraft:cooked_porkchop"
    }
}