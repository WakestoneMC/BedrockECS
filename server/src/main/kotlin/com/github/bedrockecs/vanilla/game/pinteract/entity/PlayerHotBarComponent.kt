package com.github.bedrockecs.vanilla.game.pinteract.entity

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.entity.data.EntityComponent

data class PlayerHotBarComponent(
    var selectedSlot: Int = 0
) : EntityComponent {
    companion object : Component.Companion {
        override val TYPE: String = "vanilla:hot_bar_slot"
    }

    override val type: String
        get() = TYPE

    override fun clone(): PlayerHotBarComponent {
        return copy()
    }
}
