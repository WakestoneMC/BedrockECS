package com.github.bedrockecs.server.game.db

import com.github.bedrockecs.server.game.db.dimension.DimensionChangeListener
import com.github.bedrockecs.server.game.db.entity.EntityIDAllocator

/**
 * provider interface, implement this interface and provide it in the game DI container to get [GameDB] running
 */
interface GameStorageProvider {
    val context: GameStorageContext

    val changeListener: DimensionChangeListener

    val allocator: EntityIDAllocator

    val initializer: GameDBInitializer
}
