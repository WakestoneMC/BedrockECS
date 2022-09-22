package com.github.bedrockecs.server.game.data

import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE

data class SubChunkPosition(
    val x: Int,
    val y: Int,
    val z: Int,
    val dim: Short
) {
    fun toBlock(offsetX: Int, offsetY: Int, offsetZ: Int): BlockPosition {
        return BlockPosition(
            x * SUBCHUNK_SIZE + offsetX,
            y * SUBCHUNK_SIZE + offsetY,
            z * SUBCHUNK_SIZE + offsetZ,
            dim
        )
    }

    fun toLayered(layer: Short = 0): LayeredSubChunkPosition {
        return LayeredSubChunkPosition(x, y, z, dim, layer)
    }

    fun toChunk(): ChunkPosition {
        return ChunkPosition(x, z, dim)
    }
}
