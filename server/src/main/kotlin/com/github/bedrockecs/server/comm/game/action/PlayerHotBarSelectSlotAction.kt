package com.github.bedrockecs.server.comm.game.action

import java.util.UUID

/**
 * represents a player changing its selected slot in hotbar
 */
data class PlayerHotBarSelectSlotAction(
    val player: UUID,
    val selectedSlot: Int
) : Action
