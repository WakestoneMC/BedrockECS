package com.github.bedrockecs.game.zimpl.common.testdata

import com.github.bedrockecs.game.db.common.Component
import com.github.bedrockecs.game.db.world.data.BlockComponent

data class TestBlockComponentA(
    val test: String = "test"
) : BlockComponent {
    companion object : Component.ICompanion {
        override val type: String = "becs:testA"
    }

    override val type: String
        get() = Companion.type

    override fun clone(): Component {
        return copy()
    }
}
