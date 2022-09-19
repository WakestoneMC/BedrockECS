package com.github.bedrockecs.server.game.db.invitem

import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.invitem.data.InventoryMetadata
import com.github.bedrockecs.server.game.db.invitem.data.ItemComponent
import com.github.bedrockecs.server.game.db.invitem.data.ItemTypeComponent

/**
 * a mutable store of items
 */
interface MutableInvItemStore : InvItemStore {
    /**
     * creates a new inventory
     */
    fun create(metadata: InventoryMetadata): InvRef

    /**
     * destroy an existing inventory
     */
    fun destroy(ref: InvRef)

    /**
     * place an item into the specified slot, replacing the old one
     * @param slot: slot
     * @param type: type of the new item, cannot be mutated afterwards without calling [place] again
     * @param extras: extra components to put in addition to default components.
     *      instance.getClass() -> instance map, place null to remove default components
     */
    fun place(slot: InvSlotRef, type: ItemTypeComponent, extras: ComponentMap<ItemComponent?>)

    /**
     * clear an item in the specified slot
     */
    fun clear(slot: InvSlotRef)

    /**
     * transfer an item from one inventory to another, replacing what was in the to slot
     */
    fun transfer(from: InvSlotRef, to: InvSlotRef)

    /**
     * modify a component of an item
     * @param slot slot of the item
     * @param clazz concrete component type of the component to mutate
     * @param func modifying callback, returns the updated component.
     *      gets null if not exists, returns null to remove the component
     * @param func modifying callback, gets null if not exists, returns null to remove the component
     */
    fun <T : ItemComponent> mutate(slot: InvSlotRef, clazz: Class<T>, func: (T?) -> T?)

    /**
     * [scan], but supports mutating components
     * @param config configuration for this scan
     * @param components array of concrete component type for component to mutate in the callback
     * @param callback mutation callback, takes components & returns modified array of components
     * @throws UnsupportedOperationException if the implementation does not support it
     */
    fun mutatingScan(
        config: ItemScanConfig,
        components: Array<Class<out ItemComponent>>,
        callback: (slot: Int, components: Array<ItemComponent>) -> Array<ItemComponent?>
    ) {
        throw UnsupportedOperationException()
    }
}
