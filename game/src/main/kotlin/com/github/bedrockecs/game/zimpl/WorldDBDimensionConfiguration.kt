package com.github.bedrockecs.game.zimpl

import com.github.bedrockecs.game.db.world.BlockRegistry
import com.github.bedrockecs.game.db.world.WorldDBSerial
import com.github.bedrockecs.game.db.world.WorldDBStorage
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.ext.DimensionConfiguration
import com.github.bedrockecs.game.zimpl.db.world.WorldDBImpl
import org.springframework.context.annotation.Bean

@DimensionConfiguration
class WorldDBDimensionConfiguration {
    @Bean
    fun worldDB(
        evb: EventBus,
        registry: BlockRegistry,
        serial: WorldDBSerial,
        storage: WorldDBStorage
    ): WorldDBImpl {
        return WorldDBImpl(evb, registry, serial, storage)
    }
}
