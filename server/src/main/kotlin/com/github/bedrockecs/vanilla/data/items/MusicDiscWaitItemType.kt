// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class MusicDiscWaitItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): MusicDiscWaitItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(545)

        public override val itemType: String = "minecraft:music_disc_wait"
    }
}