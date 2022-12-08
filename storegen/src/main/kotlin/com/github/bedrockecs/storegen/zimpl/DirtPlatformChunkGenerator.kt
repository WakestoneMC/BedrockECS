package com.github.bedrockecs.storegen.zimpl

import com.github.bedrockecs.common.palette.PalettedStorage
import com.github.bedrockecs.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.world.serial.SerialChunk
import com.github.bedrockecs.game.db.world.serial.SerialSubChunk
import com.github.bedrockecs.game.db.world.serial.SerialSubChunkLayer
import com.github.bedrockecs.vanilla.data.blocks.AirBlockType
import com.github.bedrockecs.vanilla.data.blocks.DirtBlockType
import kotlin.math.ceil

class DirtPlatformChunkGenerator(private val heightRange: Pair<Int, Int>) {
    fun generateChunk(pos: ChunkPosition): SerialChunk {
        val airIdx = AirBlockType.allInstances[0].runtimeID
        val dirtIdx = DirtBlockType.allInstances[0].runtimeID
        val dirtArr = PalettedStorage.createWithDefaultState(airIdx.toInt())
        val airArr = PalettedStorage.createWithDefaultState(airIdx.toInt())
        for (idx in (0..4095)) {
            val x = idx % SUBCHUNK_SIZE
            val y = idx / SUBCHUNK_SIZE % SUBCHUNK_SIZE
            val z = idx / SUBCHUNK_SIZE / SUBCHUNK_SIZE % SUBCHUNK_SIZE
            if (y == 0) {
                dirtArr.setBlock(x, y, z, dirtIdx.toInt())
            }
        }

        fun buildSubChunk(idx: Int): SerialSubChunk {
            val layer = if (idx == 7) {
                dirtArr
            } else {
                airArr
            }
            return SerialSubChunk(
                components = emptyMap(),
                layers = listOf(
                    SerialSubChunkLayer(
                        storage = layer,
                        overrides = emptyMap()
                    )
                )
            )
        }

        val chunkCount = ceil((heightRange.second - heightRange.first) / SUBCHUNK_SIZE.toFloat()).toInt()
        val ret = SerialChunk(
            components = emptyMap(),
            subChunks = (0..chunkCount).map { buildSubChunk(it) },
            initialY = heightRange.first
        )
        
        return ret
    }
}
