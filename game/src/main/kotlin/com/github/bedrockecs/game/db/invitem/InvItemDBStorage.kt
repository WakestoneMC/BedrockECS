package com.github.bedrockecs.game.db.invitem

import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.invitem.serial.SerialInventory
import java.util.concurrent.CompletableFuture

/**
 * backend storage for [InvitemDB]
 */
interface InvItemDBStorage {
    /**
     * list inventories owned by entity
     */
    fun listInventoriesFor(id: EntityID): Set<String>

    /**
     * read an inventory from storage
     */
    fun readInventory(ref: InvRef): CompletableFuture<SerialInventory>

    /**
     * write an inventory to storage
     */
    fun writeEntity(ref: InvRef, serial: SerialInventory): CompletableFuture<Void>
}
