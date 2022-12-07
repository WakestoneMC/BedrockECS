package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.entity.EntityDB
import com.github.bedrockecs.game.db.entity.EntityID
import java.util.UUID
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

    // region queries

    /**
     * returns a set of entity IDs that belongs to the chunk, meaning they should be loaded together
     */
    fun listEntitiesInChunk(pos: ChunkPosition): CompletableFuture<Set<EntityID>>

    // endregion

    // region tick

    /**
     * execute per-tick cleanup tasks, such as evicting not loaded entities
     */
    fun tick()

    // endregion

    // region player query

    /**
     * try to find a player's entity id using its name
     */
    fun findEntityByPlayerName(name: String): EntityID?

    /**
     * try to find a player's entity id using its uuid
     */
    fun findEntityByPlayerUUID(uuid: UUID): EntityID?

    // endregion
}
