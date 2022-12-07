package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.db.entity.EntityDB
import com.github.bedrockecs.game.db.entity.EntityID
import java.util.concurrent.CompletableFuture

/**
 * unstable internal API for [EntityDB]
 */
interface EntityDBInternal : EntityDB {
    // region loading

    /**
     * check is the entity loaded
     */
    fun isLoaded(eid: EntityID): Boolean

    /**
     * list all loaded entities in the database
     */
    fun listLoadedEntities(): Collection<EntityID>

    /**
     * try to load a chunk from storage
     */
    fun loadEntity(eid: EntityID): CompletableFuture<Void>

    /**
     * try to unload a chunk to storage
     */
    fun unloadEntity(eid: EntityID): CompletableFuture<Void>

    // endregion

    // region tick

    /**
     * execute per-tick cleanup tasks, such as evicting not loaded entities
     */
    fun tick()

    // endregion
}
