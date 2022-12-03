package com.github.bedrockecs.game.db.invitem.data

import com.github.bedrockecs.game.db.common.Component

/**
 * represents the type of the item
 */
interface ItemTypeComponent : ItemComponent {
    /**
     * helper for accessing properties
     */
    companion object : Component.ICompanion {
        override val type = "becs:item_type"
    }

    /**
     * spec for implementor's companion object, with TYPE filled out for implementor
     */
    interface ICompanion : Component.ICompanion {
        override val type: String
            get() = Companion.type

        val itemType: String
    }

    override val type: String
        get() = Companion.type

    val itemType: String
}
