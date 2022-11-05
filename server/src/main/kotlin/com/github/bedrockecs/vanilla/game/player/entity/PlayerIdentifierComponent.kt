package com.github.bedrockecs.vanilla.game.player.entity

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
    companion object : Component.ICompanion {
        override val type = "vanilla:player_identifiers"
    }

    override val type: String
        get() = Companion.type

    override fun clone(): PlayerIdentifierComponent {
        return this // because everything is immutable here
    }
}
