package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.db.world.BlockRegistry
import com.github.bedrockecs.game.zimpl.common.TestAirBlockType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlockRegistryImplBlockTypesTest {
    @Test
    fun registerBlockTypeWorks() {
        val reg = BlockRegistry.create()

        reg.registerBlockType(TestAirBlockType.primary)

        assertThat(reg.blockTypes).containsExactly(TestAirBlockType.primary)
    }
}
