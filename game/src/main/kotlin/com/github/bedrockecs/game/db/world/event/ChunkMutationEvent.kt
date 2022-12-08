package com.github.bedrockecs.game.db.world.event

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.world.data.ChunkComponent
import com.github.bedrockecs.game.eventbus.Event

/**
 * represents changes related to [ChunkComponent]
 * similar to [BlockMutationEvent], same as their contract
 */
data class ChunkMutationEvent(
    val pos: ChunkPosition,
    val type: MutateType
) : Event
