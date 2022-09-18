package com.github.bedrockecs.server.game.db.invitem

import com.github.bedrockecs.server.game.db.invitem.data.InventoryMetadata
import com.github.bedrockecs.server.game.db.invitem.data.ItemComponent

/**
 * represents an object that holds item information
 */
interface InvItemStore {
    /**
     * read inventory metadata from inventory
     */
    fun readMetadata(ref: InvRef): InventoryMetadata

    /**
     * is the slot empty?
     */
    fun isEmpty(ref: InvSlotRef): Boolean

    /**
     * try to read a component from the item at slot in the inventory
     * @return the component, returns null if not exists
     */
    fun <T : ItemComponent> read(ref: InvSlotRef, clazz: Class<T>): T?

    /**
     * list all components the item in the slot has
     */
    fun list(ref: InvSlotRef): Collection<ItemComponent>

    /**
     * iterate through items and components it has
     * @param config configuration for this scan
     * @param components array of concrete component type for component to view in the callback
     * @param callback iteration callback
     * @throws UnsupportedOperationException if the implementation does not support it
     * @apiNote user is not expected to mutate the provided components, use [MutableInvItemStore.mutatingScan] for that!
     */
    fun <T> scan(
        config: ItemScanConfig,
        components: Array<Class<out ItemComponent>>,
        callback: (InvSlotRef, Array<ItemComponent>) -> Unit
    ): Collection<T> {
        throw UnsupportedOperationException()
    }
}
