package com.github.bedrockecs.server.comm.game.update

import java.util.UUID

/**
 * represents an action to kick a player
 */
data class DisconnectPlayerUpdate(
    val uuid: UUID
)
