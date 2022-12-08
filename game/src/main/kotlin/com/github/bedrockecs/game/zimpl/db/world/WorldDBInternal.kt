package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.world.WorldDB
import java.util.concurrent.CompletableFuture

/**
 * unstable internal API for [WorldDB]
 */
interface WorldDBInternal : WorldDB {
    // region loading

    /**
     * check is the chunk loaded
     */
    fun isLoaded(pos: ChunkPosition): Boolean

    /**
     * list all loaded chunks in the database
     */
    fun listLoadedChunks(): Collection<ChunkPosition>

    /**
     * try to load a chunk from storage
     */
    fun loadChunk(pos: ChunkPosition): CompletableFuture<Void>

    /**
     * try to unload a chunk to storage
     */
    fun unloadChunk(pos: ChunkPosition): CompletableFuture<Void>

    // endregion

    // region tick

    /**
     * execute per-tick cleanup tasks, such as evicting not loaded chunks
     */
    fun tick()

    // endregion
}
