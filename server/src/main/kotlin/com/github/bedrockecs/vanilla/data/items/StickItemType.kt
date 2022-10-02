package com.github.bedrockecs.vanilla.data.items

class StickItemType : VanillaItemType {
    companion object : VanillaItemType.Companion {
        override val itemID: ItemID = ItemID(320)
        override val ITEM_TYPE: String = "minecraft:stick"
    }

    override val itemType: String = ITEM_TYPE

    override fun clone(): StickItemType {
        return this
    }
}
