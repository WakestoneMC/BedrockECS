package com.github.bedrockecs.game.data

import com.github.bedrockecs.game.data.BlockConstants.SUBCHUNK_SIZE

data class BlockPosition(
    val x: Int,
    val y: Int,
    val z: Int
) {
    val subchunkOffsets: Triple<Int, Int, Int> = Triple(
        x % SUBCHUNK_SIZE,
        y % SUBCHUNK_SIZE,
        z % SUBCHUNK_SIZE
    )

    fun toLayered(layer: Short = 0): LayeredBlockPosition {
        return LayeredBlockPosition(x, y, z, layer)
    }

    fun toFloat(): FloatBlockPosition {
        return FloatBlockPosition(x.toFloat(), y.toFloat(), z.toFloat())
    }

    fun toSubChunk(): SubChunkPosition {
        return SubChunkPosition(
            x / SUBCHUNK_SIZE,
            y / SUBCHUNK_SIZE,
            z / SUBCHUNK_SIZE
        )
    }

    fun toChunk(): ChunkPosition {
        return toSubChunk().toChunk()
    }
}
