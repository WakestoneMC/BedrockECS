package com.github.bedrockecs.server.game.zimpl

import com.github.bedrockecs.server.GlobalExceptionHook
import com.github.bedrockecs.server.game.GameServer
import com.github.bedrockecs.server.game.ext.GameConfiguration
import com.github.bedrockecs.server.game.zimpl.db.DBGameConfiguration
import com.github.bedrockecs.server.game.zimpl.eventbus.EventBusGameConfiguration
import com.github.bedrockecs.server.game.zimpl.registry.RegistryGameConfiguration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class GameServerImpl(
    dispatcher: CoroutineDispatcher,
    private val exceptionHook: GlobalExceptionHook,
    private val coreContext: ApplicationContext
) : GameServer {
    companion object {
        val INTRINSIC_GAME_CONFIGS: List<Class<*>> = listOf(
            RegistryGameConfiguration::class.java,
            EventBusGameConfiguration::class.java,
            DBGameConfiguration::class.java
        )
    }

    private val scope = CoroutineScope(dispatcher)

    private var job: Job? = null

    private suspend fun main() {
        log.debug("starting game server context")
        val context = createContext()
        context.refresh()
        context.start()
        try {
            log.debug("game server started")
            awaitCancellation()
        } finally {
            log.debug("game server shutting down")
            context.stop()
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
