package com.github.bedrockecs.game.tick

import com.github.bedrockecs.game.db.common.Component
import com.github.bedrockecs.game.db.entity.data.EntityComponent

/**
 * tells the current tick, attached to the global singleton entity
 */
data class TickComponent(
    var currentTick: Long = 0
) : EntityComponent {
    companion object : Component.ICompanion {
        override val type: String = "becs:tick"
    }

    override val type: String
        get() = Companion.type

    override fun clone(): EntityComponent {
        return copy()
    }
}
