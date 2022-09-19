package com.github.bedrockecs.server.storegen.zimpl

import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.GameStorageContext
import com.github.bedrockecs.server.game.db.GameStorageContextInternal
import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunk
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunkLayer
import com.github.bedrockecs.vanilla.blocks.world.AirBlockType
import com.github.bedrockecs.vanilla.blocks.world.DirtBlockType
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class MockGameStorageContext : GameStorageContextInternal {

    private val lock = ReentrantLock()

    private var counter: Int = 0

    override fun initialLoad(): GameStorageContextInternal.InitialLoad {
        return GameStorageContextInternal.InitialLoad(
            dimensions = mapOf((0.toShort()) to emptySet()),
            globalEntity = emptySet()
        )
    }

    override fun onCreatingDimension(id: Short, components: Set<DimensionComponent>) {
        TODO("Not yet implemented")
    }

    override fun onDimensionComponentUpdated(id: Short, from: DimensionComponent?, to: DimensionComponent?) {
        // no-op
    }

    override fun onDimensionDestroyed(id: Short, components: Set<DimensionComponent>) {
        TODO("Not yet implemented")
    }

    override fun readChunk(pos: ChunkPosition): CompletableFuture<SerialChunk> {
        val airIdx = AirBlockType.allInstances[0].runtimeID
        val dirtIdx = DirtBlockType.allInstances[0].runtimeID
        val arr = ShortArray(4096)
        val airArr = ShortArray(4096)
        airArr.fill(airIdx)
        for (idx in (0..4095)) {
            val x = idx % SUBCHUNK_SIZE
            val y = idx / SUBCHUNK_SIZE % SUBCHUNK_SIZE
            val z = idx / (SUBCHUNK_SIZE * SUBCHUNK_SIZE) % SUBCHUNK_SIZE
            if (y == 0) {
                arr[idx] = dirtIdx
            } else {
                arr[idx] = airIdx
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
                    SerialSubChunkLayer.UnPalettedShort(
                        ids = layer,
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

    override fun allocateID(): List<EntityID> {
        lock.withLock {
            val value = counter
            counter += 1024
            return (value..value + 1024).map { EntityID(it) }
        }
    }

    override fun releaseID(ids: List<EntityID>) {
        // no-op
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
