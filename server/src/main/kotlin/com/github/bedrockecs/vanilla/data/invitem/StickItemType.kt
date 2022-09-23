package com.github.bedrockecs.vanilla.data.invitem

class StickItemType : VanillaItemType {
    companion object : VanillaItemType.Companion {
        override val itemID: ItemID = ItemID(280)
    }

    override val itemType: String = "minecraft:stick"

    override fun clone(): StickItemType {
        return this
    }
}
