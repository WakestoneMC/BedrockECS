package com.github.bedrockecs.comm.game.update

import java.util.UUID

/**
 * represents an action to kick a player
 */
data class DisconnectPlayerUpdate(
    val uuid: UUID
) : Update
