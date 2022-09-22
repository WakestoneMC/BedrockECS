package com.github.bedrockecs.server.game.db

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.serial.SerialEntity
import com.github.bedrockecs.server.game.db.invitem.serial.SerialInventory
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import java.util.UUID
import java.util.concurrent.CompletableFuture

/**
 * low-level storage backend for [GameDB], provide this in game container to work
 */
interface GameStorageContext {

    // chunk IO //

    /**
     * read a chunk from storage
     */
    fun readChunk(pos: ChunkPosition): CompletableFuture<SerialChunk>

    /**
     * returns a set of entity IDs that belongs to the chunk, meaning they should be loaded together
     */
    fun readEntitiesInChunk(pos: ChunkPosition): CompletableFuture<Set<EntityID>>

    /**
     * write a chunk to storage
     */
    fun writeChunk(pos: ChunkPosition, chunk: SerialChunk): CompletableFuture<Void>

    // entity IO //

    /**
     * entity, and its related inventory
     */
    data class SerialInvEntity(
        val entity: SerialEntity,
        val inventories: Set<SerialInventory>
    )

    /**
     * read an entity(and associated inventory) from storage
     */
    fun readEntity(id: EntityID): CompletableFuture<SerialInvEntity>

    /**
     * write an entity(and associated inventory) to storage
     */
    fun writeEntity(entity: SerialInvEntity): CompletableFuture<Void>

    // player queries //

    /**
     * try to find a player's entity id using its name from storage
     */
    fun entityForPlayerName(name: String): CompletableFuture<EntityID?>

    /**
     * try to find a player's entity id using its uuid from storage
     */
    fun entityForPlayerUUID(uuid: UUID): CompletableFuture<EntityID?>
}
