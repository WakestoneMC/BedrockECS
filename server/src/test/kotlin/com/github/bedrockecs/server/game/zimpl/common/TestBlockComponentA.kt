package com.github.bedrockecs.server.game.zimpl.common

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.world.data.BlockComponent

data class TestBlockComponentA(
    val test: String = "test"
) : BlockComponent {
    companion object : Component.Companion {
        override val type: String = "becs:testA"
    }

    override val type: String
        get() = this@Companion.type

    override fun clone(): Component {
        return copy()
    }
}
