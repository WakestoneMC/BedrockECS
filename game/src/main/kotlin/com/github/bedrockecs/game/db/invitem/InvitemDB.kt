package com.github.bedrockecs.game.db.invitem

import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.invitem.data.InventoryMetadata
import com.github.bedrockecs.game.db.invitem.data.ItemComponent
import com.github.bedrockecs.game.db.invitem.data.ItemTypeComponent

/**
 * database for all entity inventory & item data
 */
interface InvitemDB {
    // region inventory CRUD

    /**
     * creates a new inventory
     */
    fun create(metadata: InventoryMetadata): InvRef

    /**
     * read inventory metadata from inventory
     */
    fun readMetadata(ref: InvRef): InventoryMetadata

    /**
     * destroy an existing inventory
     */
    fun destroy(ref: InvRef)

    // endregion

    // region item CRUD

    /**
     * is the slot empty?
     */
    fun isEmpty(ref: InvSlotRef): Boolean

    /**
     * list all components the item in the slot has
     */
    fun list(ref: InvSlotRef): ComponentMap<ItemComponent>

    /**
     * try to read a component from the item at slot in the inventory
     * @return the component, returns null if not exists
     */
    fun <T : ItemComponent> read(ref: InvSlotRef, clazz: Class<T>): T?

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
     * put an item into the specified slot, replacing the old one
     * @param slot: slot
     * @param type: type of the new item, cannot be mutated afterwards without calling [place] again
     * @param extras: extra components to put in addition to default components.
     *      instance.getClass() -> instance map, place null to remove default components
     */
    fun place(slot: InvSlotRef, type: ItemTypeComponent, extras: ComponentMap<ItemComponent?>)

    /**
     * remove an item from the specified slot, making it empty
     * @param slot: slot
     * @return pair of type component and extras components
     */
    fun take(slot: InvSlotRef): Pair<ItemTypeComponent, ComponentMap<ItemComponent?>>?

    /**
     * clear an item in the specified slot
     */
    fun clear(slot: InvSlotRef)

    // endregion

    // region item scanning

    /**
     * iterate through items and components it has
     * @param config configuration for this scan
     * @param components array of concrete component type for component to view in the callback
     * @param callback iteration callback
     * @throws UnsupportedOperationException if the implementation does not support it
     * @apiNote user is not expected to mutate the provided components, use [mutatingScan] for that!
     */
    fun scan(
        config: ItemScanConfig,
        components: Array<Class<out ItemComponent>>,
        callback: (InvSlotRef, Array<ItemComponent>) -> Unit
    ) {
        throw UnsupportedOperationException()
    }

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

    // endregion
}
