package com.github.bedrockecs.server.storegen.zimpl

import com.github.bedrockecs.server.game.db.GameStorageContextInternal
import com.github.bedrockecs.server.game.ext.GameConfiguration
import org.springframework.context.annotation.Bean

@GameConfiguration
class StoreGenGameConfiguration {
    @Bean
    fun gameStorageContext(): GameStorageContextInternal {
        return MockGameStorageContext()
    }
}
