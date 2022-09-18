package com.github.bedrockecs.server.game.db.world.event

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.common.LoadType
import com.github.bedrockecs.server.game.eventbus.Event

/**
 * represents chunk loading/unloading
 *
 * ## Event: Dispatch Token
 * no dispatch token, keep it null
 *
 * ## Event: Invocation Timing
 * * [LoadType.LOAD] are called after loaded,
 * * [LoadType.UNLOAD] are called before unloaded
 */
data class ChunkLoadingEvent(
    val pos: ChunkPosition,
    val type: LoadType
) : Event
