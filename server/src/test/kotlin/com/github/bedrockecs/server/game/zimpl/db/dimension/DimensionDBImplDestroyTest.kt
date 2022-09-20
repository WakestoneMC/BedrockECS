package com.github.bedrockecs.server.game.zimpl.db.dimension

import com.github.bedrockecs.server.game.db.common.LifecycleType
import com.github.bedrockecs.server.game.zimpl.db.dimension.DimensionDBTestUtil.lifecycleEventTest
import com.github.bedrockecs.server.game.zimpl.db.dimension.DimensionDBTestUtil.postDestroyCallbackTest
import com.github.bedrockecs.server.game.zimpl.db.dimension.DimensionDBTestUtil.withTestDB
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DimensionDBImplDestroyTest {

    // normal case //

    @Test
    fun destroyRemovesDimension() {
        withTestDB { evb, db, eid ->
            db.destroy(eid)

            assertThrows<IllegalArgumentException> {
                db.list(eid)
            }
        }
    }

    @Test
    fun destroyEmitsEvents() {
        lifecycleEventTest(
            null,
            listener = { db, eid, event ->
                // arguments
                assertThat(event.pos).isEqualTo(eid)
                assertThat(event.type).isEqualTo(LifecycleType.DESTROY)
                // post-destroy
                assertThrows<IllegalArgumentException> {
                    db.list(eid)
                }
            },
            func = { evb, db, eid ->
                db.destroy(eid)
            }
        )
    }

    @Test
    fun destroyEmitsCallback() {
        postDestroyCallbackTest(
            postDestroy = { db, realEid, eid, components ->
                // arguments
                assertThat(eid).isEqualTo(realEid)
                assertThat(components).containsExactly(TestComponentA())
                // post-destroy
                assertThrows<IllegalArgumentException> {
                    db.list(eid)
                }
            },
            func = { evb, db, eid ->
                db.destroy(eid)
            }
        )
    }

    // exceptional cases //

    @Test
    fun destroyNoopOnNotExistsDimension() {
        withTestDB { evb, db, eid ->
            db.destroy(1)
        }
    }
}
