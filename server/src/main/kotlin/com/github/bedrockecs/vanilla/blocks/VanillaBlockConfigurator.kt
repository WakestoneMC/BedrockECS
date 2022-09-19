package com.github.bedrockecs.vanilla.blocks

import com.github.bedrockecs.server.game.registry.BlockRegistryConfigurator
import com.github.bedrockecs.server.game.registry.MutableBlockRegistry
import com.github.bedrockecs.vanilla.blocks.world.VanillaBlockTypes
import org.springframework.stereotype.Component

@Component
class VanillaBlockConfigurator : BlockRegistryConfigurator {
    override fun configure(obj: MutableBlockRegistry) {
        for (type in VanillaBlockTypes.TYPES) {
            obj.setRuntimeIDFor(type, type.runtimeID)
        }
    }
}
