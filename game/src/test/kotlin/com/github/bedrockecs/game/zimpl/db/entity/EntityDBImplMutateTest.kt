package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.event.EntityMutationEvent
import com.github.bedrockecs.game.eventbus.listensFor
import com.github.bedrockecs.game.zimpl.db.entity.testdata.EntityDBUtil.withTestDB
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestComponentA
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestEntities.TEST_SERIAL_ENTITY_A_PARTS
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestTypeComponents.TYPE_A
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EntityDBImplMutateTest {

    // add //

    @Test
    fun mutateAddsComponent() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf())

            db.mutate(eid, TestComponentA::class.java) { TestComponentA() }

            assertThat(db.list(eid)).containsExactly(TYPE_A, TestComponentA())
        }
    }

    @Test
    fun mutateAddsComponentEmitsEvents() {
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
            val eid = db.create(TYPE_A, setOf())

            db.mutate(eid, TestComponentA::class.java) { TestComponentA() }

            assertThat(eid.value).isEqualTo(listenerEID!!.value)

            sub.close()
        }
    }

    // changes //

    @Test
    fun mutateChangesComponent() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf())

            db.mutate(eid, TestComponentA::class.java) { TestComponentA(test = "updated") }

            assertThat(db.list(eid)).containsExactly(TYPE_A, TestComponentA(test = "updated"))
        }
    }

    @Test
    fun mutateChangesComponentEmitsEvents() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))
            var listenerEID: EntityID? = null
            val listener = { event: EntityMutationEvent ->
                // arguments
                listenerEID = event.eid
                assertThat(event.type).isEqualTo(MutateType.UPDATE)
                // post-change
                assertThat(db.list(listenerEID!!)).containsExactly(TYPE_A, TestComponentA(test = "updated"))

                Unit
            }
            val sub = evb.listensFor("testing", TestComponentA.type, listener)

            db.mutate(eid, TestComponentA::class.java) { TestComponentA(test = "updated") }

            assertThat(eid.value).isEqualTo(listenerEID!!.value)

            sub.close()
        }
    }

    // removes //

    @Test
    fun mutateRemovesComponent() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))

            db.mutate(eid, TestComponentA::class.java) { null }

            assertThat(db.list(eid)).containsExactly(TYPE_A)
        }
    }

    @Test
    fun mutateRemovesComponentEmitsEvents() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))
            var listenerEID: EntityID? = null
            val listener = { event: EntityMutationEvent ->
                // arguments
                listenerEID = event.eid
                assertThat(event.type).isEqualTo(MutateType.REMOVE)
                // pre-destroy
                assertThat(db.list(listenerEID!!)).containsExactly(TYPE_A, TestComponentA())

                Unit
            }
            val sub = evb.listensFor("testing", TestComponentA.type, listener)

            db.mutate(eid, TestComponentA::class.java) { null }

            assertThat(eid.value).isEqualTo(listenerEID!!.value)

            sub.close()
        }
    }

    // exceptional cases //

    @Test
    fun mutateWorksOnNotLoadedEntity() {
        withTestDB { eid, evb, db ->
            db.mutate(eid, TestComponentA::class.java) { TestComponentA(test = "updated") }

            assertThat(db.list(eid)).containsExactlyElementsOf(
                (TEST_SERIAL_ENTITY_A_PARTS + mapOf(TestComponentA::class.java to TestComponentA(test = "updated"))).values
            )
        }
    }

    @Test
    fun mutateRejectsNotExistentEntity() {
        withTestDB { evb, db ->
            assertThrows<IllegalArgumentException> {
                db.mutate(EntityID(10), TestComponentA::class.java) { null }
            }
        }
    }
}
