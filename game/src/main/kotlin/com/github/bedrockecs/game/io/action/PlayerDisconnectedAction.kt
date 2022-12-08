package com.github.bedrockecs.game.io.action

import java.util.UUID

/**
 * represents a player has left the server
 */
data class PlayerDisconnectedAction(
    val playerUUID: UUID
) : Action
