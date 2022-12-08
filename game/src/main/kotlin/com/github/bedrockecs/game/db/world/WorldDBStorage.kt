package com.github.bedrockecs.game.db.world

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.world.serial.SerialChunk
import com.github.bedrockecs.game.zimpl.db.world.EmptyChunkGenerator
import com.github.bedrockecs.game.zimpl.db.world.InMemoryWorldDBStorage
import java.util.concurrent.CompletableFuture

/**
 * backend storage for [WorldDB]
 */
interface WorldDBStorage {
    companion object {
        /**
         * mock storage that keeps everything in memory
         */
        fun inMemory(
            heightRange: Pair<Int, Int>,
            generator: (ChunkPosition) -> SerialChunk
        ): WorldDBStorage {
            return InMemoryWorldDBStorage(heightRange, generator)
        }

        /**
         * mock storage that keeps everything in memory and generates empty chunk
         */
        fun inMemoryEmpty(heightRange: Pair<Int, Int>, serial: WorldDBSerial): WorldDBStorage {
            val generator = EmptyChunkGenerator(heightRange, serial)
            return inMemory(heightRange, generator::generate)
        }
    }

    /**
     * read a chunk from storage
     */
    fun readChunk(pos: ChunkPosition): CompletableFuture<SerialChunk>

    /**
     * write a chunk to storage
     */
    fun writeChunk(pos: ChunkPosition, chunk: SerialChunk): CompletableFuture<Void>
}
