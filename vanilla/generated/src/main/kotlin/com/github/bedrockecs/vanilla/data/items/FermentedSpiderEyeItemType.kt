// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class FermentedSpiderEyeItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): FermentedSpiderEyeItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(428)

        public override val itemType: String = "minecraft:fermented_spider_eye"
    }
}