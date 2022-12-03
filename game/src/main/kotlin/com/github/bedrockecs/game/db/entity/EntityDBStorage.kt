package com.github.bedrockecs.game.db.entity

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.entity.serial.SerialEntity
import java.util.UUID
import java.util.concurrent.CompletableFuture

/**
 * backend storage for [EntityDB]
 */
interface EntityDBStorage {
    // region CRUD

    /**
     * allocates an ID for new entity
     */
    fun allocateEntity(): CompletableFuture<EntityID>

    /**
     * removes the entity from backend store, also frees the ID
     */
    fun removeEntity(id: EntityID): CompletableFuture<Void>

    /**
     * writes entity to storage
     */
    fun writeEntity(entity: SerialEntity): CompletableFuture<EntityID>

    /**
     * reads entity from storage
     */
    fun readEntity(id: EntityID): CompletableFuture<SerialEntity>

    // endregion

    // region queries

    /**
     * returns a set of entity IDs that belongs to the chunk, meaning they should be loaded together
     */
    fun listEntitiesInChunk(pos: ChunkPosition): CompletableFuture<Set<EntityID>>

    /**
     * try to find a player's entity id using its name from storage
     */
    fun entityForPlayerName(name: String): CompletableFuture<EntityID?>

    /**
     * try to find a player's entity id using its uuid from storage
     */
    fun entityForPlayerUUID(uuid: UUID): CompletableFuture<EntityID?>

    // endregion
}
