package com.github.bedrockecs.server.game.zimpl.db

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.GameStorageContext
import com.github.bedrockecs.server.game.db.dimension.DimensionDB
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.invitem.InvitemDB
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.registry.BlockRegistry
import com.github.bedrockecs.server.game.zimpl.db.entity.NaiveEntityDB
import com.github.bedrockecs.server.game.zimpl.db.world.NaiveWorldDB
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentSkipListSet

class NaiveGameDB(
    private val eventBus: EventBus,
    private val blockRegistry: BlockRegistry,
    private val storage: GameStorageContext
) : GameDB {
    override val dimensions: DimensionDB
        get() = TODO("Not yet implemented")

    override val world: NaiveWorldDB = NaiveWorldDB(eventBus, blockRegistry)

    override val entities: NaiveEntityDB = NaiveEntityDB(eventBus)

    override val invitems: InvitemDB
        get() = TODO("Not yet implemented")

    private val loadedChunk = ConcurrentSkipListSet<ChunkPosition>()

    override fun isLoaded(pos: ChunkPosition): Boolean {
        return loadedChunk.contains(pos)
    }
    override fun listLoadedChunks(): Collection<ChunkPosition> {
        return loadedChunk.toSet()
    }
    override fun loadChunk(pos: ChunkPosition): CompletableFuture<Void> {
        val invEntities = storage.readEntitiesInChunk(pos)
            .thenCompose { eids ->
                // create future for loading all entities
                val futures = eids.map { eid -> storage.readEntity(eid) }
                // parallelize and wait for all of them to complete, extract results
                CompletableFuture.allOf(*futures.toTypedArray()).thenApply { futures.map { it.get() } }
            }
        val chunks = storage.readChunk(pos)

        val future: CompletableFuture<Void> = CompletableFuture.allOf(invEntities, chunks)
            .thenApply {
                chunks.get()
                invEntities.get().forEach { entities.load(it.entity.id, it.entity.components.values.toSet()) }
                null
            }
            .thenRun { loadedChunk.add(pos) }

        return future
    }

    override fun unloadChunk(pos: ChunkPosition): CompletableFuture<Void> {
        loadedChunk.remove(pos)
        TODO("Not yet implemented")
    }

    override fun loadEntity(id: EntityID): CompletableFuture<Void> {
        return storage.readEntity(id)
            .thenApply {
                entities.load(it.entity.id, it.entity.components.values.toSet())
                null
            }
    }

    override fun unloadEntity(id: EntityID): CompletableFuture<Void> {
        TODO("Not yet implemented")
    }
}
