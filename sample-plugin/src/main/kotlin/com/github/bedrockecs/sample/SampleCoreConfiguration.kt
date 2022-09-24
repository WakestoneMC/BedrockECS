package com.github.bedrockecs.sample

import com.github.bedrockecs.sample.game.SampleGameConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SampleCoreConfiguration {
    @Bean
    fun sampleGameConfig(): SampleGameConfiguration {
        return SampleGameConfiguration()
    }
}
