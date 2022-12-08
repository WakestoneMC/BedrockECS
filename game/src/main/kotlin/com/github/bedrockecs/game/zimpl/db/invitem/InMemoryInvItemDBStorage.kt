package com.github.bedrockecs.game.zimpl.db.invitem

import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.invitem.InvItemDBStorage
import com.github.bedrockecs.game.db.invitem.InvRef
import com.github.bedrockecs.game.db.invitem.serial.SerialInventory
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

class InMemoryInvItemDBStorage : InvItemDBStorage {

    private val invs = ConcurrentHashMap<InvRef, SerialInventory>()

    override fun listInventoriesFor(id: EntityID): Set<String> {
        return invs.keys.filter { it.owner == id }.map { it.name }.toSet()
    }

    override fun readInventory(ref: InvRef): CompletableFuture<SerialInventory?> {
        val ret = invs[ref]
        return CompletableFuture.completedFuture(ret)
    }

    override fun writeInventory(ref: InvRef, serial: SerialInventory): CompletableFuture<Void> {
        invs[ref] = serial
        return CompletableFuture.completedFuture(null)
    }

    override fun removeInventory(ref: InvRef): CompletableFuture<Void> {
        invs.remove(ref)
        return CompletableFuture.completedFuture(null)
    }
}
