package com.github.bedrockecs.game.zimpl.common.testdata

import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import kotlin.String
import kotlin.collections.List

class TestDirtBlockType : BlockTypeComponent {

    override val blockType: String = Companion.blockType

    companion object : BlockTypeComponent.ICompanion {
        override val blockType: String = "minecraft:dirt"

        private val instance0: TestDirtBlockType = TestDirtBlockType()

        override val primary: TestDirtBlockType = instance0

        override val allInstances: List<TestDirtBlockType> = listOf(instance0)
    }
}
