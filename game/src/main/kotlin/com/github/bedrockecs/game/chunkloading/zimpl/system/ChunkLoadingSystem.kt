package com.github.bedrockecs.game.chunkloading.zimpl.system

import com.github.bedrockecs.game.chunkloading.entity.EntityChunkLoadingComponent
import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.game.db.entity.scan
import com.github.bedrockecs.game.system.CommonTickOrders
import com.github.bedrockecs.game.system.ECSSystem
import com.github.bedrockecs.game.zimpl.db.entity.EntityDBInternal
import com.github.bedrockecs.game.zimpl.db.world.WorldDBInternal
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ChunkLoadingSystem(
    private val world: WorldDBInternal,
    private val entity: EntityDBInternal
) : ECSSystem {
    companion object {
        val log = LoggerFactory.getLogger("game.chunk-loading")
    }

    override val tickOrder: Int
        get() = CommonTickOrders.CHUNK_LOADING

    override fun tick() {
        val shouldLoadChunks = computeShouldLoadChunks()

        val loadedChunks = world.listLoadedChunks()
        val toLoadChunks = shouldLoadChunks - loadedChunks
        val toUnLoadChunks = loadedChunks - shouldLoadChunks

        try {
            toLoadChunks.forEach {
                log.debug("loading chunk [chunk={}]", it)
                world.loadChunk(it).join()
                entity.listEntitiesInChunk(it).join().forEach { entity.loadEntity(it).join() }
            }
            toUnLoadChunks.forEach {
                log.debug("unloading chunk [chunk={}]", it)
                entity.listEntitiesInChunk(it).join().forEach { entity.unloadEntity(it).join() }
                world.unloadChunk(it).join()
            }
        } catch (ex: Throwable) {
            log.error("error performing chunk loading [toLoad={}, toUnLoad={}]", toLoadChunks, toUnLoadChunks)
            throw ex
        }
    }

    private fun computeShouldLoadChunks(): HashSet<ChunkPosition> {
        val shouldLoadChunks = HashSet<ChunkPosition>()
        entity.scan<EntityChunkLoadingComponent, EntityPositionComponent> { eid, loading, pos ->
            val selfChunk = pos.pos.toChunk()
            val radius = loading.radius
            for (deltaX in (-radius..radius)) {
                for (deltaZ in (-radius..radius)) {
                    val chunk = ChunkPosition(selfChunk.chunkX + deltaX, selfChunk.chunkZ + deltaZ)
                    shouldLoadChunks.add(chunk)
                }
            }
        }
        return shouldLoadChunks
    }
}
