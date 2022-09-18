package com.github.bedrockecs.server.game.db.world.event

import com.github.bedrockecs.server.game.data.SubChunkPosition
import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.server.game.eventbus.Event

/**
 * represents changes related to [SubChunkComponent]
 * similar to [BlockMutationEvent], same as their contract
 */
data class SubChunkMutationEvent(
    val pos: SubChunkPosition,
    val type: MutateType
) : Event
