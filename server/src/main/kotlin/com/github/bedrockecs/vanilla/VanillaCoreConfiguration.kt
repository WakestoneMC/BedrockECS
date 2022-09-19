package com.github.bedrockecs.vanilla

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VanillaCoreConfiguration {
    @Bean
    fun gameConfiguration(): VanillaGameConfiguration {
        return VanillaGameConfiguration()
    }
}
