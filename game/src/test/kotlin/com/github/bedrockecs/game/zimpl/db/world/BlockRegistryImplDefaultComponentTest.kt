package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.db.world.BlockRegistry
import com.github.bedrockecs.game.zimpl.common.testdata.TestBlockComponentA
import com.github.bedrockecs.game.zimpl.common.testdata.TestBlockComponentB
import com.github.bedrockecs.game.zimpl.common.testdata.TestDirtBlockType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlockRegistryImplDefaultComponentTest {
    @Test
    fun addDefaultComponentWorks() {
        val reg = BlockRegistry.create()

        reg.addDefaultComponent(TestBlockComponentA())

        val ret = reg.defaultComponentOf(TestDirtBlockType.primary, TestBlockComponentA::class.java)
        assertThat(ret).isEqualTo(TestBlockComponentA())
    }

    @Test
    fun removeDefaultComponentWorks() {
        val reg = BlockRegistry.create()
        reg.addDefaultComponent(TestBlockComponentA())

        reg.removeDefaultComponent(TestBlockComponentA::class.java)

        val ret = reg.defaultComponentOf(TestDirtBlockType.primary, TestBlockComponentA::class.java)
        assertThat(ret).isNull()
    }

    @Test
    fun addTypeOverrideWorks() {
        val reg = BlockRegistry.create()
        reg.addDefaultComponent(TestBlockComponentA())

        reg.addTypeDefaultOverride(TestDirtBlockType.primary, TestBlockComponentA::class.java, null)

        val ret = reg.defaultComponentOf(TestDirtBlockType.primary, TestBlockComponentA::class.java)
        assertThat(ret).isNull()
    }

    @Test
    fun removeTypeOverrideWorks() {
        val reg = BlockRegistry.create()
        reg.addDefaultComponent(TestBlockComponentA())
        reg.addTypeDefaultOverride(TestDirtBlockType.primary, TestBlockComponentA::class.java, null)

        reg.removeTypeDefaultOverride(TestDirtBlockType.primary, TestBlockComponentA::class.java)

        val ret = reg.defaultComponentOf(TestDirtBlockType.primary, TestBlockComponentA::class.java)
        assertThat(ret).isEqualTo(TestBlockComponentA())
    }

    @Test
    fun defaultComponentsWorks() {
        val reg = BlockRegistry.create()
        reg.addDefaultComponent(TestBlockComponentA())
        reg.addTypeDefaultOverride(TestDirtBlockType.primary, TestBlockComponentA::class.java, null)

        val ret = reg.defaultComponents

        assertThat(ret).isEqualTo(
            mapOf(
                TestBlockComponentA::class.java to TestBlockComponentA()
            )
        )
    }

    @Test
    fun typeDefaultOverrideForWorks() {
        val reg = BlockRegistry.create()
        reg.addDefaultComponent(TestBlockComponentA())
        reg.addTypeDefaultOverride(TestDirtBlockType.primary, TestBlockComponentA::class.java, null)

        val ret = reg.typeDefaultOverrideFor(TestDirtBlockType.primary)

        assertThat(ret).isEqualTo(
            mapOf(
                TestBlockComponentA::class.java to null
            )
        )
    }

    @Test
    fun defaultComponentOfWorks() {
        val reg = BlockRegistry.create()
        reg.addDefaultComponent(TestBlockComponentA())
        reg.addTypeDefaultOverride(TestDirtBlockType.primary, TestBlockComponentA::class.java, null)

        val ret = reg.defaultComponentOf(TestDirtBlockType.primary, TestBlockComponentA::class.java)

        assertThat(ret).isNull()
    }

    @Test
    fun defaultComponentsOfWorks() {
        val reg = BlockRegistry.create()
        reg.addDefaultComponent(TestBlockComponentA())
        reg.addDefaultComponent(TestBlockComponentB())
        reg.addTypeDefaultOverride(TestDirtBlockType.primary, TestBlockComponentA::class.java, null)

        val ret = reg.defaultComponentsOf(TestDirtBlockType.primary)

        assertThat(ret).isEqualTo(
            mapOf(
                TestBlockComponentB::class.java to TestBlockComponentB()
            )
        )
    }
}
