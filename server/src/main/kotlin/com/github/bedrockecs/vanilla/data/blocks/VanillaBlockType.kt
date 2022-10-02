package com.github.bedrockecs.vanilla.data.blocks

import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.vanilla.data.items.ItemID

/**
 * quick hack so [VanillaBlockConfigurator] is easier to write
 */
interface VanillaBlockType : BlockTypeComponent {
    /**
     * interface for its companion object
     */
    interface Companion : BlockTypeComponent.Companion {
        val itemID: ItemID
    }

    val runtimeID: Short
}
