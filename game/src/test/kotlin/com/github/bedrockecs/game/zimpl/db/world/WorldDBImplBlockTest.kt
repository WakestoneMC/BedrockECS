package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.game.zimpl.common.testdata.TestBlockComponentA
import com.github.bedrockecs.game.zimpl.common.testdata.TestDirtBlockType
import com.github.bedrockecs.game.zimpl.db.world.testdata.TestChunks.TEST_CHUNK_A_POS
import com.github.bedrockecs.game.zimpl.db.world.testdata.WorldDBUtil.withTestDB
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WorldDBImplBlockTest {

    private val TEST_POSITION = TEST_CHUNK_A_POS.toSubChunk(0).toBlock(0, 0, 0).toLayered(0)

    @Test
    fun placeWorks() {
        withTestDB { evb, db, registry ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.placeBlock(TEST_POSITION, TestDirtBlockType.primary, emptyMap())

            val ret = db.readBlock(TEST_POSITION, BlockTypeComponent::class.java)
            assertThat(ret).isEqualTo(TestDirtBlockType.primary)
        }
    }

    @Test
    fun placeUpdatesOverride() {
        withTestDB { evb, db, registry ->
            db.loadChunk(TEST_CHUNK_A_POS).join()
            db.placeBlock(
                TEST_POSITION,
                TestDirtBlockType.primary,
                mapOf(
                    TestBlockComponentA::class.java to TestBlockComponentA()
                )
            )

            db.placeBlock(
                TEST_POSITION,
                TestDirtBlockType.primary,
                mapOf(
                    TestBlockComponentA::class.java to TestBlockComponentA("updated")
                )
            )

            val ret = db.readBlock(TEST_POSITION, TestBlockComponentA::class.java)
            assertThat(ret).isEqualTo(TestBlockComponentA("updated"))
        }
    }

    // TODO: place emits remove events for all components, and add events for all new components
    // TODO: place rejects type in extras
    // TODO: place operates on not loaded chunk

    @Test
    fun mutateAddsComponents() {
        withTestDB { evb, db, registry ->
            db.loadChunk(TEST_CHUNK_A_POS).join()

            db.placeBlock(TEST_POSITION, TestDirtBlockType.primary, emptyMap())

            val ret = db.readBlock(TEST_POSITION, BlockTypeComponent::class.java)
            assertThat(ret).isEqualTo(TestDirtBlockType.primary)
        }
    }

    // TODO: mutate works
    // TODO: mustate emits events
    // TODO: mutate block type cannot change block name
    // TODO: place operates on not loaded chunk

    // TODO: read works
    // TODO: read ret null
    // TODO: read rejects not loaded
    // TODO: place operates on not loaded chunk

    // TODO: list works
    // TODO: place operates on not loaded chunk
}
