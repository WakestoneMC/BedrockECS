package com.github.bedrockecs.game.db.entity

import com.github.bedrockecs.game.db.entity.data.EntityComponent
import com.github.bedrockecs.game.db.entity.data.EntityTypeComponent

/**
 * database for all entity(player/mob/metadata holder/etc......) data
 */
interface EntityDB {
    // region CRUD

    /**
     * creates a new entity
     * @param type: type of the entity, cannot be changed once the entity is created
     * @param extras: extra initial components to add to entity
     * @return id of new entity
     */
    fun create(
        type: EntityTypeComponent,
        extras: Set<EntityComponent>
    ): EntityID

    /**
     * list all components the entity has
     */
    fun list(id: EntityID): Collection<EntityComponent>

    /**
     * try to read a component from the entity
     * @return the component, returns null if not exists
     */
    fun <T : EntityComponent> read(id: EntityID, clazz: Class<T>): T?

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
     * remove the entity, no-op if the entity does not exist
     */
    fun destroy(id: EntityID)

    // endregion

    // region scanning

    /**
     * iterate through entity and components it has
     * @param config configuration for this scan
     * @param components array of concrete component type for component to view in the callback
     * @param callback iteration callback
     * @throws UnsupportedOperationException if the implementation does not support it
     * @apiNote user is not expected to mutate the provided components, use [mutatingScan] for that!
     */
    fun scan(
        config: EntityScanConfig,
        components: Array<Class<out EntityComponent>>,
        callback: (EntityID, Array<EntityComponent>) -> Unit
    ) {
        throw UnsupportedOperationException()
    }

    /**
     * [scan], but supports mutating components
     * @param config configuration for this scan
     * @param components array of concrete component type for component to mutate in the callback
     * @param callback mutation callback, takes components & returns modified array of components
     * @throws UnsupportedOperationException if the implementation does not support it
     */
    fun mutatingScan(
        config: EntityScanConfig,
        components: Array<Class<out EntityComponent>>,
        callback: (EntityID, Array<EntityComponent>) -> Array<EntityComponent?>
    ) {
        throw UnsupportedOperationException()
    }

    // endregion
}
