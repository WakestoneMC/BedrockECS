package com.github.bedrockecs.game.db.invitem.event

import com.github.bedrockecs.game.db.common.LoadType
import com.github.bedrockecs.game.db.invitem.InvRef
import com.github.bedrockecs.game.eventbus.Event

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
