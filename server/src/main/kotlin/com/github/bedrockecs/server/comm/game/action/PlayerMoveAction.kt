package com.github.bedrockecs.server.comm.game.action

import com.nukkitx.math.vector.Vector3f
import java.util.UUID

/**
 * represents a player is trying to move
 */
data class PlayerMoveAction(
    val playerUUID: UUID,
    val position: Vector3f,
    val rotation: Vector3f
) : Action
