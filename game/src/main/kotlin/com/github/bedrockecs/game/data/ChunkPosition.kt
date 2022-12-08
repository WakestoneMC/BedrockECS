package com.github.bedrockecs.game.data

data class ChunkPosition(
    val chunkX: Int,
    val chunkZ: Int
) {
    fun toSubChunk(chunkY: Int): SubChunkPosition {
        return SubChunkPosition(chunkX, chunkY, chunkZ)
    }
}
