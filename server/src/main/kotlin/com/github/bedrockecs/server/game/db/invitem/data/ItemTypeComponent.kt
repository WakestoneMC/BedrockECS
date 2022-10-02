package com.github.bedrockecs.server.game.db.invitem.data

import com.github.bedrockecs.server.game.db.common.Component

/**
 * represents the type of the item
 */
interface ItemTypeComponent : ItemComponent {
    /**
     * spec for its companion object, with TYPE filled out for implementor
     */
    interface Companion : Component.Companion {
        companion object {
            val TYPE = "becs:item_type"
        }

        override val TYPE: String
            get() = ItemTypeComponent.Companion.TYPE

        val ITEM_TYPE: String
    }

    override val type: String
        get() = Companion.TYPE

    val itemType: String
}
