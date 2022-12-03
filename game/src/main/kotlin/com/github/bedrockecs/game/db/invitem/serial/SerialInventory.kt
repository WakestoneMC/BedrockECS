package com.github.bedrockecs.game.db.invitem.serial

import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.invitem.data.InventoryMetadata
import com.github.bedrockecs.game.db.invitem.data.ItemComponent

/**
 * serialized form of inventory
 */
data class SerialInventory(
    val metadata: InventoryMetadata,
    /**
     * non-default components
     */
    val overrides: Map<Int, ComponentMap<ItemComponent?>>
)
