package com.github.bedrockecs.server.game.zimpl.registry

import com.github.bedrockecs.server.game.zimpl.common.TestBlockComponentA
import com.github.bedrockecs.server.game.zimpl.common.TestBlockComponentB
import com.github.bedrockecs.vanilla.data.world.DirtBlockType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class BlockRegistryImplTest {
    @Test
    fun setRuntimeIDForWorks() {
        val reg = BlockRegistryImpl()

        reg.setRuntimeIDFor(DirtBlockType.primary, DirtBlockType.primary.runtimeID)

        assertThat(reg.runtimeIDFor(DirtBlockType.primary)).isEqualTo(DirtBlockType.primary.runtimeID)
    }

    @Test
    fun setRuntimeIDForRejectsDuplicates() {
        val reg = BlockRegistryImpl()

        reg.setRuntimeIDFor(DirtBlockType.primary, DirtBlockType.primary.runtimeID)

        assertThrows<IllegalArgumentException> {
            reg.setRuntimeIDFor(DirtBlockType.primary, DirtBlockType.primary.runtimeID)
        }
    }

    @Test
    fun runtimeIDForWorks() {
        val reg = BlockRegistryImpl()
        reg.setRuntimeIDFor(DirtBlockType.primary, DirtBlockType.primary.runtimeID)

        val ret = reg.runtimeIDFor(DirtBlockType.primary)

        assertThat(ret).isEqualTo(DirtBlockType.primary.runtimeID)
    }

    @Test
    fun runtimeIDForRejectsUnknownType() {
        val reg = BlockRegistryImpl()

        assertThrows<IllegalArgumentException> {
            reg.runtimeIDFor(DirtBlockType.primary)
        }
    }

    @Test
    fun typeForWorks() {
        val reg = BlockRegistryImpl()
        reg.setRuntimeIDFor(DirtBlockType.primary, DirtBlockType.primary.runtimeID)

        val ret = reg.typeFor(DirtBlockType.primary.runtimeID)

        assertThat(ret).isEqualTo(DirtBlockType.primary)
    }

    @Test
    fun typeForRejectsUnknownType() {
        val reg = BlockRegistryImpl()

        assertThrows<IllegalArgumentException> {
            reg.typeFor(DirtBlockType.primary.runtimeID)
        }
    }

    @Test
    fun addDefaultComponentWorks() {
        val reg = BlockRegistryImpl()

        reg.addDefaultComponent(TestBlockComponentA())

        val ret = reg.defaultComponentOf(DirtBlockType.primary, TestBlockComponentA::class.java)
        assertThat(ret).isEqualTo(TestBlockComponentA())
    }

    @Test
    fun removeDefaultComponentWorks() {
        val reg = BlockRegistryImpl()
        reg.addDefaultComponent(TestBlockComponentA())

        reg.removeDefaultComponent(TestBlockComponentA::class.java)

        val ret = reg.defaultComponentOf(DirtBlockType.primary, TestBlockComponentA::class.java)
        assertThat(ret).isNull()
    }

    @Test
    fun addTypeOverrideWorks() {
        val reg = BlockRegistryImpl()
        reg.addDefaultComponent(TestBlockComponentA())

        reg.addTypeDefaultOverride(DirtBlockType.primary, TestBlockComponentA::class.java, null)

        val ret = reg.defaultComponentOf(DirtBlockType.primary, TestBlockComponentA::class.java)
        assertThat(ret).isNull()
    }

    @Test
    fun removeTypeOverrideWorks() {
        val reg = BlockRegistryImpl()
        reg.addDefaultComponent(TestBlockComponentA())
        reg.addTypeDefaultOverride(DirtBlockType.primary, TestBlockComponentA::class.java, null)

        reg.removeTypeDefaultOverride(DirtBlockType.primary, TestBlockComponentA::class.java)

        val ret = reg.defaultComponentOf(DirtBlockType.primary, TestBlockComponentA::class.java)
        assertThat(ret).isEqualTo(TestBlockComponentA())
    }

    @Test
    fun defaultComponentsWorks() {
        val reg = BlockRegistryImpl()
        reg.addDefaultComponent(TestBlockComponentA())
        reg.addTypeDefaultOverride(DirtBlockType.primary, TestBlockComponentA::class.java, null)

        val ret = reg.defaultComponents

        assertThat(ret).isEqualTo(
            mapOf(
                TestBlockComponentA::class.java to TestBlockComponentA()
            )
        )
    }

    @Test
    fun typeDefaultOverrideForWorks() {
        val reg = BlockRegistryImpl()
        reg.addDefaultComponent(TestBlockComponentA())
        reg.addTypeDefaultOverride(DirtBlockType.primary, TestBlockComponentA::class.java, null)

        val ret = reg.typeDefaultOverrideFor(DirtBlockType.primary)

        assertThat(ret).isEqualTo(
            mapOf(
                TestBlockComponentA::class.java to null
            )
        )
    }

    @Test
    fun defaultComponentOfWorks() {
        val reg = BlockRegistryImpl()
        reg.addDefaultComponent(TestBlockComponentA())
        reg.addTypeDefaultOverride(DirtBlockType.primary, TestBlockComponentA::class.java, null)

        val ret = reg.defaultComponentOf(DirtBlockType.primary, TestBlockComponentA::class.java)

        assertThat(ret).isNull()
    }

    @Test
    fun defaultComponentsOfWorks() {
        val reg = BlockRegistryImpl()
        reg.addDefaultComponent(TestBlockComponentA())
        reg.addDefaultComponent(TestBlockComponentB())
        reg.addTypeDefaultOverride(DirtBlockType.primary, TestBlockComponentA::class.java, null)

        val ret = reg.defaultComponentsOf(DirtBlockType.primary)

        assertThat(ret).isEqualTo(
            mapOf(
                TestBlockComponentB::class.java to TestBlockComponentB()
            )
        )
    }
}
