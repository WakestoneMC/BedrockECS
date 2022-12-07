package com.github.bedrockecs.comm.zimpl

import com.github.bedrockecs.GlobalExceptionHook
import com.github.bedrockecs.comm.config.NetworkConfig
import com.github.bedrockecs.comm.server.policy.NewConnectionPolicy
import com.github.bedrockecs.comm.zimpl.exchange.ActionUpdateExchange
import com.github.bedrockecs.comm.zimpl.handler.PingHandler
import com.github.bedrockecs.comm.zimpl.serial.DispatchingItemNetworkSerializer
import com.github.bedrockecs.comm.zimpl.server.NetworkServerImpl
import com.nukkitx.protocol.bedrock.BedrockPacketCodec
import com.nukkitx.protocol.bedrock.v486.Bedrock_v486
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextStoppedEvent
import org.springframework.context.event.EventListener
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order

@Configuration
@ComponentScan(
    basePackageClasses = [
        NetworkServerImpl::class,
        PingHandler::class,
        ActionUpdateExchange::class,
        DispatchingItemNetworkSerializer::class
    ]
)
class CommCoreConfiguration {
    @Bean
    fun codec(): BedrockPacketCodec {
        return Bedrock_v486.V486_CODEC
    }

    @Bean
    fun mockConfig(): NetworkConfig {
        return NetworkConfig()
    }

    @Bean
    fun dimensionConfig(): CommDimensionConfiguration {
        return CommDimensionConfiguration()
    }

    @Bean
    fun commLifecycle(server: NetworkServerImpl, hook: GlobalExceptionHook): Any {
        return object {
            @EventListener
            fun onStartup(e: ApplicationStartedEvent) {
                server.start()
            }

            /*
             * normal ContextReadyEvent cannot be used because spring-shell prevents it from happening
             */
            @EventListener
            @Order(Ordered.LOWEST_PRECEDENCE)
            fun onStartupComplete(e: ApplicationStartedEvent) {
                server.applyConnectionPolicy(NewConnectionPolicy.Accept())
            }

            @EventListener
            fun onShutdown(e: ContextStoppedEvent) {
                server.close()
            }
        }
    }
}
