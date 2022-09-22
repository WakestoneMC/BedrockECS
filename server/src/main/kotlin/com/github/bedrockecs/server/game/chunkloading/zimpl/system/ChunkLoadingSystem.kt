package com.github.bedrockecs.server.game.chunkloading.zimpl.system

import com.github.bedrockecs.server.game.chunkloading.entity.EntityChunkLoadingComponent
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.db.entity.scan
import com.github.bedrockecs.server.game.system.CommonTickOrders
import com.github.bedrockecs.server.game.system.System
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ChunkLoadingSystem(
    private val db: GameDB
) : System {
    companion object {
        val log = LoggerFactory.getLogger("game.chunk-loading")
    }

    override val tickOrder: Int
        get() = CommonTickOrders.CHUNK_LOADING

    override fun tick() {
        val shouldLoadChunks = computeShouldLoadChunks()

        val loadedChunks = db.listLoadedChunks()
        val toLoadChunks = shouldLoadChunks - loadedChunks
        val toUnLoadChunks = loadedChunks - shouldLoadChunks

        try {
            toLoadChunks.map {
                log.debug("loading chunk [chunk={}]", it)
                db.loadChunk(it)
            }.forEach { it.join() }
            toUnLoadChunks.map {
                log.debug("unloading chunk [chunk={}]", it)
                db.unloadChunk(it)
            }.forEach { it.join() }
        } catch (ex: Throwable) {
            log.error("error performing chunk loading [toLoad={}, toUnLoad={}]", toLoadChunks, toUnLoadChunks)
            throw ex
        }
    }

    private fun computeShouldLoadChunks(): HashSet<ChunkPosition> {
        val shouldLoadChunks = HashSet<ChunkPosition>()
        db.entities.scan<EntityChunkLoadingComponent, EntityPositionComponent> { eid, loading, pos ->
            val selfChunk = pos.pos.toChunk()
            val radius = loading.radius
            for (deltaX in (-radius..radius)) {
                for (deltaZ in (-radius..radius)) {
                    val chunk = ChunkPosition(selfChunk.chunkX + deltaX, selfChunk.chunkZ + deltaZ, selfChunk.dim)
                    shouldLoadChunks.add(chunk)
                }
            }
        }
        return shouldLoadChunks
    }
}
