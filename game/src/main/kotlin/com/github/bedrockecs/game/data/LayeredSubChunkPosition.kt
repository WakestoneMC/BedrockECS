package com.github.bedrockecs.game.data

data class LayeredSubChunkPosition(
    val chunkX: Int,
    val chunkY: Int,
    val chunkZ: Int,
    val layer: Short
) {
    fun toSubChunk(): SubChunkPosition {
        return SubChunkPosition(chunkX, chunkY, chunkZ)
    }

    fun toChunk(): ChunkPosition {
        return ChunkPosition(chunkX, chunkZ)
    }
}
