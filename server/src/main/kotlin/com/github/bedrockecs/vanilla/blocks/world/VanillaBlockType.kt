package com.github.bedrockecs.vanilla.blocks.world

import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent

/**
 * quick hack so [VanillaBlockConfigurator] is easier to write
 */
interface VanillaBlockType : BlockTypeComponent {
    val runtimeID: Short
}
