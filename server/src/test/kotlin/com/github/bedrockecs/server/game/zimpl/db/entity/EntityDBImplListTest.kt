package com.github.bedrockecs.server.game.zimpl.db.entity

import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.zimpl.db.entity.EntityDBUtil.withTestDB
import com.github.bedrockecs.server.game.zimpl.db.entity.TestTypeComponents.TYPE_A
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EntityDBImplListTest {
    @Test
    fun listListsComponents() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))

            assertThat(db.list(eid)).containsExactly(TYPE_A, TestComponentA())
        }
    }

    @Test
    fun listRejectsNotExistentEntityID() {
        withTestDB { evb, db ->
            assertThrows<IllegalArgumentException> {
                db.list(EntityID(10))
            }
        }
    }
}
