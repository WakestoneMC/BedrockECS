package com.github.bedrockecs.game.db.entity

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.entity.serial.SerialEntity
import com.github.bedrockecs.game.zimpl.db.entity.InMemoryEntityDBStorage
import java.util.UUID
import java.util.concurrent.CompletableFuture

/**
 * backend storage for [EntityDB]
 */
interface EntityDBStorage {
    companion object {
        /**
         * mock storage that keeps everything in memory
         */
        fun inMemory(): EntityDBStorage {
            return InMemoryEntityDBStorage()
        }
    }

    // region CRUD

    /**
     * allocates an ID for new entity
     */
    fun allocateEntity(): CompletableFuture<EntityID>

    /**
     * reads entity from storage
     */
    fun readEntity(id: EntityID): CompletableFuture<SerialEntity?>

    /**
     * writes entity to storage
     */
    fun writeEntity(entity: SerialEntity): CompletableFuture<Void>

    /**
     * removes the entity from backend store, also frees the ID
     */
    fun removeEntity(id: EntityID): CompletableFuture<Void>

    // endregion

    // region queries

    /**
     * returns a set of entity IDs that belongs to the chunk, meaning they should be loaded together
     */
    fun listEntitiesInChunk(pos: ChunkPosition): CompletableFuture<Set<EntityID>>

    /**
     * try to find a player's entity id using its name from storage
     */
    fun findEntityByPlayerName(name: String): CompletableFuture<EntityID?>

    /**
     * try to find a player's entity id using its uuid from storage
     */
    fun findEntityByPlayerUUID(uuid: UUID): CompletableFuture<EntityID?>

    // endregion
}
