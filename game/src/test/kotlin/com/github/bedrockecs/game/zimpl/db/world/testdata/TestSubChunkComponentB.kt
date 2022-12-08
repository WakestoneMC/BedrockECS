package com.github.bedrockecs.game.zimpl.db.world.testdata

import com.github.bedrockecs.game.db.common.Component
import com.github.bedrockecs.game.db.world.data.SubChunkComponent

data class TestSubChunkComponentB(val test4: String = "test4") : SubChunkComponent {
    companion object : Component.ICompanion {
        override val type: String = "becs:subchunk_test_b"
    }

    override val type: String
        get() = Companion.type

    override fun clone(): Component {
        return copy()
    }
}
