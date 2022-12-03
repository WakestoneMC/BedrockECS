package com.github.bedrockecs.game.db.world

import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.world.data.BlockComponent
import com.github.bedrockecs.game.db.world.data.BlockTypeComponent

/**
 * registry for [WorldDB]
 */
interface BlockRegistry {
    // region types

    val blockTypes: Collection<BlockTypeComponent>

    fun registerBlockType(type: BlockTypeComponent)

    // endregion

    // region default components

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

    /**
     * add a default component at global level
     */
    fun addDefaultComponent(component: BlockComponent)

    /**
     * remove a default component at global level
     */
    fun removeDefaultComponent(component: Class<out BlockComponent>)

    /**
     * add an override at type level, null for removing that component
     */
    fun addTypeDefaultOverride(type: BlockTypeComponent, clazz: Class<out BlockComponent>, component: BlockComponent?)

    /**
     * remove a default component at type level
     */
    fun removeTypeDefaultOverride(type: BlockTypeComponent, clazz: Class<out BlockComponent>)

    // endregion
}
