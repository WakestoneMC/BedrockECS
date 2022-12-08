package com.github.bedrockecs.game.zimpl.db.world.testdata

import com.github.bedrockecs.game.db.world.BlockRegistry
import com.github.bedrockecs.game.db.world.WorldDBSerial
import com.github.bedrockecs.game.db.world.WorldDBStorage
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.zimpl.common.testdata.TestAirBlockType
import com.github.bedrockecs.game.zimpl.db.world.WorldDBImpl
import com.github.bedrockecs.game.zimpl.db.world.testdata.TestChunks.TEST_CHUNK_A
import com.github.bedrockecs.game.zimpl.db.world.testdata.TestChunks.TEST_CHUNK_A_POS

object WorldDBUtil {

    val testSerial = WorldDBSerial.createAutoAssigning(TestAirBlockType.primary)

    fun withTestDB(func: (EventBus, WorldDBImpl) -> Unit) {
        val evb = EventBus.create()
        val reg = BlockRegistry.create()
        val storage = WorldDBStorage.inMemoryEmpty(-64 to 320, testSerial)
        storage.writeChunk(TEST_CHUNK_A_POS, TEST_CHUNK_A)
        val db = WorldDBImpl(evb, reg, testSerial, storage)
        func(evb, db)
    }

    fun withTestDB(func: (EventBus, WorldDBImpl, BlockRegistry) -> Unit) {
        val evb = EventBus.create()
        val reg = BlockRegistry.create()
        val storage = WorldDBStorage.inMemoryEmpty(-64 to 320, testSerial)
        storage.writeChunk(TEST_CHUNK_A_POS, TEST_CHUNK_A)
        val db = WorldDBImpl(evb, reg, testSerial, storage)
        func(evb, db, reg)
    }
}
