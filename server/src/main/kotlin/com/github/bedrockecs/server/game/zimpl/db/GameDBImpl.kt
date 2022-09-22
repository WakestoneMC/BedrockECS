package com.github.bedrockecs.server.game.zimpl.db

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.GameStorageContext
import com.github.bedrockecs.server.game.db.GameStorageProvider
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.db.entity.scan
import com.github.bedrockecs.server.game.db.invitem.InvitemDB
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.registry.BlockRegistry
import com.github.bedrockecs.server.game.zimpl.db.dimension.DimensionDBImpl
import com.github.bedrockecs.server.game.zimpl.db.entity.EntityDBImpl
import com.github.bedrockecs.server.game.zimpl.db.world.WorldDBImpl
import java.util.concurrent.CompletableFuture

class GameDBImpl(
    eventBus: EventBus,
    registry: BlockRegistry,
    private val provider: GameStorageProvider
) : GameDB {

    override val dimensions = DimensionDBImpl(eventBus)

    override val world = WorldDBImpl(eventBus, registry)

    override val entities = EntityDBImpl(eventBus, provider.allocator)

    override val invitems: InvitemDB
        get() = TODO("Not yet implemented")

    override fun isLoaded(pos: ChunkPosition): Boolean {
        return world.isLoaded(pos)
    }

    override fun listLoadedChunks(): Collection<ChunkPosition> {
        return world.listLoadedChunks()
    }

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
}
