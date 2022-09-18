package com.github.bedrockecs.server.game.registry

import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent

/**
 * core block registry of [GameServer]
 *
 * @apiNote only things that ABSOLUTELY has to be known by everyone goes here.
 *
 * @apiNote other modules may have other registry that you need to register, but they should provide sensible default,
 * while this is mandatory
 */
interface BlockRegistry {

    // runtime ID //

    /**
     * get [BlockTypeComponent] associated with this runtimeId
     */
    fun typeFor(rid: Int): BlockTypeComponent?

    /**
     * the runtime id of block of this type, used in serialization etc......
     */
    fun runtimeIDFor(type: BlockTypeComponent): Int

    // Default BlockComponents //

    /**
     * returns the global level default components, provided as default to all blocks
     */
    val globalDefaultComponents: ComponentMap<BlockComponent>

    /**
     * returns the overrides at type level
     */
    fun typeDefaultOverrideFor(
        type: BlockTypeComponent
    ): ComponentMap<BlockComponent?>

    /**
     * query default block component for type
     */
    fun defaultComponentsFor(type: BlockTypeComponent): ComponentMap<BlockComponent>
}
