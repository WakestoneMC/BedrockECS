package com.github.bedrockecs.server.game.zimpl

import com.github.bedrockecs.server.GlobalExceptionHook
import com.github.bedrockecs.server.threading.Logic
import kotlinx.coroutines.asCoroutineDispatcher
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextStoppedEvent
import org.springframework.context.event.EventListener
import java.util.concurrent.ScheduledExecutorService

@Configuration
class GameCoreConfiguration {
    @Bean
    fun gameServer(
        @Logic scheduler: ScheduledExecutorService,
        hook: GlobalExceptionHook,
        context: ApplicationContext
    ): GameServerImpl {
        return GameServerImpl(scheduler.asCoroutineDispatcher(), hook, context)
    }

    @Bean
    fun gameLifecycle(server: GameServerImpl): Any {
        return object {
            @EventListener
            fun onStartup(e: ApplicationStartedEvent) {
                server.start()
            }

            @EventListener
            fun onShutdown(e: ContextStoppedEvent) {
                server.stop()
            }
        }
    }
}
