package com.github.bedrockecs.vanilla.data.items

import com.github.bedrockecs.game.db.invitem.data.ItemTypeComponent

interface VanillaItemType : ItemTypeComponent {
    interface ICompanion : ItemTypeComponent.ICompanion {
        val itemID: ItemID
    }
}
