package com.github.bedrockecs.game.db.entity

/**
 * id of an entity
 * ## Persistence
 * EntityID is persistent, aka an entity will always have the same entity id cross server restarts
 */
@JvmInline
value class EntityID(
    val value: Int
) {
    companion object {
        /**
         * global singleton entity for storing global(dimensional-level) state
         */
        val GLOBAL = EntityID(0)
    }
}
