package com.github.bedrockecs.game.io.action

import com.nukkitx.math.vector.Vector3i
import java.util.UUID

/**
 * invoked when player is using an item (right clicking)
 */
data class PlayerUseItemAction(
    val player: UUID,
    val block: Vector3i, // x, y, z of the block
    val face: BlockFace
) : Action
