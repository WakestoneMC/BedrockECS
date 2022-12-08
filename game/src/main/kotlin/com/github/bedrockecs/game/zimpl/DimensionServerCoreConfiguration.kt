package com.github.bedrockecs.game.zimpl

import com.github.bedrockecs.game.DimensionServer
import com.github.bedrockecs.server.GlobalExceptionHook
import com.github.bedrockecs.threading.Logic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.Long.max
import java.util.concurrent.ScheduledExecutorService

@Configuration
class DimensionServerCoreConfiguration {
    @Bean
    fun dimensionServer(core: ApplicationContext): DimensionServer {
        return DimensionServerImpl(core)
    }

    @Bean
    fun dimensionServerLifecycle(
        @Logic service: ScheduledExecutorService,
        server: DimensionServer,
        hook: GlobalExceptionHook
    ): Any {
        return object : InitializingBean, DisposableBean {

            private val scope: CoroutineScope = CoroutineScope(service.asCoroutineDispatcher())

            override fun afterPropertiesSet() {
                server.start()
                scope.launch {
                    try {
                        while (true) {
                            val took = server.tick()
                            val toSleep = max(0L, 50L - took.toMillis())
                            delay(toSleep)
                        }
                    } catch (t: Throwable) {
                        hook.report(t)
                    }
                }
            }

            override fun destroy() {
                server.stop()
            }
        }
    }
}
