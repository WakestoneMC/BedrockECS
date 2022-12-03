package com.github.bedrockecs.game.db.invitem.data

import com.github.bedrockecs.game.db.entity.EntityID

/**
 * metadata of an inventory
 */
data class InventoryMetadata(
    val owner: EntityID,
    val name: String,
    val size: Int
)
