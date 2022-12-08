package com.github.bedrockecs.game.db.world.event

import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.world.data.BlockComponent
import com.github.bedrockecs.game.eventbus.Event

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
data class BlockMutationEvent(
    val target: BlockTarget,
    val type: MutateType
) : Event
