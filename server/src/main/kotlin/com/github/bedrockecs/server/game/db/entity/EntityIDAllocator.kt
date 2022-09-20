package com.github.bedrockecs.server.game.db.entity

/**
 * used to allocate persistent [EntityID] from storage, KERNEL USE ONLY!
 */
interface EntityIDAllocator {
    /**
     * allocate a list of ids from storage
     */
    fun allocateID(): List<EntityID>

    /**
     * release a list of ids to storage
     */
    fun releaseID(ids: List<EntityID>)
}
