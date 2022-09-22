package com.github.bedrockecs.server.game.zimpl.db.dimension

import com.github.bedrockecs.server.game.zimpl.db.dimension.DimensionDBTestUtil.withTestDB
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DimensionDBImplReadTest {
    @Test
    fun readWorks() {
        withTestDB { evb, db, eid ->
            assertThat(db.read(eid, TestComponentA::class.java)).isEqualTo(TestComponentA())
        }
    }

    @Test
    fun readReturnsNullOnNoSuchComponent() {
        withTestDB { evb, db, eid ->
            assertThat(db.read(eid, TestComponentB::class.java)).isNull()
        }
    }

    @Test
    fun readRejectsNotExistentDimension() {
        withTestDB { evb, db, eid ->
            assertThrows<IllegalArgumentException> {
                db.read(1, TestComponentA::class.java)
            }
        }
    }
}
