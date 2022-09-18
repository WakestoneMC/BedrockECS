package com.github.bedrockecs.server.game.db

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.dimension.DimensionDB
import com.github.bedrockecs.server.game.db.entity.EntityDB
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.invitem.InvitemDB
import com.github.bedrockecs.server.game.db.world.WorldDB
import java.util.concurrent.CompletableFuture

/**
 * the central state container of an entire game world (contains multiple dimensions), the "World" of ECS
 */
interface GameDB {

    // stores //

    val dimensions: DimensionDB

    val world: WorldDB

    val entities: EntityDB

    val invitems: InvitemDB

    // loading & unloading //

    /**
     * try to load a chunk(and associated entity/inventories) from storage
     */
    fun loadChunk(pos: ChunkPosition): CompletableFuture<Void>

    /**
     * try to unload a chunk(and associated entity/inventories) to storage
     */
    fun unloadChunk(pos: ChunkPosition): CompletableFuture<Void>

    /**
     * try to load an entity(and associated inventory) from storage
     */
    fun loadEntity(id: EntityID): CompletableFuture<Void>

    /**
     * try to unload an entity(and associated inventory) to storage
     */
    fun unloadEntity(id: EntityID): CompletableFuture<Void>

    // TODO: index & view //

    // TODO: access control //
}
