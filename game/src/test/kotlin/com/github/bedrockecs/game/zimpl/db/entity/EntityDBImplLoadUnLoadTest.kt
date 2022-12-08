package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.db.common.LoadType
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.data.CommonEntityTypes
import com.github.bedrockecs.game.db.entity.event.EntityLoadingEvent
import com.github.bedrockecs.game.eventbus.listensFor
import com.github.bedrockecs.game.zimpl.db.entity.testdata.EntityDBUtil.withTestDB
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestComponentA
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestEntities.TEST_SERIAL_ENTITY_A_PARTS
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestTypeComponents.TYPE_A
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class EntityDBImplLoadUnLoadTest {

    @Test
    fun isLoadedWorks() {
        withTestDB { eid, evb, db ->
            db.loadEntity(eid).join()

            assertThat(db.isLoaded(eid)).isTrue()
        }
    }

    @Test
    fun listLoadedEntitiesWorks() {
        withTestDB { eid, evb, db ->
            db.loadEntity(eid).join()

            assertThat(db.listLoadedEntities()).containsExactly(eid)
        }
    }

    @Test
    fun loadWorks() {
        withTestDB { eid, evb, db ->
            db.loadEntity(eid).join()

            assertThat(db.list(eid)).containsExactlyElementsOf(TEST_SERIAL_ENTITY_A_PARTS.values)
        }
    }

    @Test
    fun loadEmitsEvents() {
        withTestDB { eid, evb, db ->
            var listenerEID: EntityID? = null
            val listener = { event: EntityLoadingEvent ->
                // arguments
                listenerEID = event.eid
                assertThat(event.type).isEqualTo(LoadType.LOAD)
                // post-load
                assertThat(db.list(listenerEID!!)).containsExactlyElementsOf(TEST_SERIAL_ENTITY_A_PARTS.values)

                Unit
            }
            val sub = evb.listensFor("testing", CommonEntityTypes.PLAYER.entityType, listener)

            db.loadEntity(eid).join()

            assertThat(eid.value).isEqualTo(listenerEID!!.value)

            sub.close()
        }
    }

    @Test
    fun duplicateLoadIsNoOp() {
        withTestDB { eid, evb, db ->
            val serial = db.loadEntity(eid)

            assertDoesNotThrow {
                db.loadEntity(eid)
            }
        }
    }

    @Test
    fun unloadWorks() {
        withTestDB { evb, db ->
            val eid = db.create(TYPE_A, setOf(TestComponentA()))

            db.unloadEntity(eid).join()

            assertThat(db.listLoadedEntities()).isEmpty()
        }
    }

    @Test
    fun unloadEmitsEvents() {
        withTestDB { eid, evb, db ->
            db.loadEntity(eid).join()

            var listenerEID: EntityID? = null
            val listener = { event: EntityLoadingEvent ->
                // arguments
                listenerEID = event.eid
                assertThat(event.type).isEqualTo(LoadType.UNLOAD)
                // pre-unload
                assertThat(db.list(listenerEID!!)).containsExactlyElementsOf(TEST_SERIAL_ENTITY_A_PARTS.values)

                Unit
            }
            val sub = evb.listensFor("testing", CommonEntityTypes.PLAYER.entityType, listener)

            db.unloadEntity(eid).join()

            assertThat(eid.value).isEqualTo(listenerEID!!.value)

            sub.close()
        }
    }

    @Test
    fun unloadNoOpNotExistentEntity() {
        withTestDB { evb, db ->
            assertDoesNotThrow {
                db.unloadEntity(EntityID(10)).join()
            }
        }
    }
}
