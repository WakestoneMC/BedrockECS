package com.github.bedrockecs.server.game.data

import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE

data class SubChunkPosition(
    val chunkX: Int,
    val chunkY: Int,
    val chunkZ: Int,
    val dim: Short
) {
    fun toBlock(offsetX: Int, offsetY: Int, offsetZ: Int): BlockPosition {
        return BlockPosition(
            chunkX * SUBCHUNK_SIZE + offsetX,
            chunkY * SUBCHUNK_SIZE + offsetY,
            chunkZ * SUBCHUNK_SIZE + offsetZ,
            dim
        )
    }

    fun toLayered(layer: Short = 0): LayeredSubChunkPosition {
        return LayeredSubChunkPosition(chunkX, chunkY, chunkZ, dim, layer)
    }

    fun toChunk(): ChunkPosition {
        return ChunkPosition(chunkX, chunkZ, dim)
    }
}
