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

    /**
     * modify a component on the dimension * @param pos dimension id
     * @param clazz concrete component type of the component to mutate
     * @param func modifying callback, returns the updated component.
     *      gets null if not exists, returns null to remove the component
     */
    fun <T : DimensionComponent> mutate(pos: Short, clazz: Class<T>, func: (T?) -> T?)
}
