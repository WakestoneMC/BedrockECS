// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class WarpedFungusOnAStickItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): WarpedFungusOnAStickItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(618)

        public override val itemType: String = "minecraft:warped_fungus_on_a_stick"
    }
}
