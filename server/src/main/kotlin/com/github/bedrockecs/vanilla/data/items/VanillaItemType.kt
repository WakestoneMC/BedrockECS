package com.github.bedrockecs.vanilla.data.items

import com.github.bedrockecs.server.game.db.invitem.data.ItemTypeComponent

interface VanillaItemType : ItemTypeComponent {
    interface Companion {
        val itemID: ItemID
    }
}
