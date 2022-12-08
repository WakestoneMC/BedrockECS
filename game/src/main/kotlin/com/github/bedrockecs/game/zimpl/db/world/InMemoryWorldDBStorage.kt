package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.world.WorldDBStorage
import com.github.bedrockecs.game.db.world.serial.SerialChunk
import java.util.concurrent.CompletableFuture
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlin.math.ceil

class InMemoryWorldDBStorage(
    heightRange: Pair<Int, Int>,
    private val generator: (ChunkPosition) -> SerialChunk
) : WorldDBStorage {

    private val lowestY = heightRange.first

    private val subChunkCount = ceil((heightRange.second - heightRange.first) / SUBCHUNK_SIZE.toDouble()).toInt()

    private val lock = ReentrantLock()
    
    private val chunkMap = mutableMapOf<ChunkPosition, SerialChunk>()

    override fun readChunk(pos: ChunkPosition): CompletableFuture<SerialChunk> {
        val ret = lock.withLock {
            chunkMap.getOrPut(pos) { generator(pos) }
        }
        return CompletableFuture.completedFuture(ret)
    }

    override fun writeChunk(pos: ChunkPosition, chunk: SerialChunk): CompletableFuture<Void> {
        if (chunk.initialY != lowestY) {
            throw IllegalArgumentException("expecting lowestY be $lowestY, got ${chunk.initialY}!")
        }
        if (chunk.subChunks.size != subChunkCount) {
            throw IllegalArgumentException("expecting $subChunkCount subchunks, got ${chunk.subChunks.size}!")
        }
        lock.withLock {
            chunkMap[pos] = chunk
        }
        return CompletableFuture.completedFuture(null)
    }
}
