package com.github.bedrockecs.server.game.db.world

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.LayeredBlockPosition
import com.github.bedrockecs.server.game.data.SubChunkPosition
import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.db.world.data.ChunkComponent
import com.github.bedrockecs.server.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.server.game.db.world.op.BlockOp

/**
 * represents an object that holds block information
 */
interface WorldStore {

    // chunk-subchunk metadata hierarchy //

    /**
     * try to get a component from the chunk, similar to [readBlock]
     */
    fun <T : ChunkComponent> readChunk(pos: ChunkPosition, clazz: Class<T>): T?

    /**
     * list all components the chunk has, similar to [listBlock]
     */
    fun listChunk(pos: ChunkPosition): Collection<ChunkComponent>

    /**
     * try to get a component from the subchunk, similar to [readBlock] */
    fun <T : SubChunkComponent> readSubChunk(pos: SubChunkPosition, clazz: Class<T>): T?

    /**
     * list all components the subchunk has, similar to [listBlock]
     */
    fun listSubChunk(pos: SubChunkPosition): Collection<SubChunkComponent>

    // block operations //

    /**
     * try to get a component from the block
     * @param pos location of the block
     * @param clazz concrete component type of the component to get
     * @throws ChunkNotLoadedException if the corresponding chunk is not loaded
     * @return the component, returns null if not exists
     */
    fun <T : BlockComponent> readBlock(pos: LayeredBlockPosition, clazz: Class<T>): T?

    /**
     * list all components the block has
     * @throws ChunkNotLoadedException if the corresponding chunk is not loaded
     */
    fun listBlock(pos: LayeredBlockPosition): Collection<BlockComponent>

    /**
     * perform batch operations on the store
     * @throws UnsupportedOperationException if the implementation does not support it
     * @apiNote user is not expected to mutate the provided components, use [MutableWorldStore.execute] for that!
     */
    fun <R : BlockOp.Result> execute(op: BlockOp): R {
        throw UnsupportedOperationException()
    }
}
