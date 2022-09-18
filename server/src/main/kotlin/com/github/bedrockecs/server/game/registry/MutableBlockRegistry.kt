package com.github.bedrockecs.server.game.registry

import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent

/**
 * mutable variant of [BlockRegistry], only provided during configuration
 */
interface MutableBlockRegistry : BlockRegistry {

    // runtimeID - BlockType & BlockState //

    /**
     * set a runtime id for this block of this type
     */
    fun setRuntimeIDFor(type: BlockTypeComponent, rid: Int)

    /**
     * try to allocate a runtime id for this block of this type
     */
    fun allocateRuntimeIDFor(type: BlockTypeComponent): Int

    // Default BlockComponents //

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
}
