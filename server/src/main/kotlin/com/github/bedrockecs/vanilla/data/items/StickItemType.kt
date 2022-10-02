package com.github.bedrockecs.vanilla.data.items

class StickItemType : VanillaItemType {
    companion object : VanillaItemType.ICompanion {
        override val itemID: ItemID = ItemID(320)
        override val itemType: String = "minecraft:stick"
    }

    override val itemType: String = Companion.itemType

    override fun clone(): StickItemType {
        return this
    }
}
