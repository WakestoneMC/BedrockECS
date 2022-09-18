package com.github.bedrockecs.server.game.db.world

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.LayeredBlockPosition
import com.github.bedrockecs.server.game.data.SubChunkPosition
import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.server.game.db.world.data.ChunkComponent
import com.github.bedrockecs.server.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.server.game.db.world.op.BlockOp
import com.github.bedrockecs.server.game.db.world.op.MutateBlockOp

/**
 * [WorldStore], but its content can be mutated
 */
interface MutableWorldStore : WorldStore {

    // chunk-subchunk metadata hierarchy //

    /**
     * modify a component on the chunk, similar to [mutateBlock]
     */
    fun <T : ChunkComponent> mutateChunk(pos: ChunkPosition, clazz: Class<T>, func: (T?) -> T?)

    /**
     * modify a component on the subchunk, similar to [mutateBlock] */
    fun <T : SubChunkComponent> mutateSubChunk(pos: SubChunkPosition, clazz: Class<T>, func: (T?) -> T?)

    // block operations //

    /**
     * place a block at specified location, replacing the old one
     * @param pos: location of the block
     * @param type: type of the new block, cannot be mutated afterwards without calling [place] again
     * @param state: block state of the new block
     * @param extras: extra components to put in addition to default components.
     *      instance.getClass() -> instance map, place null to remove default components
     * @throws ChunkNotLoadedException if the corresponding chunk is not loaded
     */
    fun placeBlock(
        pos: LayeredBlockPosition,
        type: BlockTypeComponent,
        extras: ComponentMap<BlockComponent>
    )

    /**
     * modify a component on the block
     * @param pos location of the block
     * @param clazz concrete component type (or [BlockTypeComponent]) of the component to mutate
     * @param func modifying callback, returns the updated component.
     *      gets null if not exists, returns null to remove the component
     * @throws ChunkNotLoadedException if the corresponding chunk is not loaded
     */
    fun <T : BlockComponent> mutateBlock(pos: LayeredBlockPosition, clazz: Class<T>, func: (T?) -> T?)

    /**
     * [WorldStore.execute], but accepts mutating operation as well
     * @throws UnsupportedOperationException if the implementation does not support it
     */
    fun <R : BlockOp.Result> execute(op: MutateBlockOp): R {
        throw UnsupportedOperationException()
    }
}
