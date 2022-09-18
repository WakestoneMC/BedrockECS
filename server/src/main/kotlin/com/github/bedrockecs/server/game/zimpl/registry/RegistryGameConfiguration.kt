package com.github.bedrockecs.server.game.zimpl.registry

import com.github.bedrockecs.server.game.ext.GameConfiguration
import com.github.bedrockecs.server.game.registry.BlockRegistryConfigurator
import org.springframework.context.annotation.Bean

@GameConfiguration
class RegistryGameConfiguration {
    @Bean
    fun blockRegistry(
        configs: Collection<BlockRegistryConfigurator>
    ): BlockRegistryImpl {
        val impl = BlockRegistryImpl()
        configs.forEach { it.configure(impl) }
        return impl
    }
}
