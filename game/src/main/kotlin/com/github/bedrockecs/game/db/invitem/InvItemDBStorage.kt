package com.github.bedrockecs.game.db.invitem

import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.invitem.serial.SerialInventory
import com.github.bedrockecs.game.zimpl.db.invitem.InMemoryInvItemDBStorage
import java.util.concurrent.CompletableFuture

/**
 * backend storage for [InvItemDB]
 */
interface InvItemDBStorage {
    companion object {
        /**
         * mock storage that keeps everything in memory
         */
        fun inMemory(): InvItemDBStorage {
            return InMemoryInvItemDBStorage()
        }
    }

    /**
     * list inventories owned by entity
     */
    fun listInventoriesFor(id: EntityID): Set<String>

    /**
     * read an inventory from storage
     */
    fun readInventory(ref: InvRef): CompletableFuture<SerialInventory?>

    /**
     * write an inventory to storage
     */
    fun writeInventory(ref: InvRef, serial: SerialInventory): CompletableFuture<Void>

    /**
     * removes an inventory from storage
     */
    fun removeInventory(ref: InvRef): CompletableFuture<Void>
}
