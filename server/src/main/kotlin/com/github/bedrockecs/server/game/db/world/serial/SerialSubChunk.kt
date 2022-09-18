package com.github.bedrockecs.server.game.db.world.serial

import com.github.bedrockecs.server.game.db.world.data.SubChunkComponent

/**
 * represents the serial form of a subchunk
 */
data class SerialSubChunk(
    /**
     * components attached to this subchunk
     */
    val components: Map<Class<out SubChunkComponent>, SubChunkComponent>,
    /**
     * ordered by layer
     */
    val layers: List<SerialSubChunkLayer>
)
