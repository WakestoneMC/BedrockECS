package com.github.bedrockecs.game.zimpl

import com.github.bedrockecs.game.db.entity.EntityDBStorage
import com.github.bedrockecs.game.db.invitem.InvItemDBStorage
import com.github.bedrockecs.game.db.world.BlockRegistry
import com.github.bedrockecs.game.db.world.BlockRegistryConfigurator
import com.github.bedrockecs.game.db.world.WorldDBSerial
import com.github.bedrockecs.game.db.world.WorldDBStorage
import com.github.bedrockecs.game.zimpl.common.testdata.TestAirBlockType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * fake DB dependency configurations
 */
@Configuration
class TestDBDepsConfiguration {
    @Bean
    fun worldDBSerial(): WorldDBSerial {
        return WorldDBSerial.createAutoAssigning(TestAirBlockType.primary)
    }

    @Bean
    fun worldDBStorage(testSerial: WorldDBSerial): WorldDBStorage {
        return WorldDBStorage.inMemoryEmpty(-64 to 320, testSerial)
    }

    @Bean
    fun blockRegistry(configurators: List<BlockRegistryConfigurator>): BlockRegistry {
        val reg = BlockRegistry.create()
        configurators.forEach { it.configure(reg) }
        return reg
    }

    @Bean
    fun entityDBStorage(): EntityDBStorage {
        return EntityDBStorage.inMemory()
    }

    @Bean
    fun invitemDBStorage(): InvItemDBStorage {
        return InvItemDBStorage.inMemory()
    }
}
