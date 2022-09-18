package com.github.bedrockecs.server.threading.zimpl

import com.github.bedrockecs.server.threading.BossEventLoopGroup
import com.github.bedrockecs.server.threading.IO
import com.github.bedrockecs.server.threading.Logic
import com.github.bedrockecs.server.threading.WorkerEventLoopGroup
import io.netty.channel.EventLoopGroup
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.EventListener
import org.springframework.http.client.reactive.ReactorResourceFactory
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

/**
 * provides everything specified in [com.github.bedrockecs.server.threading]
 * @implNote we are basically leeching off webflux for IO, roll our own logic
 */
@Configuration
class ThreadingConfiguration {
    @Bean(initMethod = "", destroyMethod = "")
    @BossEventLoopGroup
    fun bossEventLoopGroup(factory: ReactorResourceFactory): EventLoopGroup {
        return factory.loopResources.onServerSelect(true)
    }

    @Bean(initMethod = "", destroyMethod = "")
    @WorkerEventLoopGroup
    fun workerEventLoopGroup(factory: ReactorResourceFactory): EventLoopGroup {
        return factory.loopResources.onServer(true)
    }

    @Bean(initMethod = "", destroyMethod = "")
    @IO
    fun ioExecutor(@WorkerEventLoopGroup group: EventLoopGroup): ScheduledExecutorService {
        return group
    }

    @Bean(initMethod = "", destroyMethod = "")
    @Logic
    fun logicExecutor(): ScheduledExecutorService {
        return Executors.newScheduledThreadPool(4)
    }

    @Bean
    fun logicExecutorShutdown(@Logic logicExecutor: ScheduledExecutorService): Any {
        return object {
            @EventListener
            fun onClose(event: ContextClosedEvent) {
                logicExecutor.shutdown()
            }
        }
    }
}
