package com.github.bedrockecs.vanilla.data.world

import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent

/**
 * quick hack so [VanillaBlockConfigurator] is easier to write
 */
interface VanillaBlockType : BlockTypeComponent {
    /**
     * interface for its companion object
     */
    interface Companion {
        val blockID: BlockID
    }

    val runtimeID: Short
}
