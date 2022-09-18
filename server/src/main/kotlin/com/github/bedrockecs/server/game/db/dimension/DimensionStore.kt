package com.github.bedrockecs.server.game.db.dimension

import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent

/**
 * represents an object that holds dimension information
 */
interface DimensionStore {
    /**
     * try to get a component from the dimension
     */
    fun <T : DimensionComponent> read(id: Short, clazz: Class<T>): T?

    /**
     * list all components the dimension has
     */
    fun list(id: Short): Collection<DimensionComponent>
}
