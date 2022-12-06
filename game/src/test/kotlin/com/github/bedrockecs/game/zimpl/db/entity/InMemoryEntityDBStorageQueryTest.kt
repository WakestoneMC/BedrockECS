package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.zimpl.db.entity.testdata.EntityDBUtil.buildTestStorage
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestEntities.TEST_ENTITY_A_NAME
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestEntities.TEST_ENTITY_A_POSITION
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestEntities.TEST_ENTITY_A_UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID

class InMemoryEntityDBStorageQueryTest {
    @Test
    fun listEntitiesInChunkWorks() {
        val (eid, storage) = buildTestStorage()

        val ret = storage.listEntitiesInChunk(TEST_ENTITY_A_POSITION.toChunk()).join()

        assertThat(ret).containsExactly(eid)
    }

    @Test
    fun findEntityByPlayerNameWorks() {
        val (eid, storage) = buildTestStorage()

        val ret = storage.findEntityByPlayerName(TEST_ENTITY_A_NAME).join()

        assertThat(ret).isEqualTo(eid)
    }

    @Test
    fun findEntityByPlayerNameReturnsNullOnNotFound() {
        val (eid, storage) = buildTestStorage()

        val ret = storage.findEntityByPlayerName("test").join()

        assertThat(ret).isNull()
    }

    @Test
    fun findEntityByPlayerUUIDWorks() {
        val (eid, storage) = buildTestStorage()

        val ret = storage.findEntityByPlayerUUID(TEST_ENTITY_A_UUID).join()

        assertThat(ret).isEqualTo(eid)
    }

    @Test
    fun entityForPlayerNameReturnsNullOnNotFound() {
        val (eid, storage) = buildTestStorage()

        val ret = storage.findEntityByPlayerUUID(UUID.randomUUID()).join()

        assertThat(ret).isNull()
    }
}
