package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.game.zimpl.db.entity.testdata.EntityDBUtil.withTestDB
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestComponentA
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestTypeComponents.TYPE_A
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EntityDBImplReadTest {
    @Test
    fun readReadsComponent() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))

            assertThat(db.read(eid, EntityTypeComponent::class.java)).isEqualTo(TYPE_A)
        }
    }

    @Test
    fun readReturnsNullOnNoComponent() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf())

            assertThat(db.read(eid, TestComponentA::class.java)).isNull()
        }
    }

    @Test
    fun readWorksOnNotLoadedEntity() {
        withTestDB { eid, evb, db ->
            val ret = db.read(eid, TestComponentA::class.java)

            assertThat(ret).isEqualTo(TestComponentA())
        }
    }

    @Test
    fun readRejectsNotExistentEntity() {
        withTestDB { evb, db ->
            assertThrows<IllegalArgumentException> {
                db.read(EntityID(10), TestComponentA::class.java)
            }
        }
    }
}
