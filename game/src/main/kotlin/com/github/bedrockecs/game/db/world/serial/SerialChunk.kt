package com.github.bedrockecs.game.db.world.serial

import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.world.data.ChunkComponent

/**
 * represents the serial form of a chunk
 */
data class SerialChunk(
    /**
     * components attached to this chunk
     */
    val components: ComponentMap<ChunkComponent> = emptyMap(),
    /**
     * ordered by value of y
     */
    val subChunks: List<SerialSubChunk>,
    /**
     * the Y value of the lowest block in chunk
     */
    val initialY: Int
)
