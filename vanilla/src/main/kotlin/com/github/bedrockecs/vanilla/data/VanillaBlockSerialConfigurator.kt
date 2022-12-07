package com.github.bedrockecs.vanilla.data

import com.github.bedrockecs.game.db.world.WorldDBSerialConfigurator
import com.github.bedrockecs.game.db.world.WorldDBSerialRegisterable
import com.github.bedrockecs.vanilla.data.blocks.AirBlockType
import com.github.bedrockecs.vanilla.data.blocks.VanillaBlockTypes
import org.springframework.stereotype.Component

@Component
class VanillaBlockSerialConfigurator : WorldDBSerialConfigurator {
    override fun configure(obj: WorldDBSerialRegisterable) {
        obj.registerAirBlock(AirBlockType.primary)
        for (type in VanillaBlockTypes.TYPES) {
            obj.register(type, type.runtimeID)
        }
    }
}
