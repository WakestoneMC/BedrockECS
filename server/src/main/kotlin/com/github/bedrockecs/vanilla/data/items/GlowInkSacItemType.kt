// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class GlowInkSacItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): GlowInkSacItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(503)

        public override val itemType: String = "minecraft:glow_ink_sac"
    }
}
