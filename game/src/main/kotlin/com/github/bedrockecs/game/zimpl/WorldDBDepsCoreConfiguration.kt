package com.github.bedrockecs.game.zimpl

import com.github.bedrockecs.game.db.world.BlockRegistry
import com.github.bedrockecs.game.db.world.BlockRegistryConfigurator
import com.github.bedrockecs.game.db.world.WorldDBSerial
import com.github.bedrockecs.game.db.world.WorldDBSerialConfigurator
import com.github.bedrockecs.game.db.world.WorldDBSerialRegisterable
import com.github.bedrockecs.game.ext.DimensionConfiguration
import org.springframework.context.annotation.Bean

@DimensionConfiguration
class WorldDBDepsCoreConfiguration {
    @Bean
    fun worldDBSerial(configurators: List<WorldDBSerialConfigurator>): WorldDBSerial {
        return WorldDBSerialRegisterable.create(configurators)
    }

    @Bean
    fun blockRegistry(configurators: List<BlockRegistryConfigurator>): BlockRegistry {
        val reg = BlockRegistry.create()
        configurators.forEach { it.configure(reg) }
        return reg
    }
}
