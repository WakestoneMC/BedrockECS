package com.github.bedrockecs.server.game.db.invitem.event

import com.github.bedrockecs.server.game.db.common.LoadType
import com.github.bedrockecs.server.game.db.invitem.InvRef
import com.github.bedrockecs.server.game.eventbus.Event

/**
 * represents inventory loading/unloading
 *
 * ## Event: Dispatch Token
 * no dispatch token, keep it null
 *
 * ## Event: Invocation Timing
 * * [LoadType.LOAD] are called after loaded,
 * * [LoadType.UNLOAD] are called before unloaded
 */
data class InventoryLoadingEvent(
    val ref: InvRef,
    val type: LoadType
) : Event
