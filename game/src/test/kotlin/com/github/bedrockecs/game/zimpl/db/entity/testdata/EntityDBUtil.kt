package com.github.bedrockecs.game.zimpl.db.entity.testdata

import com.github.bedrockecs.game.db.entity.EntityDBStorage
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.serial.SerialEntity
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestEntities.TEST_SERIAL_ENTITY_A_PARTS

object EntityDBUtil {
    fun buildTestStorage(): Pair<EntityID, EntityDBStorage> {
        val storage = EntityDBStorage.inMemory()
        val eid = storage.allocateEntity().join()
        storage.writeEntity(SerialEntity(eid, TEST_SERIAL_ENTITY_A_PARTS)).join()
        return eid to storage
    }
}
