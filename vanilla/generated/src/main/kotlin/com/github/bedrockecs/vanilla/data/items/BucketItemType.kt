// generated by becs datacompiler, DO NOT EDIT!
package com.github.bedrockecs.vanilla.`data`.items

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
public class BucketItemType : VanillaItemType {
    public override val itemType: String = Companion.itemType

    public override fun clone(): BucketItemType = this

    public companion object : VanillaItemType.ICompanion {
        public override val itemID: ItemID = ItemID(360)

        public override val itemType: String = "minecraft:bucket"
    }
}
