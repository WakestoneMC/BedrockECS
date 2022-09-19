package com.github.bedrockecs.server.game.zimpl.eventbus

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventBusGameConfiguration {
    @Bean
    fun eventBusImpl(): NaiveEventBusImpl {
        return NaiveEventBusImpl()
    }
}
