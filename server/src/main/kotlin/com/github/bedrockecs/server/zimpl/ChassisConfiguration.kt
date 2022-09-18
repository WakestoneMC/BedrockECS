package com.github.bedrockecs.server.zimpl

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChassisConfiguration {
    @Bean
    fun globalExceptionHook(): GlobalExceptionHookImpl {
        return GlobalExceptionHookImpl()
    }
}
