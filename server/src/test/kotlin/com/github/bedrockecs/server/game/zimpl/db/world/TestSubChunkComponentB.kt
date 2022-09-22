package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.world.data.SubChunkComponent

data class TestSubChunkComponentB(val test4: String = "test4") : SubChunkComponent {
    companion object : Component.Companion {
        override val TYPE: String = "becs:subchunk_test_b"
    }

    override val type: String
        get() = TYPE

    override fun clone(): Component {
        return copy()
    }
}