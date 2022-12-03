package com.github.bedrockecs.game.db.entity.data

/**
 * represents the type of the entity
 */
data class EntityTypeComponent(
    /**
     * the entity type it represents, should be unique
     */
    val entityType: String
) : EntityComponent {
    override val type: String
        get() = "becs:entity_type"

    override fun clone(): EntityTypeComponent {
        return this // because everything is immutable here
    }
}
