package com.github.bedrockecs.vanilla

import com.github.bedrockecs.vanilla.data.VanillaBlockConfigurator
import com.github.bedrockecs.vanilla.data.VanillaBlockSerialConfigurator
import com.github.bedrockecs.vanilla.game.VanillaDimensionConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    value = [
        VanillaBlockConfigurator::class,
        VanillaBlockSerialConfigurator::class
    ]
)
class VanillaCoreConfiguration {
    @Bean
    fun vanillaGameConfiguration(): VanillaDimensionConfiguration {
        return VanillaDimensionConfiguration()
    }
}
