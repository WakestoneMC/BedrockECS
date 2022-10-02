package com.github.bedrockecs.server.game.zimpl.db.dimension

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent

data class TestComponentA(val test: String = "test") : DimensionComponent {
    companion object : Component.Companion {
        override val type: String = "becs:testA"
    }

    override val type: String
        get() = this@Companion.type

    override fun clone(): Component {
        return copy()
    }
}
