package com.github.bedrockecs.server.game.db.world.data

import com.github.bedrockecs.server.game.db.common.Component

/**
 * represents the type of block, should be a global singleton
 */
interface BlockTypeComponent : BlockComponent {
    companion object : Component.Companion {
        override val TYPE: String = "becs:block_type"
    }

    override val type: String
        get() = TYPE

    /**
     * the block type it represents, should be unique
     */
    val blockType: String

    /**
     * should contain other "variant attributes" for block states
     */

    override fun clone(): BlockTypeComponent {
        return this // because everything is immutable here
    }
}
