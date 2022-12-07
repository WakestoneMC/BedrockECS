package com.github.bedrockecs.game.zimpl

import com.github.bedrockecs.game.ext.DimensionConfiguration
import com.github.bedrockecs.game.zimpl.eventbus.EventBusImpl
import org.springframework.context.annotation.Bean

@DimensionConfiguration
class EventBusDimensionConfiguration {
    @Bean
    fun eventBus(): EventBusImpl {
        return EventBusImpl()
    }
}
