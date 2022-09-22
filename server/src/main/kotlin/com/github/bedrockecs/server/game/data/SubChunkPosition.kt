package com.github.bedrockecs.server.game.data

data class SubChunkPosition(
    val x: Int,
    val y: Int,
    val z: Int,
    val dim: Short
) {
    fun toLayered(layer: Short = 0): LayeredSubChunkPosition {
        return LayeredSubChunkPosition(x, y, z, dim, layer)
    }

    fun toChunk(): ChunkPosition {
        return ChunkPosition(x, z, dim)
    }
}
