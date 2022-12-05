package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.common.MutableComponentMap
import com.github.bedrockecs.game.db.world.BlockRegistry
import com.github.bedrockecs.game.db.world.data.BlockComponent
import com.github.bedrockecs.game.db.world.data.BlockTypeComponent

class BlockRegistryImpl : BlockRegistry {
    
    private val blockTypeSet: MutableSet<BlockTypeComponent> = mutableSetOf()
    
    private val globalDefault: MutableComponentMap<BlockComponent> = mutableMapOf()

    private val typeOverride: MutableMap<BlockTypeComponent, MutableComponentMap<BlockComponent?>> = mutableMapOf()
    
    // block types //
    
    override val blockTypes: Collection<BlockTypeComponent>
        get() = blockTypeSet

    override fun registerBlockType(type: BlockTypeComponent) {
        blockTypeSet.add(type)
    }

    // defaults //

    override val defaultComponents: ComponentMap<BlockComponent>
        get() = globalDefault

    override fun addDefaultComponent(component: BlockComponent) {
        globalDefault[component.javaClass] = component
    }

    override fun removeDefaultComponent(component: Class<out BlockComponent>) {
        globalDefault.remove(component)
    }

    override fun typeDefaultOverrideFor(type: BlockTypeComponent): ComponentMap<BlockComponent?> {
        val maybeMap = typeOverride[type]
        return maybeMap ?: emptyMap()
    }

    override fun addTypeDefaultOverride(type: BlockTypeComponent, clazz: Class<out BlockComponent>, component: BlockComponent?) {
        typeOverride.getOrPut(type) { mutableMapOf() }[clazz] = component
    }

    override fun removeTypeDefaultOverride(type: BlockTypeComponent, clazz: Class<out BlockComponent>) {
        val overrides = typeOverride[type]
        overrides?.remove(clazz)
    }

    override fun <T : BlockComponent> defaultComponentOf(type: BlockTypeComponent, clazz: Class<T>): T? {
        return if (typeOverride.contains(type) && typeOverride[type]!!.contains(clazz)) {
            typeOverride[type]!![clazz] as T?
        } else {
            globalDefault[clazz] as T?
        }
    }

    override fun defaultComponentsOf(type: BlockTypeComponent): ComponentMap<BlockComponent> {
        val default = globalDefault.toMutableMap()
        val over = typeOverride.getOrDefault(type, emptyMap())
        over.forEach {
            if (it.value == null) {
                default.remove(it.key)
            } else {
                default[it.key] = it.value!!
            }
        }
        return default
    }
}
