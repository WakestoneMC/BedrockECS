package com.github.bedrockecs.game.zimpl.db.world.testdata

import com.github.bedrockecs.game.db.common.Component
import com.github.bedrockecs.game.db.world.data.SubChunkComponent

data class TestSubChunkComponentA(val test3: String = "test3") : SubChunkComponent {
    companion object : Component.ICompanion {
        override val type: String = "becs:subchunk_test_a"
    }

    override val type: String
        get() = Companion.type

    override fun clone(): Component {
        return copy()
    }
}
