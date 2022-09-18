package com.github.bedrockecs.server.game.zimpl.db

import com.github.bedrockecs.server.game.db.GameStorageContextInternal
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.ext.GameConfiguration
import com.github.bedrockecs.server.game.registry.BlockRegistry
import org.springframework.context.annotation.Bean

@GameConfiguration
class DBGameConfiguration {
    @Bean
    fun gameDB(
        eventBus: EventBus,
        blockRegistry: BlockRegistry,
        storageContextInternal: GameStorageContextInternal
    ): NaiveGameDB {
        return NaiveGameDB(eventBus, blockRegistry, storageContextInternal)
    }
}
