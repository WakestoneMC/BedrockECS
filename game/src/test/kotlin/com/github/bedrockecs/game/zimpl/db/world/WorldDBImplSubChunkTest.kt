package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.world.event.SubChunkMutationEvent
import com.github.bedrockecs.game.eventbus.listensFor
import com.github.bedrockecs.game.zimpl.db.world.testdata.TestChunks.TEST_CHUNK_A_POS
import com.github.bedrockecs.game.zimpl.db.world.testdata.TestChunks.TEST_SUBCHUNK_A_POS
import com.github.bedrockecs.game.zimpl.db.world.testdata.TestSubChunkComponentA
import com.github.bedrockecs.game.zimpl.db.world.testdata.TestSubChunkComponentB
import com.github.bedrockecs.game.zimpl.db.world.testdata.WorldDBUtil.withTestDB
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WorldDBImplSubChunkTest {

    @Test
    fun readSubChunkWorks() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            val ret = db.readSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentA::class.java)

            assertThat(ret).isEqualTo(TestSubChunkComponentA())
        }
    }

    @Test
    fun readSubChunkReturnsNullOnNotExistent() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            val ret = db.readSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentB::class.java)

            assertThat(ret).isNull()
        }
    }

    @Test
    fun readSubChunkWorksOnNotLoadedChunk() {
        withTestDB { evb, db ->
            val ret = db.readSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentA::class.java)

            assertThat(ret).isEqualTo(TestSubChunkComponentA())
        }
    }

    @Test
    fun listSubChunkWorks() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            val ret = db.listSubChunk(TEST_SUBCHUNK_A_POS)

            assertThat(ret).containsExactly(TestSubChunkComponentA())
        }
    }

    @Test
    fun listSubChunkWorksOnNotLoadedChunk() {
        withTestDB { evb, db ->
            val ret = db.listSubChunk(TEST_SUBCHUNK_A_POS)

            assertThat(ret).containsExactly(TestSubChunkComponentA())
        }
    }

    @Test
    fun mutateSubChunkAddsComponent() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.mutateSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentB::class.java) { TestSubChunkComponentB() }

            assertThat(db.listSubChunk(TEST_SUBCHUNK_A_POS)).contains(TestSubChunkComponentB())
        }
    }

    @Test
    fun mutateSubChunkEmitsAddEvent() {
        withTestDB { evb, db ->
            var invoked = false
            val listener = { e: SubChunkMutationEvent ->
                invoked = true
                // arguments
                assertThat(e.pos).isEqualTo(TEST_SUBCHUNK_A_POS)
                assertThat(e.type).isEqualTo(MutateType.ADD)
                // post-add
                assertThat(db.readSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentB::class.java))
                    .isEqualTo(TestSubChunkComponentB())
                Unit
            }
            evb.listensFor("test", TestSubChunkComponentB.type, listener)
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.mutateSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentB::class.java) { TestSubChunkComponentB() }

            assertThat(invoked).isTrue
        }
    }

    @Test
    fun mutateSubChunkChangesComponent() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.mutateSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentA::class.java) { TestSubChunkComponentA("updated") }

            assertThat(db.listSubChunk(TEST_SUBCHUNK_A_POS)).containsExactly(TestSubChunkComponentA("updated"))
        }
    }

    @Test
    fun mutateSubChunkEmitsChangeEvent() {
        withTestDB { evb, db ->
            var invoked = false
            val listener = { e: SubChunkMutationEvent ->
                invoked = true
                // arguments
                assertThat(e.pos).isEqualTo(TEST_SUBCHUNK_A_POS)
                assertThat(e.type).isEqualTo(MutateType.UPDATE)
                // post-update
                assertThat(db.readSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentA::class.java))
                    .isEqualTo(TestSubChunkComponentA("updated"))
                Unit
            }
            evb.listensFor("test", TestSubChunkComponentA.type, listener)
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.mutateSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentA::class.java) { TestSubChunkComponentA("updated") }

            assertThat(invoked).isTrue
        }
    }

    @Test
    fun mutateSubChunkRemovesComponent() {
        withTestDB { evb, db ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.mutateSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentA::class.java) { null }

            assertThat(db.readSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentA::class.java)).isNull()
        }
    }

    @Test
    fun mutateSubChunkEmitsRemoveEvent() {
        withTestDB { evb, db ->
            var invoked = false
            val listener = { e: SubChunkMutationEvent ->
                invoked = true
                // arguments
                assertThat(e.pos).isEqualTo(TEST_SUBCHUNK_A_POS)
                assertThat(e.type).isEqualTo(MutateType.REMOVE)
                // pre-remove
                assertThat(db.readSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentA::class.java))
                    .isEqualTo(TestSubChunkComponentA())
                Unit
            }
            evb.listensFor("test", TestSubChunkComponentA.type, listener)
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.mutateSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentA::class.java) { null }

            assertThat(invoked).isTrue
        }
    }

    @Test
    fun mutateChunkWorksOnNotLoadedChunk() {
        withTestDB { evb, db ->
            db.mutateSubChunk(TEST_SUBCHUNK_A_POS, TestSubChunkComponentA::class.java) { TestSubChunkComponentA() }

            assertThat(db.listSubChunk(TEST_SUBCHUNK_A_POS)).containsExactly(TestSubChunkComponentA())
        }
    }
}
