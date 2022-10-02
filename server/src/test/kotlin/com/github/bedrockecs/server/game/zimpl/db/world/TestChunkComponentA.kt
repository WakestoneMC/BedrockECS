package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.world.data.ChunkComponent

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
