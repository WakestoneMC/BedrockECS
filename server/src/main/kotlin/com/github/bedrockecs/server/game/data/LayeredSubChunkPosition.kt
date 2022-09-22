package com.github.bedrockecs.server.game.data

data class LayeredSubChunkPosition(
    val chunkX: Int,
    val chunkY: Int,
    val chunkZ: Int,
    val dim: Short,
    val layer: Short
) {
    fun toSubChunk(): SubChunkPosition {
        return SubChunkPosition(chunkX, chunkY, chunkZ, dim)
    }

    fun toChunk(): ChunkPosition {
        return ChunkPosition(chunkX, chunkZ, dim)
    }
}
