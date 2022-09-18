package com.github.bedrockecs.server.game.data

data class FloatBlockPosition(
    val x: Float,
    val y: Float,
    val z: Float,
    val dim: Short
) {
    fun toBlock(): BlockPosition {
        return BlockPosition(x.toInt(), y.toInt(), z.toInt(), dim)
    }

    fun toSubChunk(): SubChunkPosition {
        return toBlock().toSubChunk()
    }

    fun toChunk(): ChunkPosition {
        return toSubChunk().toChunk()
    }
}
