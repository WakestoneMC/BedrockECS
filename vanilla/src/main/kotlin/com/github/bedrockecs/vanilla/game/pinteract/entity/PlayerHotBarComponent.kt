package com.github.bedrockecs.vanilla.game.pinteract.entity

import com.github.bedrockecs.game.db.common.Component
import com.github.bedrockecs.game.db.entity.data.EntityComponent

data class PlayerHotBarComponent(
    var selectedSlot: Int = 0
) : EntityComponent {
    companion object : Component.ICompanion {
        override val type: String = "vanilla:hot_bar_slot"
    }

    override val type: String
        get() = Companion.type

    override fun clone(): PlayerHotBarComponent {
        return copy()
    }
}
