package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.db.world.WorldDBSerial
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows

class AutoAssigningWorldDBSerialTest {
    @Test
    fun airBlockTypeWorks() {
        val serial = buildTestSerial()

        assertThat(serial.airBlockType).isEqualTo(TestAirBlockType.primary)
    }

    @Test
    fun blockTypeForWorks() {
        val serial = buildTestSerial()

        assertThat(serial.blockTypeFor(0)).isEqualTo(TestAirBlockType.primary)
    }

    @Test
    fun blockTypeForRejectsUnassignedID() {
        val serial = buildTestSerial()

        assertThrows<IllegalArgumentException> {
            serial.blockTypeFor(1)
        }
    }

    @Test
    fun idForWorks() {
        val serial = buildTestSerial()

        assertThat(serial.idFor(TestAirBlockType.primary)).isEqualTo(0)
    }

    @Test
    fun idAssignsIDForUnseenType() {
        val serial = buildTestSerial()

        val ret = serial.idFor(TestDirtBlockType.primary)

        assertThat(ret).isEqualTo(1)
        assertThat(serial.blockTypeFor(1)).isEqualTo(TestDirtBlockType.primary)
    }

    private fun buildTestSerial(): WorldDBSerial {
        return WorldDBSerial.createAutoAssigning(TestAirBlockType.primary)
    }
}
