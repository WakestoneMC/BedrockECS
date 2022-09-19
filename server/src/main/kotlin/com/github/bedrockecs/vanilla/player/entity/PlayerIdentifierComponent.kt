package com.github.bedrockecs.vanilla.player.entity

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.entity.data.EntityComponent
import java.util.UUID

/**
 * represents the identifier of player
 */
data class PlayerIdentifierComponent(
    val displayName: String,
    val uuid: UUID
) : EntityComponent {
    companion object : Component.Companion {
        override val TYPE = "vanilla:player_identifiers"
    }

    override val type: String
        get() = TYPE

    override fun clone(): PlayerIdentifierComponent {
        return this // because everything is immutable here
    }
}
