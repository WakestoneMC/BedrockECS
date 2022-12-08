package com.github.bedrockecs.game.db.entity.data

import java.util.UUID

/**
 * represents the identity information of player entity
 */
data class PlayerIdentityComponent(
    var name: String,
    var uuid: UUID
) : EntityComponent {
    override val type: String
        get() = "becs:player_identity"

    override fun clone(): PlayerIdentityComponent {
        return copy()
    }
}
