package com.github.bedrockecs.server.game.db.world.serial

import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.world.data.ChunkComponent

/**
 * represents the serial form of a chunk
 */
data class SerialChunk(
    /**
     * components attached to this chunk
     */
    val components: ComponentMap<ChunkComponent>,
    /**
     * ordered by value of y
     */
    val subChunks: List<SerialSubChunk>,
    /**
     * initial y value
     */
    val subChunksInitialY: Int
)
