package com.github.bedrockecs.game.zimpl

import com.github.bedrockecs.game.db.invitem.InvItemDBStorage
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.ext.DimensionConfiguration
import com.github.bedrockecs.game.zimpl.db.invitem.NaiveInvItemDB
import org.springframework.context.annotation.Bean

@DimensionConfiguration
class InvItemDBDimensionConfiguration {
    @Bean
    fun invitemDB(
        evb: EventBus,
        storage: InvItemDBStorage
    ): NaiveInvItemDB {
        return NaiveInvItemDB(evb, storage)
    }
}
