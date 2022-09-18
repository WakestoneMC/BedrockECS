package com.github.bedrockecs.server.game.data

import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE

data class LayeredBlockPosition(
    val x: Int,
    val y: Int,
    val z: Int,
    val layer: Short = 0,
    val dim: Short
) {
    val subchunkOffsets: Triple<Int, Int, Int> = Triple(
        x % SUBCHUNK_SIZE,
        y % SUBCHUNK_SIZE,
        z % SUBCHUNK_SIZE
    )

    fun toBlock(): BlockPosition {
        return BlockPosition(x, y, z, dim)
    }
    fun toLayeredSubChunk(): LayeredSubChunkPosition {
        return LayeredSubChunkPosition(
            x / SUBCHUNK_SIZE,
            y / SUBCHUNK_SIZE,
            z / SUBCHUNK_SIZE,
            layer,
            dim
        )
    }

    fun toSubChunk(): SubChunkPosition {
        return SubChunkPosition(
            x / SUBCHUNK_SIZE,
            y / SUBCHUNK_SIZE,
            z / SUBCHUNK_SIZE,
            dim
        )
    }
}
