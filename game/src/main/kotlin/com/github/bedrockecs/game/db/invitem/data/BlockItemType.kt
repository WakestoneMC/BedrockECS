package com.github.bedrockecs.game.db.invitem.data

import com.github.bedrockecs.game.db.common.Component
import com.github.bedrockecs.game.db.world.data.BlockTypeComponent

/**
 * a special type for all item that corresponds to blocks
 */
data class BlockItemType(
    val block: BlockTypeComponent
) : ItemTypeComponent {
    override val itemType: String
        get() = "becs:block"

    override fun clone(): Component {
        return copy()
    }
}
