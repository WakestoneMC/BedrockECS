package com.github.bedrockecs.game.zimpl

import com.github.bedrockecs.game.ext.DimensionConfiguration
import com.github.bedrockecs.game.system.CommonTickOrders
import com.github.bedrockecs.game.system.ECSSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class DimensionServerTest {
    @Test
    fun dimensionServerWorks() {
        val context = AnnotationConfigApplicationContext()
        context.register(TestDBDepsConfiguration::class.java)
        context.refresh()

        val server = DimensionServerImpl(context)

        server.start()
        server.tick()
        server.stop()
    }

    class Spy {

        var cnt = 0
        var startedAt = -1
        var tickedAt = -1
        var stoppedAt = -1

        fun onStart() {
            startedAt = cnt++
        }

        fun onTick() {
            tickedAt = cnt++
        }

        fun onStop() {
            stoppedAt = cnt++
        }
    }

    @DimensionConfiguration
    class TestDimensionConfiguration {
        @Bean
        fun lifecycle(spy: Spy): ECSSystem {
            return object : ECSSystem, InitializingBean, DisposableBean {
                override val tickOrder: Int = CommonTickOrders.GAME_LOGIC

                override fun afterPropertiesSet() {
                    spy.onStart()
                }

                override fun destroy() {
                    spy.onStop()
                }

                override fun tick() {
                    spy.onTick()
                }
            }
        }
    }

    @Configuration
    class TestConfiguration {
        @Bean
        fun spy(): Spy {
            return Spy()
        }

        @Bean
        fun testDimensionConfig(): TestDimensionConfiguration {
            return TestDimensionConfiguration()
        }
    }

    @Test
    fun dimensionServerProvidesSpringLifecycle() {
        val context = AnnotationConfigApplicationContext()
        context.register(TestDBDepsConfiguration::class.java)
        context.register(TestConfiguration::class.java)
        context.refresh()

        val server = DimensionServerImpl(context)

        server.start()
        server.stop()

        val spy = context.getBean(Spy::class.java)
        assertThat(spy.startedAt).isEqualTo(0)
        assertThat(spy.stoppedAt).isEqualTo(1)
    }

    @Test
    fun dimensionServerTicks() {
        val context = AnnotationConfigApplicationContext()
        context.register(TestDBDepsConfiguration::class.java)
        context.register(TestConfiguration::class.java)
        context.refresh()

        val server = DimensionServerImpl(context)

        server.start()
        server.tick()
        server.stop()

        val spy = context.getBean(Spy::class.java)
        assertThat(spy.startedAt).isEqualTo(0)
        assertThat(spy.tickedAt).isEqualTo(1)
        assertThat(spy.stoppedAt).isEqualTo(2)
    }
}
