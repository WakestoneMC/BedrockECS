package com.github.bedrockecs.game.db.world

import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.game.zimpl.db.world.WorldDBSerialRegisterableImpl

/**
 * [WorldDBSerial], but is actually a registry you can register IDs
 */
interface WorldDBSerialRegisterable : WorldDBSerial {
    companion object {
        /**
         * creates a configured registry
         */
        fun create(configurators: List<WorldDBSerialConfigurator>): WorldDBSerial {
            return WorldDBSerialRegisterableImpl(configurators)
        }
    }

    /**
     * make block of this type air, can only be done once!
     */
    fun registerAirBlock(type: BlockTypeComponent)

    /**
     * registers the id with the type
     */
    fun register(type: BlockTypeComponent, id: Short)
}
