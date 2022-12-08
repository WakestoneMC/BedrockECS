package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.common.palette.PalettedStorage
import com.github.bedrockecs.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.world.WorldDBSerial
import com.github.bedrockecs.game.db.world.serial.SerialChunk
import com.github.bedrockecs.game.db.world.serial.SerialSubChunk
import com.github.bedrockecs.game.db.world.serial.SerialSubChunkLayer
import kotlin.math.ceil

class EmptyChunkGenerator(
    heightRange: Pair<Int, Int>,
    private val serial: WorldDBSerial
) {

    private val lowestY = heightRange.first

    private val subChunkCount = ceil((heightRange.second - heightRange.first) / SUBCHUNK_SIZE.toDouble()).toInt()

    fun generate(pos: ChunkPosition): SerialChunk {
        val airID = serial.idFor(serial.airBlockType).toInt()
        
        val subChunkLayer = SerialSubChunkLayer(storage = PalettedStorage.createWithDefaultState(airID))
        val subChunks = (0 until subChunkCount)
            .map { SerialSubChunk(layers = listOf(subChunkLayer)) }
        
        return SerialChunk(subChunks = subChunks, initialY = lowestY)
    }
}
