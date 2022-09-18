package com.github.bedrockecs.server.game.db.dimension.event

import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent
import com.github.bedrockecs.server.game.db.world.event.BlockMutationEvent
import com.github.bedrockecs.server.game.eventbus.Event

/**
 * represents changes related to [DimensionComponent]
 * similar to [BlockMutationEvent], same as their contract
 */
data class DimensionMutationEvent(
    val pos: Short,
    val type: MutateType
) : Event
