package com.github.bedrockecs.game.io.action

import java.util.UUID

/**
 * represents a player changing its selected slot in hotbar
 */
data class PlayerHotBarSelectSlotAction(
    val player: UUID,
    val selectedSlot: Int
) : Action
