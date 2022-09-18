package com.github.bedrockecs.server.game.db.dimension

import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent

/**
 * [DimensionStore], but its content can be mutated
 */
interface MutableDimensionStore : DimensionStore {
    /**
     * creates a new dimension, might have minimum component requirements
     */
    fun create(components: Set<DimensionComponent>): Short

    /**
     * destroy the dimension, also removes everything inside it
     */
    fun destroy(pos: Short)
}
