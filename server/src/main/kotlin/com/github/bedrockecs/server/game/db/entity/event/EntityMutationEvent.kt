package com.github.bedrockecs.server.game.db.entity.event

import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityComponent
import com.github.bedrockecs.server.game.eventbus.Event

/**
 * represents changes related to an entity component
 *
 * ## Related: Loading/Unloading
 * we are not in charge of load/unloading, see [EntityLoadingEvent] for that
 *
 * ## Event: Dispatch Token
 * [String], being the value of [EntityComponent.type] of respective component type
 *
 * ## Event: Invocation Timing
 * * [MutateType.ADD] are called after added, either by create() or later added by mutate()
 * * [MutateType.UPDATE] are called after updated,
 * * [MutateType.REMOVE] are called before the component or the entity it is attached to is removed
 */
data class EntityMutationEvent(
    val eid: EntityID,
    val type: MutateType
) : Event
