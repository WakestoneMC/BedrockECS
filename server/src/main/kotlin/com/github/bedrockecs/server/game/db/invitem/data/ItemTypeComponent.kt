package com.github.bedrockecs.server.game.db.invitem.data

import com.github.bedrockecs.server.game.db.common.Component

/**
 * represents the type of the item
 */
data class ItemTypeComponent(
    /**
     * the item type it represents, should be unique
     */
    val itemType: String
) : ItemComponent {
    companion object : Component.Companion {
        override val TYPE: String = "becs:item_type"
    }

    override val type: String
        get() = TYPE

    override fun clone(): ItemTypeComponent {
        return this
    }
}
