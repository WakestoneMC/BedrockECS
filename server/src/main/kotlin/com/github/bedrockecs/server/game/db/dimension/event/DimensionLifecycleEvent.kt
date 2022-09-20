package com.github.bedrockecs.server.game.db.dimension.event

import com.github.bedrockecs.server.game.db.common.LifecycleType
import com.github.bedrockecs.server.game.eventbus.Event

/**
 * represents a dimension getting created / destroyed
 *
 * ## Event: Dispatch Token
 * no dispatch token, keep it null
 *
 * ## Event: Invocation Timing
 * * [LifecycleType.CREATE] are called after created,
 * * [LifecycleType.DESTROY] are called before destroyed
 */
data class DimensionLifecycleEvent(
    val pos: Short,
    val type: LifecycleType
) : Event
