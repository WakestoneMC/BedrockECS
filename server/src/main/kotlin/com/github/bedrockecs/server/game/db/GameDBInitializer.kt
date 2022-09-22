package com.github.bedrockecs.server.game.db

import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent

/**
 * used to initialize [GameDB], KERNEL USE ONLY!
 */
interface GameDBInitializer {
    data class InitialLoad(val dimensions: Map<Short, Set<DimensionComponent>>)

    /**
     * performs initial load, used to init the database
     */
    fun initialLoad(): InitialLoad
}
