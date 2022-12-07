package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.zimpl.db.entity.testdata.EntityDBUtil.withTestDB
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestComponentA
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestEntities.TEST_SERIAL_ENTITY_A_PARTS
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestTypeComponents.TYPE_A
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
    fun listWorksOnNotLoadedEntity() {
        withTestDB { eid, evb, db ->
            assertThat(db.list(eid)).containsExactlyElementsOf(TEST_SERIAL_ENTITY_A_PARTS.values)
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
