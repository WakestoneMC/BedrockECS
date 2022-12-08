package com.github.bedrockecs.game.db.entity.event

import com.github.bedrockecs.game.db.common.LoadType
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.game.eventbus.Event

/**
 * represents entity loading/unloading
 *
 * ## Event: Dispatch Token
 * [String], being the value of [EntityTypeComponent.entityType] for the entity
 *
 * ## Event: Invocation Timing
 * * [LoadType.LOAD] are called after loaded,
 * * [LoadType.UNLOAD] are called before unloaded
 */
data class EntityLoadingEvent(
    val eid: EntityID,
    val type: LoadType
) : Event
