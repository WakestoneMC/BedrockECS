package com.github.bedrockecs.server.game.tick

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent

/**
 * tells the current tick, attached to dimension 0 as global singleton
 */
data class TickComponent(
    var currentTick: Long = 0
) : DimensionComponent {
    companion object : Component.Companion {
        override val type: String = "becs:tick"
    }

    override val type: String
        get() = this@Companion.type

    override fun clone(): Component {
        return copy()
    }
}
