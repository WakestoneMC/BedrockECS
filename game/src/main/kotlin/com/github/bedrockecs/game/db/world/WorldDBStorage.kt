package com.github.bedrockecs.game.db.world

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.world.serial.SerialChunk
import java.util.concurrent.CompletableFuture

/**
 * backend storage for [WorldDB]
 */
interface WorldDBStorage {
    /**
     * read a chunk from storage
     */
    fun readChunk(pos: ChunkPosition): CompletableFuture<SerialChunk>

    /**
     * write a chunk to storage
     */
    fun writeChunk(pos: ChunkPosition, chunk: SerialChunk): CompletableFuture<Void>
}
