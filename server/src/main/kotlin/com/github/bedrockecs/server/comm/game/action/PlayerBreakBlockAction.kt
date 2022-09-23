package com.github.bedrockecs.server.comm.game.action

import com.nukkitx.math.vector.Vector3i
import java.util.UUID

/**
 * emitted whenever player is breaking a block
 */
data class PlayerBreakBlockAction(
    val player: UUID,
    val block: Vector3i, // x, y, z of the block
    val face: BlockFace
) : Action
