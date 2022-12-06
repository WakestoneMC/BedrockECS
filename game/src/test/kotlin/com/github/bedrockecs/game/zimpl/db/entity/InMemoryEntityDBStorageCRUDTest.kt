package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.serial.SerialEntity
import com.github.bedrockecs.game.zimpl.db.entity.testdata.EntityDBUtil
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestEntities.TEST_SERIAL_ENTITY_A_PARTS
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestEntities.TEST_SERIAL_ENTITY_B_PARTS
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class InMemoryEntityDBStorageCRUDTest {
    @Test
    fun allocateEntityWorks() {
        val (eid, storage) = EntityDBUtil.buildTestStorage()

        var ret: EntityID? = null
        assertDoesNotThrow {
            ret = storage.allocateEntity().join()
        }

        assertThat(storage.readEntity(ret!!).join()).isEqualTo(SerialEntity(ret!!, emptyMap()))
    }

    @Test
    fun readEntityWorks() {
        val (eid, storage) = EntityDBUtil.buildTestStorage()

        val ret = storage.readEntity(eid).join()

        assertThat(ret).isEqualTo(SerialEntity(eid, TEST_SERIAL_ENTITY_A_PARTS))
    }

    @Test
    fun readEntityReturnsNullOnNoSuchEntity() {
        val (eid, storage) = EntityDBUtil.buildTestStorage()

        val ret = storage.readEntity(EntityID(10)).join()

        assertThat(ret).isNull()
    }

    @Test
    fun writeEntityWorks() {
        val (eid, storage) = EntityDBUtil.buildTestStorage()

        storage.writeEntity(
            SerialEntity(eid, TEST_SERIAL_ENTITY_B_PARTS)
        ).join()

        assertThat(storage.readEntity(eid).join()).isEqualTo(SerialEntity(eid, TEST_SERIAL_ENTITY_B_PARTS))
    }

    @Test
    fun writeEntityRejectsNoSuchEntity() {
        val (eid, storage) = EntityDBUtil.buildTestStorage()

        assertThrows<IllegalArgumentException> {
            storage.writeEntity(SerialEntity(EntityID(10), emptyMap()))
        }
    }

    @Test
    fun removeEntityWorks() {
        val (eid, storage) = EntityDBUtil.buildTestStorage()

        storage.removeEntity(eid).join()

        assertThat(storage.readEntity(eid).join()).isNull()
    }

    @Test
    fun removeEntityNoOpOnNoSuchEntity() {
        val (eid, storage) = EntityDBUtil.buildTestStorage()

        assertDoesNotThrow {
            storage.removeEntity(EntityID(10)).join()
        }
    }

    // global special entity tests

    @Test
    fun allocateEntityDoesNotAllocateGlobal() {
        val (eid, storage) = EntityDBUtil.buildTestStorage()

        val ret = storage.allocateEntity().join()

        assertThat(ret).isNotEqualTo(EntityID.GLOBAL)
    }

    @Test
    fun storageStartsWithEmptyGlobal() {
        val (eid, storage) = EntityDBUtil.buildTestStorage()

        assertThat(storage.readEntity(EntityID.GLOBAL).join()).isEqualTo(SerialEntity(EntityID.GLOBAL, emptyMap()))
    }
}
