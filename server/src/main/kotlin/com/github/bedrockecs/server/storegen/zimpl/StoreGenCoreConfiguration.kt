package com.github.bedrockecs.server.storegen.zimpl

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StoreGenCoreConfiguration {
    @Bean
    fun storeGenGameConfiguration(): StoreGenGameConfiguration {
        return StoreGenGameConfiguration()
    }
}
