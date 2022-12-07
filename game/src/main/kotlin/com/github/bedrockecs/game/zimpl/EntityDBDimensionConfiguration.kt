package com.github.bedrockecs.game.zimpl

import com.github.bedrockecs.game.db.entity.EntityDBStorage
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.ext.DimensionConfiguration
import com.github.bedrockecs.game.zimpl.db.entity.EntityDBImpl
import org.springframework.context.annotation.Bean

@DimensionConfiguration
class EntityDBDimensionConfiguration {
    @Bean
    fun entityDB(eventBus: EventBus, storage: EntityDBStorage): EntityDBImpl {
        return EntityDBImpl(eventBus, storage)
    }
}
