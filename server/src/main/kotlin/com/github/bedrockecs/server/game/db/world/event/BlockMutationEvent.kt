package com.github.bedrockecs.server.game.db.world.event

import com.github.bedrockecs.server.game.data.LayeredBlockPosition
import com.github.bedrockecs.server.game.data.LayeredSubChunkPosition
import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.eventbus.Event
import java.util.BitSet

/**
 * represents changes related to a block component
 *
 * ## Related: Loading/Unloading
 * we are not in charge of load/unloading, see [ChunkLoadingEvent] for that
 *
 * ## Event: Dispatch Token
 * [String], being the value of [BlockComponent.type] of respective component type
 *
 * ## Event: Invocation Timing
 * * [MutateType.ADD] are called after added,
 * * [MutateType.UPDATE] are called after updated,
 * * [MutateType.REMOVE] are called before removed
 */
sealed class BlockMutationEvent : Event {

    abstract val type: MutateType

    /**
     * represents a single block is being mutated
     */
    data class Single(
        val pos: LayeredBlockPosition,
        override val type: MutateType
    ) : BlockMutationEvent()

    /**
     * represents multiple blocks inside a subchunk are being mutated,
     * [Batched.mask] is a bitset covering all the blocks, =1 means this block has been mutated
     */
    data class Batched(
        val pos: LayeredSubChunkPosition,
        val mask: BitSet, // TODO: switch to something better
        override val type: MutateType
    ) : BlockMutationEvent()
}
