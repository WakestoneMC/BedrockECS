package com.github.bedrockecs.server.game.chunkloading.zimpl.system

import com.github.bedrockecs.server.game.chunkloading.entity.EntityChunkLoadingComponent
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.EntityScanConfig
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.system.System
import org.springframework.stereotype.Component
import java.util.Collections.synchronizedSet

@Component
class ChunkLoadingSystem(
    private val db: GameDB
) : System {
    override val tickOrder: Int
        get() = Int.MIN_VALUE + 10_000

    override fun tick() {
        val shouldLoadChunks = synchronizedSet(HashSet<ChunkPosition>())
        db.entities.scan(
            EntityScanConfig(),
            arrayOf(EntityChunkLoadingComponent::class.java, EntityPositionComponent::class.java)
        ) { _, arr ->
            val loadingComponent = arr[0] as EntityChunkLoadingComponent
            val positionComponent = arr[1] as EntityPositionComponent

            val selfChunk = positionComponent.pos.toChunk()
            val radius = loadingComponent.radius
            for (deltaX in (-radius..radius)) {
                for (deltaZ in (-radius..radius)) {
                    val chunk = ChunkPosition(selfChunk.x + deltaX, selfChunk.z + deltaZ, selfChunk.dim)
                    shouldLoadChunks.add(chunk)
                }
            }
        }
        val loadedChunks = db.listLoadedChunks()
        shouldLoadChunks.removeAll(loadedChunks.toSet())

        shouldLoadChunks.map { db.loadChunk(it) }.forEach { it.join() }
    }
}
