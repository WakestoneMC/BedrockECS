package com.github.bedrockecs.game.db.world

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.data.LayeredBlockPosition
import com.github.bedrockecs.game.data.SubChunkPosition
import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.world.data.BlockComponent
import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.game.db.world.data.ChunkComponent
import com.github.bedrockecs.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.game.db.world.op.BlockOp
import com.github.bedrockecs.game.db.world.serial.SerialChunk

/**
 * database for all world(block+chunk/subchunk metadata) data
 */
interface WorldDB {

    // region chunk-subchunk metadata

    /**
     * list all components the chunk has, similar to [listBlock]
     */
    fun listChunk(pos: ChunkPosition): Collection<ChunkComponent>

    /**
     * try to get a component from the chunk, similar to [readBlock]
     */
    fun <T : ChunkComponent> readChunk(pos: ChunkPosition, clazz: Class<T>): T?

    /**
     * modify a component on the chunk, similar to [mutateBlock]
     */
    fun <T : ChunkComponent> mutateChunk(pos: ChunkPosition, clazz: Class<T>, func: (T?) -> T?)

    /**
     * list all components the subchunk has, similar to [listBlock]
     */
    fun listSubChunk(pos: SubChunkPosition): Collection<SubChunkComponent>

    /**
     * try to get a component from the subchunk, similar to [readBlock] */
    fun <T : SubChunkComponent> readSubChunk(pos: SubChunkPosition, clazz: Class<T>): T?

    /**
     * modify a component on the subchunk, similar to [mutateBlock]
     */
    fun <T : SubChunkComponent> mutateSubChunk(pos: SubChunkPosition, clazz: Class<T>, func: (T?) -> T?)

    // endregion

    // region block CRUD

    /**
     * place a block at specified location, replacing the old one
     * @param pos: location of the block
     * @param type: type of the new block, cannot be mutated afterwards without calling [place] again
     * @param state: block state of the new block
     * @param extras: extra components to put in addition to default components.
     *      instance.getClass() -> instance map, place null to remove default components
     */
    fun placeBlock(
        pos: LayeredBlockPosition,
        type: BlockTypeComponent,
        extras: ComponentMap<BlockComponent>
    )

    /**
     * list all components the block has
     */
    fun listBlock(pos: LayeredBlockPosition): Collection<BlockComponent>

    /**
     * try to get a component from the block
     * @param pos location of the block
     * @param clazz concrete component type of the component to get
     * @return the component, returns null if not exists
     */
    fun <T : BlockComponent> readBlock(pos: LayeredBlockPosition, clazz: Class<T>): T?

    /**
     * modify a component on the block
     * @param pos location of the block
     * @param clazz concrete component type (or [BlockTypeComponent]) of the component to mutate
     * @param func modifying callback, returns the updated component.
     *      gets null if not exists, returns null to remove the component
     */
    fun <T : BlockComponent> mutateBlock(pos: LayeredBlockPosition, clazz: Class<T>, func: (T?) -> T?)

    // endregion

    // region operations

    /**
     * perform batch operations on the store
     * @throws UnsupportedOperationException if the implementation does not support it
     */
    fun <R : BlockOp.Result> execute(op: BlockOp): R {
        throw UnsupportedOperationException()
    }

    // endregion

    // region serialization

    /**
     * get the serialized form of the chunk
     */
    fun serialize(pos: ChunkPosition, includeComponents: Boolean = false): SerialChunk

    // endregion
}
