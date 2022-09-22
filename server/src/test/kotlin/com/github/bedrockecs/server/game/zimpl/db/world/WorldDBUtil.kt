package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.zimpl.eventbus.EventBusImpl
import com.github.bedrockecs.server.game.zimpl.registry.BlockRegistryImpl

object WorldDBUtil {
    fun withTestDB(func: (EventBusImpl, WorldDBImpl) -> Unit) {
        val evb = EventBusImpl()
        val reg = BlockRegistryImpl()
        val db = WorldDBImpl(evb, reg)
        func(evb, db)
    }

    fun withTestDB(func: (EventBusImpl, WorldDBImpl, BlockRegistryImpl) -> Unit) {
        val evb = EventBusImpl()
        val reg = BlockRegistryImpl()
        val db = WorldDBImpl(evb, reg)
        func(evb, db, reg)
    }
}
