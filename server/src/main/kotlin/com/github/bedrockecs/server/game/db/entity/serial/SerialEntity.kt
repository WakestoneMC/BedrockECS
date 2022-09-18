package com.github.bedrockecs.server.game.db.entity.serial

import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityComponent

/**
 * serialized form of entity
 */
data class SerialEntity(
    val id: EntityID,
    val components: Map<Class<in EntityComponent>, EntityComponent>
)
