package com.github.bedrockecs.server.game.zimpl.db.entity

import com.github.bedrockecs.server.game.db.common.LoadType
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.server.game.db.entity.event.EntityLoadingEvent
import com.github.bedrockecs.server.game.eventbus.listensFor
import com.github.bedrockecs.server.game.zimpl.db.entity.EntityDBUtil.withTestDB
import com.github.bedrockecs.server.game.zimpl.db.entity.TestTypeComponents.TYPE_A
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class EntityDBImplLoadUnLoadTest {

    @Test
    fun loadWorks() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))
            val serial = db.unload(eid)

            db.load(serial)

            assertThat(db.list(eid)).containsExactly(TYPE_A, TestComponentA())
        }
    }

    @Test
    fun loadEmitsEvents() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))
            val serial = db.unload(eid)
            var listenerEID: EntityID? = null
            val listener = { event: EntityLoadingEvent ->
                // arguments
                listenerEID = event.eid
                assertThat(event.type).isEqualTo(LoadType.LOAD)
                // post-load
                assertThat(db.list(listenerEID!!)).containsExactly(TYPE_A, TestComponentA())

                Unit
            }
            val sub = evb.listensFor("testing", TYPE_A.entityType, listener)

            db.load(serial)

            assertThat(eid.value).isEqualTo(listenerEID!!.value)

            sub.close()
        }
    }

    @Test
    fun duplicateLoadFails() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))
            val serial = db.unload(eid)
            db.load(serial)

            assertThrows<IllegalArgumentException> {
                db.load(serial)
            }
        }
    }

    @Test
    fun unloadWorks() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))

            val serial = db.unload(eid)

            assertThat(serial.id).isEqualTo(eid)
            assertThat(serial.components).isEqualTo(
                mapOf(
                    EntityTypeComponent::class.java to TYPE_A,
                    TestComponentA::class.java to TestComponentA()
                )
            )
        }
    }

    @Test
    fun unloadEmitsEvents() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))
            var listenerEID: EntityID? = null
            val listener = { event: EntityLoadingEvent ->
                // arguments
                listenerEID = event.eid
                assertThat(event.type).isEqualTo(LoadType.UNLOAD)
                // pre-unload
                assertThat(db.list(listenerEID!!)).containsExactly(TYPE_A, TestComponentA())

                Unit
            }
            val sub = evb.listensFor("testing", TYPE_A.entityType, listener)

            db.unload(eid)

            assertThat(eid.value).isEqualTo(listenerEID!!.value)

            sub.close()
        }
    }

    @Test
    fun unloadRejectsNotExistentEntity() {
        withTestDB { evb, db ->
            assertThrows<IllegalArgumentException> {
                db.unload(EntityID(10))
            }
        }
    }
}
