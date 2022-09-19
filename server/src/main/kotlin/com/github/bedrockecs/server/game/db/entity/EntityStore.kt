package com.github.bedrockecs.server.game.db.entity

import com.github.bedrockecs.server.game.db.entity.data.EntityComponent

/**
 * represents an object that holds ECS entity information
 */
interface EntityStore {
    /**
     * try to read a component from the entity
     * @return the component, returns null if not exists
     */
    fun <T : EntityComponent> read(id: EntityID, clazz: Class<T>): T?

    /**
     * list all components the entity has
     */
    fun list(id: EntityID): Collection<EntityComponent>

    /**
     * iterate through entity and components it has
     * @param config configuration for this scan
     * @param components array of concrete component type for component to view in the callback
     * @param callback iteration callback
     * @throws UnsupportedOperationException if the implementation does not support it
     * @apiNote user is not expected to mutate the provided components, use [MutableEntityStore.mutatingScan] for that!
     */
    fun scan(
        config: EntityScanConfig,
        components: Array<Class<out EntityComponent>>,
        callback: (EntityID, Array<EntityComponent>) -> Unit
    ) {
        throw UnsupportedOperationException()
    }
}
