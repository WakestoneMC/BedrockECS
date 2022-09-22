package com.github.bedrockecs.server.game.zimpl.db.dimension

import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.zimpl.db.dimension.DimensionDBTestUtil.mutationEventTest
import com.github.bedrockecs.server.game.zimpl.db.dimension.DimensionDBTestUtil.preUpdateCallbackTest
import com.github.bedrockecs.server.game.zimpl.db.dimension.DimensionDBTestUtil.withTestDB
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DimensionDBImplMutateTest {

    // add //

    @Test
    fun mutateAddsComponent() {
        withTestDB { evb, db, eid ->
            db.mutate(eid, TestComponentB::class.java) { TestComponentB() }

            assertThat(db.list(eid)).contains(TestComponentB())
        }
    }

    @Test
    fun mutateAddsComponentEmitsEvents() {
        mutationEventTest(
            TestComponentB.TYPE,
            listener = { db, eid, event ->
                // arguments
                assertThat(event.pos).isEqualTo(eid)
                assertThat(event.type).isEqualTo(MutateType.ADD)
                // post-update
                assertThat(db.list(event.pos)).containsExactly(TestComponentA(), TestComponentB())
            },
            func = { evb, db, eid ->
                db.mutate(eid, TestComponentB::class.java) { TestComponentB() }
            }
        )
    }

    @Test
    fun mutateAddsComponentEmitsCallback() {
        preUpdateCallbackTest(
            preUpdate = { db, createdEid, eid, from, to ->
                // arguments
                assertThat(createdEid).isEqualTo(eid)
                assertThat(from).isNull()
                assertThat(to).isEqualTo(TestComponentB())
                // pre-update
                assertThat(db.list(eid)).containsExactly(TestComponentA())
            },
            func = { evb, db, eid ->
                db.mutate(eid, TestComponentB::class.java) { TestComponentB() }
            }
        )
    }

    // changes //

    @Test
    fun mutateChangesComponent() {
        withTestDB { evb, db, eid ->
            db.mutate(eid, TestComponentA::class.java) { TestComponentA(test = "updated") }

            assertThat(db.list(eid)).contains(TestComponentA(test = "updated"))
        }
    }

    @Test
    fun mutateChangesComponentEmitsEvents() {
        mutationEventTest(
            TestComponentA.TYPE,
            listener = { db, eid, event ->
                // arguments
                assertThat(event.pos).isEqualTo(eid)
                assertThat(event.type).isEqualTo(MutateType.UPDATE)
                // post-update
                assertThat(db.list(event.pos)).containsExactly(TestComponentA(test = "updated"))
            },
            func = { evb, db, eid ->
                db.mutate(eid, TestComponentA::class.java) { TestComponentA(test = "updated") }
            }
        )
    }

    @Test
    fun mutateChangesComponentEmitsCallback() {
        preUpdateCallbackTest(
            preUpdate = { db, createdEid, eid, from, to ->
                // arguments
                assertThat(createdEid).isEqualTo(eid)
                assertThat(from).isEqualTo(TestComponentA())
                assertThat(to).isEqualTo(TestComponentA(test = "updated"))
                // pre-update
                assertThat(db.list(eid)).containsExactly(TestComponentA())
            },
            func = { evb, db, eid ->
                db.mutate(eid, TestComponentA::class.java) { TestComponentA(test = "updated") }
            }
        )
    }

    // removes //

    @Test
    fun mutateRemovesComponent() {
        withTestDB { evb, db, eid ->
            db.mutate(eid, TestComponentA::class.java) { null }

            assertThat(db.list(eid)).isEmpty()
        }
    }

    @Test
    fun mutateRemovesComponentEmitsEvents() {
        mutationEventTest(
            TestComponentA.TYPE,
            listener = { db, eid, event ->
                // arguments
                assertThat(event.pos).isEqualTo(eid)
                assertThat(event.type).isEqualTo(MutateType.REMOVE)
                // post-update
                assertThat(db.list(event.pos)).containsExactly(TestComponentA())
            },
            func = { evb, db, eid ->
                db.mutate(eid, TestComponentA::class.java) { null }
            }
        )
    }

    @Test
    fun mutateRemovesComponentEmitsCallback() {
        preUpdateCallbackTest(
            preUpdate = { db, createdEid, eid, from, to ->
                // arguments
                assertThat(createdEid).isEqualTo(eid)
                assertThat(from).isEqualTo(TestComponentA())
                assertThat(to).isEqualTo(null)
                // pre-update
                assertThat(db.list(eid)).containsExactly(TestComponentA())
            },
            func = { evb, db, eid ->
                db.mutate(eid, TestComponentA::class.java) { null }
            }
        )
    }

    // exceptional cases //

    @Test
    fun mutateRejectsNotExistentDimension() {
        withTestDB { evb, db, eid ->
            assertThrows<IllegalArgumentException> {
                db.mutate(2, TestComponentA::class.java) { null }
            }
        }
    }
}
