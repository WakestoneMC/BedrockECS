// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class SheepSpawnEggItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): SheepSpawnEggItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(438)

        public override val itemType: String = "minecraft:sheep_spawn_egg"
    }
}
