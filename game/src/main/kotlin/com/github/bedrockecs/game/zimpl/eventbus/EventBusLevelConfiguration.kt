package com.github.bedrockecs.game.zimpl.eventbus

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventBusLevelConfiguration {
    @Bean
    fun eventBusImpl(): EventBusImpl {
        return EventBusImpl()
    }
}
