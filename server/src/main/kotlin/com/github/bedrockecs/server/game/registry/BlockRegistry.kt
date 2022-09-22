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
    fun typeFor(rid: Short): BlockTypeComponent

    /**
     * the runtime id of block of this type, used in serialization etc......
     */
    fun runtimeIDFor(type: BlockTypeComponent): Short

    // Default BlockComponents //

    // TODO: remove getter for the internal map so we have more room for optimization

    /**
     * returns the global level default components, provided as default to all blocks
     */
    val defaultComponents: ComponentMap<BlockComponent>

    /**
     * returns the overrides at type level
     */
    fun typeDefaultOverrideFor(type: BlockTypeComponent): ComponentMap<BlockComponent?>

    /**
     * query default block component for type
     */
    fun <T : BlockComponent> defaultComponentOf(type: BlockTypeComponent, clazz: Class<T>): T?

    /**
     * query default block components for type
     */
    fun defaultComponentsOf(type: BlockTypeComponent): ComponentMap<BlockComponent>
}
