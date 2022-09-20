package com.github.bedrockecs.server.game.zimpl.db.dimension

import com.github.bedrockecs.server.game.zimpl.db.dimension.DimensionDBTestUtil.withTestDB
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DimensionDBImplListTest {
    @Test
    fun listWorks() {
        withTestDB { evb, db, eid ->
            assertThat(db.list(eid)).containsExactly(TestComponentA())
        }
    }

    @Test
    fun listRejectsNotExistentDimension() {
        withTestDB { evb, db, eid ->
            assertThrows<IllegalArgumentException> {
                db.list(1)
            }
        }
    }
}
