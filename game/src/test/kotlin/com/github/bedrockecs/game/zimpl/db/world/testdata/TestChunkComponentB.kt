package com.github.bedrockecs.game.zimpl.db.world.testdata

import com.github.bedrockecs.game.db.common.Component
import com.github.bedrockecs.game.db.world.data.ChunkComponent

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
