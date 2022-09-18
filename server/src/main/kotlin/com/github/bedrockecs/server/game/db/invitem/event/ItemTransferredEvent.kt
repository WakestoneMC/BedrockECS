package com.github.bedrockecs.server.game.db.invitem.event

import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.invitem.InvSlotRef
import com.github.bedrockecs.server.game.db.invitem.data.ItemTypeComponent
import com.github.bedrockecs.server.game.eventbus.Event

/**
 * represents transfer of an item across inventories
 *
 * ## Event: Dispatch Token
 * [String], being the value of [ItemTypeComponent.itemType] for the item
 *
 * ## Event: Invocation Timing
 * called after the transfer takes place
 */
data class ItemTransferredEvent(
    val ref: InvSlotRef,
    val type: MutateType
) : Event
