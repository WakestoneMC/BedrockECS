package com.github.bedrockecs.game.data

import com.github.bedrockecs.game.data.BlockConstants.SUBCHUNK_SIZE

data class SubChunkPosition(
    val chunkX: Int,
    val chunkY: Int,
    val chunkZ: Int
) {
    fun toBlock(offsetX: Int, offsetY: Int, offsetZ: Int): BlockPosition {
        return BlockPosition(
            chunkX * SUBCHUNK_SIZE + offsetX,
            chunkY * SUBCHUNK_SIZE + offsetY,
            chunkZ * SUBCHUNK_SIZE + offsetZ
        )
    }

    fun toLayered(layer: Short = 0): LayeredSubChunkPosition {
        return LayeredSubChunkPosition(chunkX, chunkY, chunkZ, layer)
    }

    fun toChunk(): ChunkPosition {
        return ChunkPosition(chunkX, chunkZ)
    }
}
