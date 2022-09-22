package com.github.bedrockecs.server.game.data

data class ChunkPosition(
    val x: Int,
    val z: Int,
    val dim: Short
) {
    fun toSubChunk(y: Int): SubChunkPosition {
        return SubChunkPosition(x, y, z, dim)
    }
}
