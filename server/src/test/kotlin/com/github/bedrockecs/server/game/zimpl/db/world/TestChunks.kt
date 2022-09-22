package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.common.palette.PalettedStorage
import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.SubChunkPosition
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunk
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunkLayer
import com.github.bedrockecs.vanilla.blocks.world.AirBlockType
import com.github.bedrockecs.vanilla.blocks.world.DirtBlockType

object TestChunks {
    val TEST_CHUNK_A: SerialChunk
    val INITIAL_Y = -64
    val TEST_CHUNK_A_POS = ChunkPosition(2, 2, 0)
    val TEST_SUBCHUNK_A_POS = SubChunkPosition(2, 3, 2, 0)
    init {
        val pureAirStorage = PalettedStorage.createWithDefaultState(AirBlockType.primary.runtimeID.toInt())
        val dirtPlatformStorage = pureAirStorage.copy()
        for (idx in (0..4095)) {
            val y = idx / SUBCHUNK_SIZE % SUBCHUNK_SIZE
            if (y == 0) {
                dirtPlatformStorage.setBlock(idx, DirtBlockType.primary.runtimeID.toInt())
            }
        }

        val initial = (INITIAL_Y / SUBCHUNK_SIZE)
        val subChunks = (initial..initial + 11)
            .map { subChunkY ->
                if (subChunkY == TEST_SUBCHUNK_A_POS.y) {
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
            subChunksInitialY = INITIAL_Y
        )
    }
}
