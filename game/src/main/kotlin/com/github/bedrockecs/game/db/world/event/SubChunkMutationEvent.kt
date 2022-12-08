package com.github.bedrockecs.game.db.world.event

import com.github.bedrockecs.game.data.SubChunkPosition
import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.game.eventbus.Event

/**
 * represents changes related to [SubChunkComponent]
 * similar to [BlockMutationEvent], same as their contract
 */
data class SubChunkMutationEvent(
    val pos: SubChunkPosition,
    val type: MutateType
) : Event
