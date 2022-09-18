package com.github.bedrockecs.server.game.db

import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityComponent

/**
 * kernel APIs of [GameStorageContext], KERNEL USE ONLY!
 */
interface GameStorageContextInternal : GameStorageContext {

    // initial load //

    data class InitialLoad(
        val dimensions: Map<Short, Set<DimensionComponent>>,
        val globalEntity: Set<EntityComponent>
    )

    /**
     * performs initial load, used to init the database
     */
    fun initialLoad(): InitialLoad

    // dimension handler //

    fun onCreatingDimension(id: Short, components: Set<DimensionComponent>)

    fun onDimensionComponentUpdated(id: Short, from: DimensionComponent?, to: DimensionComponent?)

    fun onDimensionDestroyed(id: Short, components: Set<DimensionComponent>)

    // entity ID Allocator //

    /**
     * allocate a list of ids from storage
     */
    fun allocateID(): List<EntityID>

    /**
     * release a list of ids to storage
     */
    fun releaseID(ids: List<EntityID>)
}
