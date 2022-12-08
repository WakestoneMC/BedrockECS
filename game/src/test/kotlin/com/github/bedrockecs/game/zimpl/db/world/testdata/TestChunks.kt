package com.github.bedrockecs.game.zimpl.db.world.testdata

import com.github.bedrockecs.common.palette.PalettedStorage
import com.github.bedrockecs.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.data.SubChunkPosition
import com.github.bedrockecs.game.db.world.serial.SerialChunk
import com.github.bedrockecs.game.db.world.serial.SerialSubChunk
import com.github.bedrockecs.game.db.world.serial.SerialSubChunkLayer
import com.github.bedrockecs.game.zimpl.common.testdata.TestAirBlockType
import com.github.bedrockecs.game.zimpl.common.testdata.TestDirtBlockType

object TestChunks {

    val TEST_CHUNK_A: SerialChunk
    val INITIAL_Y = -64
    val TEST_CHUNK_A_POS = ChunkPosition(2, 2)
    val TEST_SUBCHUNK_A_POS = SubChunkPosition(2, 3, 2)

    init {
        val testSerial = WorldDBUtil.testSerial

        val pureAirStorage = PalettedStorage.createWithDefaultState(testSerial.idFor(TestAirBlockType.primary).toInt())
        val dirtPlatformStorage = pureAirStorage.copy()
        for (idx in (0..4095)) {
            val y = idx / SUBCHUNK_SIZE % SUBCHUNK_SIZE
            if (y == 0) {
                dirtPlatformStorage.setBlock(idx, testSerial.idFor(TestDirtBlockType.primary).toInt())
            }
        }

        val initial = (INITIAL_Y / SUBCHUNK_SIZE)
        val subChunks = (initial..initial + 23)
            .map { subChunkY ->
                if (subChunkY == TEST_SUBCHUNK_A_POS.chunkY) {
                    SerialSubChunk(
                        components = mapOf(TestSubChunkComponentA::class.java to TestSubChunkComponentA()),
                        layers = listOf(SerialSubChunkLayer(dirtPlatformStorage, emptyMap()))
                    )
                } else {
                    SerialSubChunk(
                        components = emptyMap(),
                        layers = listOf(SerialSubChunkLayer(pureAirStorage, emptyMap()))
                    )
                }
            }
            .toList()

        TEST_CHUNK_A = SerialChunk(
            components = mapOf(TestChunkComponentA::class.java to TestChunkComponentA()),
            subChunks = subChunks,
            initialY = INITIAL_Y
        )
    }
}
