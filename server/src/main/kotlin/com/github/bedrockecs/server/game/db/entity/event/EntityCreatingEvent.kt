package com.github.bedrockecs.server.game.db.entity.event

import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.MutableEntityStore
import com.github.bedrockecs.server.game.db.entity.data.EntityComponent
import com.github.bedrockecs.server.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.server.game.eventbus.Event

/**
 * used to add extra components in the process of creating new entities
 *
 * ## Event: Dispatch Token
 * [String], being the value of [EntityTypeComponent.entityType] for the entity
 *
 * ## Event: Invocation Timing
 * called when trying to add entity via [MutableEntityStore.create], before all [EntityMutationEvent] caused by the call
 */
data class EntityCreatingEvent(
    val eid: EntityID,
    val type: EntityTypeComponent,
    val extra: MutableSet<EntityComponent>
) : Event
