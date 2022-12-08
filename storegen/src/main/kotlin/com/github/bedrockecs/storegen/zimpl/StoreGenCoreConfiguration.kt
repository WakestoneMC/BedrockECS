package com.github.bedrockecs.storegen.zimpl

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StoreGenCoreConfiguration {
    @Bean
    fun storeGenDimensionConfiguration(): MockStoreGenDimensionConfiguration {
        return MockStoreGenDimensionConfiguration()
    }
}
