package com.github.bedrockecs.server.game.db.invitem.data

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent

/**
 * represents the block this item represents
 */
data class ItemBlockComponent(
    val block: BlockTypeComponent
) : ItemComponent {
    companion object : Component.Companion {
        override val TYPE: String = "becs:item_block"
    }

    override val type: String
        get() = TYPE

    override fun clone(): ItemBlockComponent {
        return this
    }
}
