package com.github.bedrockecs.server.game.db.world.event

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.world.data.ChunkComponent
import com.github.bedrockecs.server.game.eventbus.Event

/**
 * represents changes related to [ChunkComponent]
 * similar to [BlockMutationEvent], same as their contract
 */
data class ChunkMutationEvent(
    val pos: ChunkPosition,
    val type: MutateType
) : Event
