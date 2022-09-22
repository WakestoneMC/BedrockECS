package com.github.bedrockecs.server.game.zimpl.db.entity

import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.EntityIDAllocator

class FakeIDAllocator : EntityIDAllocator {

    private var id: Int = 1

    override fun allocateID(): List<EntityID> {
        val ret = (id..id + 1024).map { EntityID(it) }
        id += 1025
        return ret
    }

    override fun releaseID(ids: List<EntityID>) {
        // no-op
    }
}
