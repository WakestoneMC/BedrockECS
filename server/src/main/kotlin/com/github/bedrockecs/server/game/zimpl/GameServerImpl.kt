package com.github.bedrockecs.server.game.zimpl

import com.github.bedrockecs.server.GlobalExceptionHook
import com.github.bedrockecs.server.game.GameServer
import com.github.bedrockecs.server.game.chunkloading.zimpl.ChunkLoadingGameConfiguration
import com.github.bedrockecs.server.game.ext.GameConfiguration
import com.github.bedrockecs.server.game.system.System
import com.github.bedrockecs.server.game.zimpl.db.DBGameConfiguration
import com.github.bedrockecs.server.game.zimpl.eventbus.EventBusGameConfiguration
import com.github.bedrockecs.server.game.zimpl.registry.RegistryGameConfiguration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.time.Duration
import java.time.Instant
import kotlin.math.max

class GameServerImpl(
    dispatcher: CoroutineDispatcher,
    private val exceptionHook: GlobalExceptionHook,
    private val coreContext: ApplicationContext
) : GameServer {
    companion object {
        val INTRINSIC_GAME_CONFIGS: List<Class<*>> = listOf(
            RegistryGameConfiguration::class.java,
            EventBusGameConfiguration::class.java,
            DBGameConfiguration::class.java,
            ChunkLoadingGameConfiguration::class.java
        )
    }

    private val scope = CoroutineScope(dispatcher)

    private var job: Job? = null

    private suspend fun main() {
        log.debug("starting game server context")
        val context = createContext()
        context.refresh()
        context.start()
        val systemBeans = context.getBeansOfType(System::class.java)
        val orderedSystems = systemBeans.values.sortedBy { it.tickOrder }
        try {
            log.debug("game server started")
            var tickStart: Instant
            var tickEnd: Instant
            while (true) {
                tickStart = Instant.now()
                orderedSystems.forEach { it.tick() }
                tickEnd = Instant.now()
                val tickDurationMillis = Duration.between(tickStart, tickEnd).toMillis()
                val toDelay = max(0, 50 - tickDurationMillis) // clip to 0
                delay(toDelay)
            }
        } finally {
            log.debug("game server shutting down")
            context.close()
            log.debug("game server stopped")
        }
    }

    private fun createContext(): AnnotationConfigApplicationContext {
        val context = AnnotationConfigApplicationContext()
        context.parent = coreContext
        INTRINSIC_GAME_CONFIGS.forEach { context.register(it) }
        coreContext.getBeansWithAnnotation(GameConfiguration::class.java)
            .forEach { context.register(it.value::class.java) }
        return context
    }

    fun start() {
        val job = scope.launch { main() }
        job.invokeOnCompletion {
            if (it != null) {
                exceptionHook.report(it)
            }
        }
        this.job = job
    }

    fun stop() {
        runBlocking {
            job?.cancelAndJoin()
        }
    }
}
