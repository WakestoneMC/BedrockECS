package com.github.bedrockecs.server.game.zimpl.db.dimension

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent

data class TestComponentB(val test2: String = "test2") : DimensionComponent {
    companion object : Component.Companion {
        override val TYPE: String = "becs:testB"
    }

    override val type: String
        get() = TYPE

    override fun clone(): Component {
        return copy()
    }
}
