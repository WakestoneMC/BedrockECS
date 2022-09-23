package com.github.bedrockecs.server.game.zimpl.db

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.GameStorageContext
import com.github.bedrockecs.server.game.db.GameStorageProvider
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.db.entity.scan
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.registry.BlockRegistry
import com.github.bedrockecs.server.game.zimpl.db.dimension.DimensionDBImpl
import com.github.bedrockecs.server.game.zimpl.db.entity.EntityDBImpl
import com.github.bedrockecs.server.game.zimpl.db.invitem.NaiveInvItemDB
import com.github.bedrockecs.server.game.zimpl.db.world.WorldDBImpl
import java.util.concurrent.CompletableFuture
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class GameDBImpl(
    eventBus: EventBus,
    registry: BlockRegistry,
    private val provider: GameStorageProvider
) : GameDB {

    override val dimensions = DimensionDBImpl(
        eventBus,
        preCreate = { id, extras ->
            provider.changeListener.onCreatingDimension(id, extras)
        },
        preUpdate = { id, from, to ->
            provider.changeListener.onUpdatingDimensionComponent(id, from, to)
        },
        preDestroy = { id, c ->
            onDimensionDestroying(id)
        },
        postDestroy = { id, c ->
            provider.changeListener.onDestroyedDimension(id, c)
        }
    )

    override val world = WorldDBImpl(eventBus, registry)

    override val entities = EntityDBImpl(
        eventBus,
        provider.allocator,
        preEntityDestroy = { eid -> onEntityDestroying(eid) }
    )

    override val invitems: NaiveInvItemDB = NaiveInvItemDB(eventBus)

    override fun isLoaded(pos: ChunkPosition): Boolean {
        return world.isLoaded(pos)
    }

    override fun listLoadedChunks(): Collection<ChunkPosition> {
        return world.listLoadedChunks()
    }

    // lifecycle //

    @PostConstruct
    fun init() {
        val loaded = provider.initializer.initialLoad()
        dimensions.initialize(loaded.dimensions)
    }

    @PreDestroy
    fun close() {
        unloadEverything()
    }

    private fun unloadEverything() {
        listLoadedChunks().forEach { unloadChunk(it) }
    }

    // loading and unloading //

    override fun loadChunk(pos: ChunkPosition): CompletableFuture<Void> {
        if (!isLoaded(pos)) {
            val chunk = provider.context.readChunk(pos).join()
            val entityIDs = provider.context.readEntitiesInChunk(pos).join()
            val entities = entityIDs.map { provider.context.readEntity(it).join() }

            world.load(pos, chunk)
            entities.forEach { this.entities.load(it.entity) }
        }
        return CompletableFuture.completedFuture(null)
    }

    override fun unloadChunk(pos: ChunkPosition): CompletableFuture<Void> {
        if (isLoaded(pos)) {
            val unloadedChunk = world.unload(pos)

            val toUnload = mutableListOf<EntityID>()
            entities.scan<EntityPositionComponent> { eid, epc ->
                if (epc.pos.toChunk() == pos) {
                    toUnload.add(eid)
                }
            }
            val unloadedEntities = toUnload.map { entities.unload(it) }

            unloadedEntities.forEach {
                val e = GameStorageContext.SerialInvEntity(it, emptySet())
                provider.context.writeEntity(e).join()
            }

            provider.context.writeChunk(pos, unloadedChunk).join()
        }
        return CompletableFuture.completedFuture(null)
    }

    override fun loadEntity(id: EntityID): CompletableFuture<Void> {
        if (!entities.isLoaded(id)) {
            val e = provider.context.readEntity(id).join()
            entities.load(e.entity)
        }
        return CompletableFuture.completedFuture(null)
    }

    override fun unloadEntity(id: EntityID): CompletableFuture<Void> {
        if (entities.isLoaded(id)) {
            val se = entities.unload(id)
            provider.context.writeEntity(GameStorageContext.SerialInvEntity(se, emptySet()))
        }
        return CompletableFuture.completedFuture(null)
    }

    // cascade //

    private fun onDimensionDestroying(dim: Short) {
        unloadAllInvEntitiesInDimension(dim)
        world.unloadAllChunksInDimension(dim)
    }

    private fun unloadAllInvEntitiesInDimension(dim: Short) {
        val toUnload = mutableListOf<EntityID>()
        entities.scan<EntityPositionComponent> { eid, epc ->
            if (epc.pos.dim == dim) {
                toUnload.add(eid)
            }
        }
        toUnload.forEach {
            invitems.unloadAllForEntity(it)
            entities.unload(it)
        }
    }

    private fun onEntityDestroying(eid: EntityID) {
        invitems.removeAllInventoryForEntity(eid)
    }
}
