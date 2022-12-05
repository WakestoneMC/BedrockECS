package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.common.palette.PalettedStorage
import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.world.WorldDBSerial
import com.github.bedrockecs.game.db.world.WorldDBStorage
import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.game.db.world.serial.SerialChunk
import com.github.bedrockecs.game.db.world.serial.SerialSubChunk
import com.github.bedrockecs.game.db.world.serial.SerialSubChunkLayer
import com.github.bedrockecs.game.zimpl.common.testdata.TestAirBlockType
import io.netty.buffer.Unpooled
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class InMemoryEmptyWorldDBStorageTest {

    val testSerial = WorldDBSerial.createAutoAssigning(TestAirBlockType.primary)

    @Test
    fun readChunkWorks() {
        val storage = createTestStorage()

        val ret = storage.readChunk(ChunkPosition(0, 0))

        val expected = SerialChunk(
            subChunks = (0..23).map { buildFilledSerialSubChunk(TestAirBlockType.primary) },
            initialY = -64
        )
        
        assertThat(ret.join())
            .usingRecursiveComparison()
            .withComparatorForType(this::comparePalettedStorage, PalettedStorage::class.java)
            .isEqualTo(expected)
    }

    @Test
    fun writeChunkWorks() {
        val storage = createTestStorage()
        val position = ChunkPosition(0, 0)
        val writing = SerialChunk(
            subChunks = (0..23).map { buildFilledSerialSubChunk(TestAirBlockType.primary) },
            initialY = -64
        )

        storage.writeChunk(position, writing).join()

        assertThat(storage.readChunk(position).join())
            .usingRecursiveComparison()
            .withComparatorForType(this::comparePalettedStorage, PalettedStorage::class.java)
            .isEqualTo(writing)
    }

    @Test
    fun writeChunkRejectsSerialChunkWithDifferentHeightRange() {
        val storage = createTestStorage()
        val position = ChunkPosition(0, 0)

        val writingA = SerialChunk(
            subChunks = (0..23).map { buildFilledSerialSubChunk(TestAirBlockType.primary) },
            initialY = -128
        )
        assertThrows<IllegalArgumentException> {
            storage.writeChunk(position, writingA).join()
        }

        val writingB = SerialChunk(
            subChunks = (0..1).map { buildFilledSerialSubChunk(TestAirBlockType.primary) },
            initialY = -64
        )
        assertThrows<IllegalArgumentException> {
            storage.writeChunk(position, writingB).join()
        }
    }

    private fun buildFilledSerialSubChunk(blockType: BlockTypeComponent): SerialSubChunk {
        val airID = testSerial.idFor(blockType).toInt()
        return SerialSubChunk(
            layers = listOf(SerialSubChunkLayer(storage = PalettedStorage.createWithDefaultState(airID)))
        )
    }

    private fun createTestStorage(): WorldDBStorage {
        return WorldDBStorage.inMemoryEmpty(-64 to 320, testSerial)
    }

    private fun comparePalettedStorage(a: PalettedStorage, b: PalettedStorage): Int {
        val aBuffer = Unpooled.buffer()
        a.writeTo(aBuffer)
        val bBuffer = Unpooled.buffer()
        b.writeTo(bBuffer)
        return aBuffer.compareTo(bBuffer)
    }
}
