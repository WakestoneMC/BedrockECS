package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.world.event.ChunkMutationEvent
import com.github.bedrockecs.server.game.eventbus.listensFor
import com.github.bedrockecs.server.game.zimpl.db.world.TestChunks.TEST_CHUNK_A
import com.github.bedrockecs.server.game.zimpl.db.world.TestChunks.TEST_CHUNK_A_POS
import com.github.bedrockecs.server.game.zimpl.db.world.WorldDBUtil.withTestDB
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class WorldDBImplChunkTest {

    @Test
    fun readChunkWorks() {
        withTestDB { evb, db ->
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            val ret = db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java)

            assertThat(ret).isEqualTo(TestChunkComponentA())
        }
    }

    @Test
    fun readChunkReturnsNullOnNotExistent() {
        withTestDB { evb, db ->
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            val ret = db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentB::class.java)

            assertThat(ret).isNull()
        }
    }

    @Test
    fun readChunkRejectsNotLoadedChunk() {
        withTestDB { evb, db ->
            assertThrows<IllegalArgumentException> {
                db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java)
            }
        }
    }

    @Test
    fun listChunkWorks() {
        withTestDB { evb, db ->
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            val ret = db.listChunk(TEST_CHUNK_A_POS)

            assertThat(ret).containsExactly(TestChunkComponentA())
        }
    }

    @Test
    fun listChunkRejectsNotLoadedChunk() {
        withTestDB { evb, db ->
            assertThrows<IllegalArgumentException> {
                db.listChunk(TEST_CHUNK_A_POS)
            }
        }
    }

    @Test
    fun mutateChunkAddsComponent() {
        withTestDB { evb, db ->
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentB::class.java) { TestChunkComponentB() }

            assertThat(db.listChunk(TEST_CHUNK_A_POS)).containsExactly(TestChunkComponentA(), TestChunkComponentB())
        }
    }

    @Test
    fun mutateChunkEmitsAddEvent() {
        withTestDB { evb, db ->
            var invoked = false
            val listener = { e: ChunkMutationEvent ->
                invoked = true
                // arguments
                assertThat(e.pos).isEqualTo(TEST_CHUNK_A_POS)
                assertThat(e.type).isEqualTo(MutateType.ADD)
                // post-add
                assertThat(db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentB::class.java)).isEqualTo(TestChunkComponentB())
                Unit
            }
            evb.listensFor("test", TestChunkComponentB.TYPE, listener)
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentB::class.java) { TestChunkComponentB() }

            assertThat(invoked).isTrue
        }
    }

    @Test
    fun mutateChunkChangesComponent() {
        withTestDB { evb, db ->
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java) { TestChunkComponentA(test = "updated") }

            assertThat(db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java))
                .isEqualTo(TestChunkComponentA(test = "updated"))
        }
    }

    @Test
    fun mutateChunkEmitsChangeEvent() {
        withTestDB { evb, db ->
            var invoked = false
            val listener = { e: ChunkMutationEvent ->
                invoked = true
                // arguments
                assertThat(e.pos).isEqualTo(TEST_CHUNK_A_POS)
                assertThat(e.type).isEqualTo(MutateType.UPDATE)
                // post-update
                assertThat(db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java))
                    .isEqualTo(TestChunkComponentA(test = "updated"))
                Unit
            }
            evb.listensFor("test", TestChunkComponentA.TYPE, listener)
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java) { TestChunkComponentA(test = "updated") }

            assertThat(invoked).isTrue
        }
    }

    @Test
    fun mutateChunkRemovesComponent() {
        withTestDB { evb, db ->
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java) { null }

            assertThat(db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java)).isNull()
        }
    }

    @Test
    fun mutateChunkEmitsRemoveEvent() {
        withTestDB { evb, db ->
            var invoked = false
            val listener = { e: ChunkMutationEvent ->
                invoked = true
                // arguments
                assertThat(e.pos).isEqualTo(TEST_CHUNK_A_POS)
                assertThat(e.type).isEqualTo(MutateType.REMOVE)
                // pre-remove
                assertThat(db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java)).isEqualTo(TestChunkComponentA())
                Unit
            }
            evb.listensFor("test", TestChunkComponentA.TYPE, listener)
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java) { null }

            assertThat(invoked).isTrue
        }
    }

    @Test
    fun mutateChunkRejectsNotLoadedChunk() {
        withTestDB { evb, db ->
            assertThrows<IllegalArgumentException> {
                db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java) { null }
            }
        }
    }
}
