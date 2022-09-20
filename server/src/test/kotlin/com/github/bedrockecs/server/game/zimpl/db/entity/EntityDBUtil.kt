package com.github.bedrockecs.server.game.zimpl.db.entity

import com.github.bedrockecs.server.game.zimpl.eventbus.EventBusImpl

object EntityDBUtil {
    fun withTestDB(func: (EventBusImpl, EntityDBImpl) -> Unit) {
        val evb = EventBusImpl()
        val allocator = FakeIDAllocator()
        val db = EntityDBImpl(evb, allocator)
        try {
            func(evb, db)
        } finally {
            db.close()
        }
    }
}
