package com.github.bedrockecs.server.game.db.invitem.data

import com.github.bedrockecs.server.game.db.common.Component

/**
 * represents the type of the item
 */
interface ItemTypeComponent : ItemComponent {
    companion object : Component.Companion {
        override val TYPE: String = "becs:item_type"
    }

    override val type: String
        get() = TYPE

    val itemType: String
}
