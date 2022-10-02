package com.github.bedrockecs.server.game.db.invitem.data

import com.github.bedrockecs.server.game.db.common.Component

/**
 * represents the amount of item in the slot
 */
data class ItemCountComponent(
    val count: Int
) : ItemComponent {
    companion object : Component.Companion {
        override val type: String = "becs:item_count"
    }

    override val type: String
        get() = this@Companion.type

    override fun clone(): ItemCountComponent {
        return this
    }
}
