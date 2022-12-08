package com.github.bedrockecs.game.db.entity.serial

import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.entity.data.EntityComponent
import com.github.bedrockecs.game.db.entity.EntityID

/**
 * serialized form of entity
 */
data class SerialEntity(
    val id: EntityID,
    val components: ComponentMap<EntityComponent>
)
