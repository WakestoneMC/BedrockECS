package com.github.bedrockecs.server.game.db.invitem.data

import com.github.bedrockecs.server.game.db.entity.EntityID

/**
 * metadata of an inventory
 */
data class InventoryMetadata(
    val owner: EntityID,
    val name: String,
    val size: Int
)
