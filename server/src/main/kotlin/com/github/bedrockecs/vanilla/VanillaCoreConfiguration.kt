package com.github.bedrockecs.vanilla

import com.github.bedrockecs.vanilla.comm.VanillaCommConfiguration
import com.github.bedrockecs.vanilla.game.VanillaGameConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(VanillaCommConfiguration::class)
class VanillaCoreConfiguration {
    @Bean
    fun vanillaGameConfiguration(): VanillaGameConfiguration {
        return VanillaGameConfiguration()
    }
}
