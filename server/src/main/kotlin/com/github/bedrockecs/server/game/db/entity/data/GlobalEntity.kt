package com.github.bedrockecs.server.game.db.entity.data

import com.github.bedrockecs.server.game.db.entity.EntityID

/**
 * the global singleton entity for storing global states
 *
 * ## Entity: Assigned EntityID
 * assigned to be 0
 */
object GlobalEntity {
    val TYPE = EntityTypeComponent("becs:global")
    val ID = EntityID(0)
}
