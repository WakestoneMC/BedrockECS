package com.github.bedrockecs.server.game.zimpl.db.entity

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.entity.data.EntityComponent

data class TestComponentA(
    val test: String = "test"
) : EntityComponent {
    companion object : Component.Companion {
        override val TYPE: String = "becs:testA"
    }

    override val type: String
        get() = TYPE

    override fun clone(): Component {
        return copy()
    }
}
