package com.github.bedrockecs.server.game.data

data class ChunkPosition(
    val chunkX: Int,
    val chunkZ: Int,
    val dim: Short
) {
    fun toSubChunk(chunkY: Int): SubChunkPosition {
        return SubChunkPosition(chunkX, chunkY, chunkZ, dim)
    }
}
