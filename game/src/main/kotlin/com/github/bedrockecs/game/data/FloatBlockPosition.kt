package com.github.bedrockecs.game.data

data class FloatBlockPosition(
    val x: Float,
    val y: Float,
    val z: Float
) {
    fun toBlock(): BlockPosition {
        return BlockPosition(x.toInt(), y.toInt(), z.toInt())
    }

    fun toSubChunk(): SubChunkPosition {
        return toBlock().toSubChunk()
    }

    fun toChunk(): ChunkPosition {
        return toSubChunk().toChunk()
    }
}
