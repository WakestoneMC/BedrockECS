package com.github.bedrockecs.game.zimpl.common

import com.github.bedrockecs.game.db.common.Component
import com.github.bedrockecs.game.db.world.data.BlockComponent

data class TestBlockComponentB(
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
