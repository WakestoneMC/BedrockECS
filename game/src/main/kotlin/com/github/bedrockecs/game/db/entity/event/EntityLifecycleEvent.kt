package com.github.bedrockecs.game.db.entity.event

import com.github.bedrockecs.game.db.common.LifecycleType
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.game.eventbus.Event

/**
 * represents an entity getting created / destroyed
 *
 * ## Event: Dispatch Token
 * [String], being the value of [EntityTypeComponent.entityType] for the entity
 *
 * ## Event: Invocation Timing
 * * [LifecycleType.CREATE] are called after created,
 * * [LifecycleType.DESTROY] are called before destroyed
 */
data class EntityLifecycleEvent(
    val pos: EntityID,
    val type: LifecycleType
) : Event
