package com.github.bedrockecs.game.db.world.event

import com.github.bedrockecs.game.data.LayeredBlockPosition
import com.github.bedrockecs.game.data.LayeredSubChunkPosition
import java.util.BitSet

sealed class BlockTarget {
    /**
     * represents a single block is being mutated
     */
    data class Single(
        val pos: LayeredBlockPosition
    ) : BlockTarget()

    /**
     * represents multiple blocks inside a subchunk are being mutated,
     * [Batched.mask] is a bitset covering all the blocks, =1 means this block has been mutated
     */
    data class Batched(
        val pos: LayeredSubChunkPosition,
        val mask: BitSet // TODO: switch to something better
    ) : BlockTarget()
}
