package com.github.bedrockecs.game.db.invitem.event

import com.github.bedrockecs.game.db.common.LifecycleType
import com.github.bedrockecs.game.db.invitem.data.InventoryMetadata
import com.github.bedrockecs.game.eventbus.Event

/**
 * represents the creation and disposal of inventory
 *
 * ## Event: Dispatch Token
 * no dispatch token, keep it null
 *
 * ## Event: Invocation Timing
 * invoked after creation or before destroy
 */
data class InventoryLifecycleEvent(
    val metadata: InventoryMetadata,
    val type: LifecycleType
) : Event
