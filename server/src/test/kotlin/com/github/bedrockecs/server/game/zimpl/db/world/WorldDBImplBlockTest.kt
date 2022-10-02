package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.server.game.zimpl.common.TestBlockComponentA
import com.github.bedrockecs.server.game.zimpl.db.world.TestChunks.TEST_CHUNK_A
import com.github.bedrockecs.server.game.zimpl.db.world.TestChunks.TEST_CHUNK_A_POS
import com.github.bedrockecs.server.game.zimpl.db.world.WorldDBUtil.withTestDB
import com.github.bedrockecs.vanilla.data.blocks.DirtBlockType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WorldDBImplBlockTest {

    private val TEST_POSITION = TEST_CHUNK_A_POS.toSubChunk(0).toBlock(0, 0, 0).toLayered(0)

    @Test
    fun placeWorks() {
        withTestDB { evb, db, registry ->
            registry.setRuntimeIDFor(DirtBlockType.primary, DirtBlockType.primary.runtimeID)
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            db.placeBlock(TEST_POSITION, DirtBlockType.primary, emptyMap())

            val ret = db.readBlock(TEST_POSITION, BlockTypeComponent::class.java)
            assertThat(ret).isEqualTo(DirtBlockType.primary)
        }
    }

    @Test
    fun placeUpdatesOverride() {
        withTestDB { evb, db, registry ->
            registry.setRuntimeIDFor(DirtBlockType.primary, DirtBlockType.primary.runtimeID)
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)
            db.placeBlock(
                TEST_POSITION,
                DirtBlockType.primary,
                mapOf(
                    TestBlockComponentA::class.java to TestBlockComponentA()
                )
            )

            db.placeBlock(
                TEST_POSITION,
                DirtBlockType.primary,
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
    // TODO: place rejects not loaded

    @Test
    fun mutateAddsComponents() {
        withTestDB { evb, db, registry ->
            registry.setRuntimeIDFor(DirtBlockType.primary, DirtBlockType.primary.runtimeID)
            db.load(TEST_CHUNK_A_POS, TEST_CHUNK_A)

            db.placeBlock(TEST_POSITION, DirtBlockType.primary, emptyMap())

            val ret = db.readBlock(TEST_POSITION, BlockTypeComponent::class.java)
            assertThat(ret).isEqualTo(DirtBlockType.primary)
        }
    }

    // TODO: mutate works
    // TODO: mustate emits events
    // TODO: mutate block type cannot change block name
    // TODO: place rejects not loaded

    // TODO: read works
    // TODO: read ret null
    // TODO: read rejects not loaded

    // TODO: list works
    // TODO: list rejects not loaded
}
