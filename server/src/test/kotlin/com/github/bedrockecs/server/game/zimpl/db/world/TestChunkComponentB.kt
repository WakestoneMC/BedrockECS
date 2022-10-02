package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.world.data.ChunkComponent

data class TestChunkComponentB(val test2: String = "test2") : ChunkComponent {
    companion object : Component.ICompanion {
        override val type: String = "becs:chunk_test_b"
    }

    override val type: String
        get() = Companion.type

    override fun clone(): Component {
        return copy()
    }
}
