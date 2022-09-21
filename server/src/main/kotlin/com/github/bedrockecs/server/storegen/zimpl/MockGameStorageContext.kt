package com.github.bedrockecs.server.storegen.zimpl

import com.github.bedrockecs.server.common.palette.PalettedStorage
import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.GameStorageContext
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunk
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunkLayer
import com.github.bedrockecs.vanilla.blocks.world.AirBlockType
import com.github.bedrockecs.vanilla.blocks.world.DirtBlockType
import java.util.UUID
import java.util.concurrent.CompletableFuture

class MockGameStorageContext : GameStorageContext {

    override fun readChunk(pos: ChunkPosition): CompletableFuture<SerialChunk> {
        val airIdx = AirBlockType.allInstances[0].runtimeID
        val dirtIdx = DirtBlockType.allInstances[0].runtimeID
        val arr = PalettedStorage.createWithDefaultState(airIdx.toInt())
        val airArr = PalettedStorage.createWithDefaultState(airIdx.toInt())
        for (idx in (0..4095)) {
            val y = idx / SUBCHUNK_SIZE % SUBCHUNK_SIZE
            if (y == 0) {
                arr.setBlock(idx, dirtIdx.toInt())
            } else {
                arr.setBlock(idx, airIdx.toInt())
            }
        }

        fun buildSubChunk(idx: Int): SerialSubChunk {
            val layer = if (idx == 7) {
                arr
            } else {
                airArr
            }
            return SerialSubChunk(
                components = emptyMap(),
                layers = listOf(
                    SerialSubChunkLayer(
                        storage = layer,
                        overrides = emptyMap()
                    )
                )
            )
        }

        val ret = SerialChunk(
            components = emptyMap(),
            subChunks = (0..11).map { buildSubChunk(it) }
        )
        return CompletableFuture.completedFuture(ret)
    }

    override fun readEntitiesInChunk(pos: ChunkPosition): CompletableFuture<Set<EntityID>> {
        return CompletableFuture.completedFuture(emptySet())
    }

    override fun writeChunk(pos: ChunkPosition, chunk: SerialChunk): CompletableFuture<Void> {
        return CompletableFuture.completedFuture(null)
    }

    override fun readEntity(id: EntityID): CompletableFuture<GameStorageContext.SerialInvEntity> {
        TODO("Not yet implemented")
    }

    override fun writeEntity(id: EntityID, entity: GameStorageContext.SerialInvEntity): CompletableFuture<Void> {
        return CompletableFuture.completedFuture(null)
    }

    override fun entityForPlayerName(name: String): CompletableFuture<EntityID?> {
        return CompletableFuture.completedFuture(null)
    }

    override fun entityForPlayerUUID(uuid: UUID): CompletableFuture<EntityID?> {
        return CompletableFuture.completedFuture(null)
    }
}
