package com.github.bedrockecs.server.game.db.world.data

import com.github.bedrockecs.server.game.db.common.Component

/**
 * represents the type of block, should be a global singleton
 */
interface BlockTypeComponent : BlockComponent {
    /**
     * companion object for easier property access
     */
    companion object : Component.ICompanion {
        override val type = "becs:block_type"
    }

    /**
     * spec for implementor's companion object, with TYPE filled out for implementor
     */
    interface ICompanion : Component.ICompanion {
        override val type: String
            get() = BlockTypeComponent.type

        val blockType: String

        val primary: BlockTypeComponent

        val allInstances: List<BlockTypeComponent>
    }

    override val type: String
        get() = Companion.type

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
