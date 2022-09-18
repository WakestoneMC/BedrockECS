package com.github.bedrockecs.server.game.db.invitem.event

import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.invitem.InvSlotRef
import com.github.bedrockecs.server.game.db.invitem.data.ItemComponent
import com.github.bedrockecs.server.game.eventbus.Event

/**
 * represents changes related to an item component
 *
 * ## Related: Loading/Unloading
 * we are not in charge of load/unloading, see [InventoryLoadingEvent] for that
 *
 * ## Event: Dispatch Token
 * [String], being the value of [ItemComponent.type] of respective component type
 *
 * ## Event: Invocation Timing
 * * [MutateType.ADD] are called after added, either by place() or later added by mutate()
 * * [MutateType.UPDATE] are called after updated,
 * * [MutateType.REMOVE] are called before the component or the inventory it is attached to is removed
 */
data class ItemMutationEvent(
    val ref: InvSlotRef,
    val type: MutateType
) : Event
