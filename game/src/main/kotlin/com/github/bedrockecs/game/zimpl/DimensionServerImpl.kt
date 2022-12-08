package com.github.bedrockecs.game.zimpl

import com.github.bedrockecs.game.DimensionServer
import com.github.bedrockecs.game.chunkloading.zimpl.ChunkLoadingGameConfiguration
import com.github.bedrockecs.game.ext.DimensionConfiguration
import com.github.bedrockecs.game.system.ECSSystem
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.time.Duration
import java.time.Instant

class DimensionServerImpl(
    private val coreContext: ApplicationContext
) : DimensionServer {

    enum class State {
        INIT,
        RUNNING,
        STOPPED
    }

    companion object {
        val INTRINSIC_GAME_CONFIGS: List<Class<*>> = listOf(
            EventBusDimensionConfiguration::class.java,
            WorldDBDimensionServerConfiguration::class.java,
            EntityDBDimensionConfiguration::class.java,
            InvItemDBDimensionConfiguration::class.java,
            ChunkLoadingGameConfiguration::class.java
        )
    }

    private var state: State = State.INIT

    private lateinit var context: AnnotationConfigApplicationContext

    private fun createContext(): AnnotationConfigApplicationContext {
        val context = AnnotationConfigApplicationContext()
        context.parent = coreContext
        INTRINSIC_GAME_CONFIGS.forEach { context.register(it) }
        coreContext.getBeansWithAnnotation(DimensionConfiguration::class.java)
            .forEach { context.register(it.value::class.java) }
        return context
    }

    override fun start() {
        assert(state == State.INIT)
        log.debug("starting game server context")
        context = createContext()
        context.refresh()
        context.start()
        log.debug("game server started")
        state = State.RUNNING
    }
    
    override fun tick(): Duration {
        assert(state == State.RUNNING)
        val systemBeans = context.getBeansOfType(ECSSystem::class.java)
        val orderedSystems = systemBeans.values.sortedBy { it.tickOrder }
        val tickStart: Instant = Instant.now()
        orderedSystems.forEach { it.tick() }
        val tickEnd: Instant = Instant.now()
        return Duration.between(tickStart, tickEnd)
    }

    override fun stop() {
        assert(state == State.RUNNING || state == State.STOPPED)
        if (state == State.RUNNING) {
            log.debug("game server shutting down")
            context.close()
            log.debug("game server stopped")
            state = State.STOPPED
        }
    }
}
