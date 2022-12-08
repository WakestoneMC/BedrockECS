package com.github.bedrockecs.game.zimpl.db.entity.testdata

import com.github.bedrockecs.game.db.common.Component
import com.github.bedrockecs.game.db.entity.data.EntityComponent

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
