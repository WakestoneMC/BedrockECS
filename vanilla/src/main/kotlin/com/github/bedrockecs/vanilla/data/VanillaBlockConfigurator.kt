package com.github.bedrockecs.vanilla.data

import com.github.bedrockecs.game.db.world.BlockRegistryConfigurator
import com.github.bedrockecs.game.db.world.BlockRegistry
import com.github.bedrockecs.vanilla.data.blocks.VanillaBlockTypes
import org.springframework.stereotype.Component

@Component
class VanillaBlockConfigurator : BlockRegistryConfigurator {
    override fun configure(obj: BlockRegistry) {
        for (type in VanillaBlockTypes.TYPES) {
            obj.registerBlockType(type)
        }
    }
}
