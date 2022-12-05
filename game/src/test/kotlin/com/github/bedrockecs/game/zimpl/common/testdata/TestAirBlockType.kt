package com.github.bedrockecs.game.zimpl.common.testdata

import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import kotlin.String
import kotlin.collections.List

class TestAirBlockType : BlockTypeComponent {

    override val blockType: String = Companion.blockType

    companion object : BlockTypeComponent.ICompanion {
        override val blockType: String = "minecraft:air"

        private val instance0: TestAirBlockType = TestAirBlockType()

        override val primary: TestAirBlockType = instance0

        override val allInstances: List<TestAirBlockType> = listOf(instance0)
    }
}
