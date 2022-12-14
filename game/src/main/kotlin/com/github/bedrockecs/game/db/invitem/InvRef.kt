package com.github.bedrockecs.game.db.invitem

import com.github.bedrockecs.game.db.entity.EntityID

/**
 * represents a reference to an inventory that belongs to an entity
 */
data class InvRef(
    val owner: EntityID,
    val name: String
)
