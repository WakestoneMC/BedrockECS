package com.github.bedrockecs.server.game.zimpl.db.dimension

import com.github.bedrockecs.server.game.db.common.LifecycleType
import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent
import com.github.bedrockecs.server.game.db.dimension.event.DimensionLifecycleEvent
import com.github.bedrockecs.server.game.db.dimension.event.DimensionMutationEvent
import com.github.bedrockecs.server.game.eventbus.listensFor
import com.github.bedrockecs.server.game.zimpl.eventbus.EventBusImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DimensionDBImplCreateTest {

    // core functionality //

    @Test
    fun createCreatesNewDimension() {
        val evb = EventBusImpl()
        val db = DimensionDBImpl(evb)

        val eid = db.create(setOf(TestComponentA()))

        assertThat(db.list(eid)).containsExactly(TestComponentA())

        db.close()
    }

    // eventing //

    @Test
    fun createEmitsLifecycleEvents() {
        val evb = EventBusImpl()
        val db = DimensionDBImpl(evb)
        val listener = mock<(DimensionLifecycleEvent) -> Unit>()
        val sub = evb.listensFor("test", listener)

        val eid = db.create(setOf(TestComponentA()))

        verify(listener) {
            1 * { listener(DimensionLifecycleEvent(eid, LifecycleType.CREATE)) }
        }

        sub.close()
        db.close()
    }

    @Test
    fun createEmitsLifecycleEventsAtPostCreate() {
        val evb = EventBusImpl()
        val db = DimensionDBImpl(evb)
        val listener: (DimensionLifecycleEvent) -> Unit = {
            assertThat(db.list(it.pos)).containsExactly(TestComponentA())
        }
        val sub = evb.listensFor("test", listener)

        db.create(setOf(TestComponentA()))

        sub.close()
        db.close()
    }

    @Test
    fun createEmitsMutationEvents() {
        val evb = EventBusImpl()
        val db = DimensionDBImpl(evb)
        val listenerA = mock<(DimensionMutationEvent) -> Unit>()
        val subA = evb.listensFor("testA", TestComponentA.TYPE, listenerA)
        val listenerB = mock<(DimensionMutationEvent) -> Unit>()
        val subB = evb.listensFor("testB", TestComponentB.TYPE, listenerB)

        val eid = db.create(setOf(TestComponentA(), TestComponentB()))

        verify(listenerA) {
            1 * { listenerA(DimensionMutationEvent(eid, MutateType.ADD)) }
        }
        verify(listenerB) {
            1 * { listenerB(DimensionMutationEvent(eid, MutateType.ADD)) }
        }

        subB.close()
        subA.close()
        db.close()
    }

    @Test
    fun createEmitsMutationEventsAtPostCreate() {
        val evb = EventBusImpl()
        val db = DimensionDBImpl(evb)
        val listenerA: (DimensionMutationEvent) -> Unit = {
            assertThat(db.list(it.pos)).containsExactly(TestComponentA())
        }
        val subA = evb.listensFor("testA", TestComponentA.TYPE, listenerA)

        db.create(setOf(TestComponentA()))

        subA.close()
        db.close()
    }

    // callback //

    @Test
    fun createEmitsPreCreateCallback() {
        val evb = EventBusImpl()
        val preCreate = mock<(Short, Set<DimensionComponent>) -> Unit>()
        val db = DimensionDBImpl(evb, preCreate)

        val eid = db.create(setOf(TestComponentA()))

        verify(preCreate) {
            1 * { preCreate(eid, setOf(TestComponentA())) }
        }

        db.close()
    }

    @Test
    fun createEmitsPreCreateCallbackAtPreCreate() {
        val evb = EventBusImpl()
        lateinit var dbHolder: DimensionDBImpl
        val preCreate: PreCreateCallback = { eid, p1 ->
            assertThrows<IllegalArgumentException> {
                dbHolder.list(eid)
            }
        }
        val db = DimensionDBImpl(evb, preCreate)
        dbHolder = db

        db.create(setOf(TestComponentA()))

        db.close()
    }

    @Test
    fun createIsTransactionalOnPreCreateFailure() {
        val evb = EventBusImpl()
        val preCreate: PreCreateCallback = { eid, p1 ->
            throw RuntimeException("I failed lol")
        }
        val db = DimensionDBImpl(evb, preCreate)

        try {
            db.create(setOf(TestComponentA()))
        } catch (ex: RuntimeException) {
            // pass
        }

        assertThrows<IllegalArgumentException> {
            db.list(0)
        }

        db.close()
    }
}
