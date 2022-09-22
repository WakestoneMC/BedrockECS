package com.github.bedrockecs.server.game.zimpl.db.dimension

import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent
import com.github.bedrockecs.server.game.db.dimension.event.DimensionLifecycleEvent
import com.github.bedrockecs.server.game.db.dimension.event.DimensionMutationEvent
import com.github.bedrockecs.server.game.eventbus.listensFor
import com.github.bedrockecs.server.game.zimpl.eventbus.EventBusImpl
import org.assertj.core.api.Assertions.assertThat
import kotlin.properties.Delegates

object DimensionDBTestUtil {
    fun withTestDB(func: (EventBusImpl, DimensionDBImpl, Short) -> Unit) {
        val evb = EventBusImpl()
        val db = DimensionDBImpl(evb)
        val eid = db.create(setOf(TestComponentA()))
        try {
            func(evb, db, eid)
        } finally {
            db.close()
        }
    }

    fun lifecycleEventTest(
        dispatchToken: Any?,
        listener: (DimensionDBImpl, Short, DimensionLifecycleEvent) -> Unit,
        func: (EventBusImpl, DimensionDBImpl, Short) -> Unit
    ) {
        withTestDB { evb, db, eid ->
            var invoked = false
            val listener: (DimensionLifecycleEvent) -> Unit = {
                invoked = true
                listener(db, eid, it)
            }
            val sub = evb.listensFor("test", dispatchToken, listener)

            func(evb, db, eid)

            assertThat(invoked).isTrue

            sub.close()
        }
    }

    fun mutationEventTest(
        dispatchToken: Any?,
        listener: (DimensionDBImpl, Short, DimensionMutationEvent) -> Unit,
        func: (EventBusImpl, DimensionDBImpl, Short) -> Unit
    ) {
        withTestDB { evb, db, eid ->
            var invoked = false
            val listener: (DimensionMutationEvent) -> Unit = {
                invoked = true
                listener(db, eid, it)
            }
            val sub = evb.listensFor("test", dispatchToken, listener)

            func(evb, db, eid)

            assertThat(invoked).isTrue

            sub.close()
        }
    }

    fun preUpdateCallbackTest(
        preUpdate: (
            db: DimensionDBImpl,
            realEid: Short,
            eid: Short,
            from: DimensionComponent?,
            to: DimensionComponent?
        ) -> Unit,
        func: (EventBusImpl, DimensionDBImpl, Short) -> Unit
    ) {
        val evb = EventBusImpl()
        var eid by Delegates.notNull<Short>()
        lateinit var db: DimensionDBImpl
        var invoked = false
        val callback: PreUpdateCallback = { reid, from, to ->
            // invoked
            invoked = true
            preUpdate(db, eid, reid, from, to)
        }
        db = DimensionDBImpl(evb, preUpdate = callback)
        eid = db.create(setOf(TestComponentA()))

        try {
            func(evb, db, eid)
            assertThat(invoked).isTrue
        } finally {
            db.close()
        }
    }

    fun postDestroyCallbackTest(
        postDestroy: (
            db: DimensionDBImpl,
            realEid: Short,
            eid: Short,
            components: Set<DimensionComponent>
        ) -> Unit,
        func: (EventBusImpl, DimensionDBImpl, Short) -> Unit
    ) {
        val evb = EventBusImpl()
        var eid by Delegates.notNull<Short>()
        lateinit var db: DimensionDBImpl
        var invoked = false
        val callback: PostDestroyCallback = { reid, components ->
            // invoked
            invoked = true
            postDestroy(db, eid, reid, components)
        }
        db = DimensionDBImpl(evb, postDestroy = callback)
        eid = db.create(setOf(TestComponentA()))

        try {
            func(evb, db, eid)
            assertThat(invoked).isTrue
        } finally {
            db.close()
        }
    }
}
