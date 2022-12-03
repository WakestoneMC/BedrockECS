package com.github.bedrockecs.game.db.invitem

import com.github.bedrockecs.game.db.entity.EntityID

/**
 * represents a reference to an item slot inside an inventory
 */
data class InvSlotRef(
    val owner: EntityID,
    val name: String,
    val slot: Int
)
