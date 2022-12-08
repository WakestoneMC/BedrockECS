package com.github.bedrockecs.game.io.action

import java.util.UUID

/**
 * represents a player has joined the server
 */
data class PlayerConnectedAction(
    val playerUUID: UUID,
    val displayName: String
) : Action
