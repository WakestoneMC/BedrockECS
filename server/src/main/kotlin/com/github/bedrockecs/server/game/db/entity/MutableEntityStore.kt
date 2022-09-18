package com.github.bedrockecs.server.game.db.entity

import com.github.bedrockecs.server.game.data.BlockPosition
import com.github.bedrockecs.server.game.db.entity.data.EntityComponent
import com.github.bedrockecs.server.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.server.game.db.world.data.BlockComponent

interface MutableEntityStore : EntityStore {
    /**
     * creates a new entity
     * @param type: type of the entity, cannot be changed once the entity is created
     * @param extras: extra initial components to add to entity, as instance.getClass() -> instance map
     * @return id of new entity
     */
    fun create(
        type: EntityTypeComponent,
        extras: MutableSet<EntityComponent>
    ): EntityID

    /**
     * remove the entity, no-op if the entity does not exist
     */
    fun destroy(id: EntityID)

    /**
     * modify a component of the entity
     * @param id id of the entity
     * @param clazz concrete component type of the component to mutate
     * @param func modifying callback, returns the updated component.
     *      gets null if not exists, returns null to remove the component
     * @param func modifying callback, gets null if not exists, returns null to remove the component
     */
    fun <T : EntityComponent> mutate(id: EntityID, clazz: Class<T>, func: (T?) -> T?)

    /**
     * [EntityStore.scan], but supports mutating components
     * @param config configuration for this scan
     * @param components array of concrete component type for component to mutate in the callback
     * @param callback mutation callback, takes components & returns modified array of components
     * @throws UnsupportedOperationException if the implementation does not support it
     */
    fun <T> mutatingScan(
        config: EntityScanConfig,
        components: Array<Class<out EntityComponent>>,
        callback: (BlockPosition, Array<BlockComponent>) -> Array<BlockComponent?>
    ): Collection<T> {
        throw UnsupportedOperationException()
    }
}
