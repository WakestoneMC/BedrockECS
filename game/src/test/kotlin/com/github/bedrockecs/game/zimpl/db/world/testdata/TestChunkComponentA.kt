package com.github.bedrockecs.game.zimpl.db.world.testdata

import com.github.bedrockecs.game.db.common.Component
import com.github.bedrockecs.game.db.world.data.ChunkComponent

data class TestChunkComponentA(val test: String = "test") : ChunkComponent {
    companion object : Component.ICompanion {
        override val type: String = "becs:chunk_test_a"
    }

    override val type: String
        get() = Companion.type

    override fun clone(): Component {
        return copy()
    }
}
