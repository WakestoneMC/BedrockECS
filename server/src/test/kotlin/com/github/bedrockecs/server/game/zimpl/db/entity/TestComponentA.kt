package com.github.bedrockecs.server.game.zimpl.db.entity

import com.github.bedrockecs.server.game.db.common.Component
import com.github.bedrockecs.server.game.db.entity.data.EntityComponent

data class TestComponentA(
    val test: String = "test"
) : EntityComponent {
    companion object : Component.ICompanion {
        override val type: String = "becs:testA"
    }

    override val type: String
        get() = Companion.type

    override fun clone(): Component {
        return copy()
    }
}
