package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.world.event.ChunkMutationEvent
import com.github.bedrockecs.game.eventbus.listensFor
import com.github.bedrockecs.game.zimpl.db.world.testdata.TestChunkComponentA
import com.github.bedrockecs.game.zimpl.db.world.testdata.TestChunkComponentB
import com.github.bedrockecs.game.zimpl.db.world.testdata.TestChunks.TEST_CHUNK_A_POS
import com.github.bedrockecs.game.zimpl.db.world.testdata.WorldDBUtil.withTestDB
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WorldDBImplChunkTest {

    @Test
    fun readChunkWorks() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            val ret = db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java)

            assertThat(ret).isEqualTo(TestChunkComponentA())
        }
    }

    @Test
    fun readChunkReturnsNullOnNoSuchComponent() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            val ret = db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentB::class.java)

            assertThat(ret).isNull()
        }
    }

    @Test
    fun readChunkWorksOnNotLoadedChunk() {
        withTestDB { evb, db ->
            val ret = db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java)

            assertThat(ret).isEqualTo(TestChunkComponentA())
        }
    }

    @Test
    fun listChunkWorks() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            val ret = db.listChunk(TEST_CHUNK_A_POS)

            assertThat(ret).containsExactly(TestChunkComponentA())
        }
    }

    @Test
    fun listChunkWorksOnNotLoadedChunk() {
        withTestDB { evb, db ->
            val ret = db.listChunk(TEST_CHUNK_A_POS)

            assertThat(ret).containsExactly(TestChunkComponentA())
        }
    }

    @Test
    fun mutateChunkAddsComponent() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

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
                assertThat(db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentB::class.java)).isEqualTo(
                    TestChunkComponentB()
                )
                Unit
            }
            evb.listensFor("test", TestChunkComponentB.type, listener)
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentB::class.java) { TestChunkComponentB() }

            assertThat(invoked).isTrue
        }
    }

    @Test
    fun mutateChunkChangesComponent() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

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
            evb.listensFor("test", TestChunkComponentA.type, listener)
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java) { TestChunkComponentA(test = "updated") }

            assertThat(invoked).isTrue
        }
    }

    @Test
    fun mutateChunkRemovesComponent() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

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
                assertThat(db.readChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java)).isEqualTo(
                    TestChunkComponentA()
                )
                Unit
            }
            evb.listensFor("test", TestChunkComponentA.type, listener)
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java) { null }

            assertThat(invoked).isTrue
        }
    }

    @Test
    fun mutateChunkWorksOnNotLoadedChunk() {
        withTestDB { evb, db ->
            db.mutateChunk(TEST_CHUNK_A_POS, TestChunkComponentA::class.java) { TestChunkComponentA() }

            assertThat(db.listChunk(TEST_CHUNK_A_POS)).containsExactly(TestChunkComponentA())
        }
    }
}
