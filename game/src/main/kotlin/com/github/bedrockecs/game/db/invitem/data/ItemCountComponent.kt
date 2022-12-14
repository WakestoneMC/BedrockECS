package com.github.bedrockecs.game.db.invitem.data

import com.github.bedrockecs.game.db.common.Component

/**
 * represents the amount of item in the slot
 */
data class ItemCountComponent(
    val count: Int
) : ItemComponent {
    companion object : Component.ICompanion {
        override val type: String = "becs:item_count"
    }

    override val type: String
        get() = Companion.type

    override fun clone(): ItemCountComponent {
        return this
    }
}
