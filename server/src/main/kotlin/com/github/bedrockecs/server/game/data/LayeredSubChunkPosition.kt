package com.github.bedrockecs.server.game.data

data class LayeredSubChunkPosition(
    val x: Int,
    val y: Int,
    val z: Int,
    val dim: Short,
    val layer: Short
) {
    fun toSubChunk(): SubChunkPosition {
        return SubChunkPosition(x, y, z, dim)
    }

    fun toChunk(): ChunkPosition {
        return ChunkPosition(x, z, dim)
    }
}
