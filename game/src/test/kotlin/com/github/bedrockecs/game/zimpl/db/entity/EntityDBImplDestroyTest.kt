package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.db.common.LifecycleType
import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.entity.event.EntityLifecycleEvent
import com.github.bedrockecs.game.db.entity.event.EntityMutationEvent
import com.github.bedrockecs.game.eventbus.listensFor
import com.github.bedrockecs.game.zimpl.db.entity.testdata.EntityDBUtil.withTestDB
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestComponentA
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestTypeComponents.TYPE_A
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class EntityDBImplDestroyTest {
    @Test
    fun destroyRemovesEntity() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))

            db.destroy(eid)

            assertThrows<IllegalArgumentException> {
                db.list(eid)
            }
        }
    }

    @Test
    fun destroyIsIdempotent() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))
            db.destroy(eid)

            assertDoesNotThrow {
                db.destroy(eid)
            }
        }
    }

    @Test
    fun destroysEmitLifecycleEvents() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))
            var invoked = false
            val listener = { event: EntityLifecycleEvent ->
                invoked = true
                // arguments
                assertThat(event.pos).isEqualTo(eid)
                assertThat(event.type).isEqualTo(LifecycleType.DESTROY)
                // pre-destroy
                assertThat(db.list(eid)).containsExactly(TYPE_A, TestComponentA())

                Unit
            }
            val sub = evb.listensFor("testing", TYPE_A.entityType, listener)

            db.destroy(eid)

            assertThat(invoked).isTrue

            sub.close()
        }
    }

    @Test
    fun destroyEmitMutationEvents() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))
            var invoked = false
            val listener = { event: EntityMutationEvent ->
                invoked = true
                // arguments
                assertThat(event.eid).isEqualTo(eid)
                assertThat(event.type).isEqualTo(MutateType.REMOVE)
                // pre-destroy
                assertThat(db.list(eid)).containsExactly(TYPE_A, TestComponentA())

                Unit
            }
            val sub = evb.listensFor("testing", TestComponentA.type, listener)

            db.destroy(eid)

            assertThat(invoked).isTrue

            sub.close()
        }
    }
}
