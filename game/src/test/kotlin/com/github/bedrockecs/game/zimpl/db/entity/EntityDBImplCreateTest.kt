package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.db.common.LifecycleType
import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.event.EntityCreatingEvent
import com.github.bedrockecs.game.db.entity.event.EntityLifecycleEvent
import com.github.bedrockecs.game.db.entity.event.EntityMutationEvent
import com.github.bedrockecs.game.eventbus.listensFor
import com.github.bedrockecs.game.zimpl.db.entity.testdata.EntityDBUtil.withTestDB
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestComponentA
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestTypeComponents.TYPE_A
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EntityDBImplCreateTest {
    @Test
    fun createCreatesNewEntity() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))

            assertThat(db.list(eid)).containsExactly(TYPE_A, TestComponentA())
        }
    }

    @Test
    fun createRejectsTypeInExtras() {
        withTestDB { evb, db ->
            assertThrows<IllegalArgumentException> {
                db.create(TYPE_A, setOf(TYPE_A, TestComponentA()))
            }
        }
    }

    @Test
    fun createEmitCreatingEvents() {
        withTestDB { evb, db ->
            var listenerEID: EntityID? = null
            val listener = { event: EntityCreatingEvent ->
                // arguments
                listenerEID = event.eid
                assertThat(event.type).isEqualTo(TYPE_A)
                assertThat(event.extras).containsExactly(TestComponentA())
                // pre-construct
                assertThat(db.list(listenerEID!!)).isEmpty()
                Unit
            }
            val sub = evb.listensFor("testing", TYPE_A.entityType, listener)

            val eid = db.create(TYPE_A, setOf(TestComponentA()))

            assertThat(eid.value).isEqualTo(listenerEID!!.value)

            sub.close()
        }
    }

    @Test
    fun createEmitLifecycleEvents() {
        withTestDB { evb, db ->
            var listenerEID: EntityID? = null
            val listener = { event: EntityLifecycleEvent ->
                // arguments
                listenerEID = event.pos
                assertThat(event.type).isEqualTo(LifecycleType.CREATE)
                // post-construct
                assertThat(db.list(listenerEID!!)).containsExactly(TYPE_A, TestComponentA())

                Unit
            }
            val sub = evb.listensFor("testing", TYPE_A.entityType, listener)

            val eid = db.create(TYPE_A, setOf(TestComponentA()))

            assertThat(eid.value).isEqualTo(listenerEID!!.value)

            sub.close()
        }
    }

    @Test
    fun createEmitMutationEvents() {
        withTestDB { evb, db ->
            var listenerEID: EntityID? = null
            val listener = { event: EntityMutationEvent ->
                // arguments
                listenerEID = event.eid
                assertThat(event.type).isEqualTo(MutateType.ADD)
                // post-construct
                assertThat(db.list(listenerEID!!)).containsExactly(TYPE_A, TestComponentA())

                Unit
            }
            val sub = evb.listensFor("testing", TestComponentA.type, listener)

            val eid = db.create(TYPE_A, setOf(TestComponentA()))

            assertThat(eid.value).isEqualTo(listenerEID!!.value)

            sub.close()
        }
    }
}
