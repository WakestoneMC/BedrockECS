package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.zimpl.eventbus.EventBusImpl

object WorldDBUtil {
    fun withTestDB(func: (EventBusImpl, WorldDBImpl) -> Unit) {
        val evb = EventBusImpl()
        val db = WorldDBImpl(evb)
        func(evb, db)
    }
}
