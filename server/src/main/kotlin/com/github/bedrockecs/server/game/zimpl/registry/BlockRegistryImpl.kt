package com.github.bedrockecs.server.game.zimpl.registry

import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.common.MutableComponentMap
import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.server.game.registry.MutableBlockRegistry
import java.lang.IllegalArgumentException

class BlockRegistryImpl : MutableBlockRegistry {

    private val typeRID: MutableMap<BlockTypeComponent, Short> = mutableMapOf()

    private val globalDefault: MutableComponentMap<BlockComponent> = mutableMapOf()

    private val typeOverride: MutableMap<BlockTypeComponent, MutableComponentMap<BlockComponent?>> = mutableMapOf()

    // RIDs //

    override fun setRuntimeIDFor(type: BlockTypeComponent, rid: Short) {
        val current = typeRID.putIfAbsent(type, rid)
        if (current != null) {
            throw IllegalArgumentException("rid $rid is already assigned!")
        }
    }

    override fun runtimeIDFor(type: BlockTypeComponent): Short {
        return typeRID[type] ?: throw IllegalArgumentException("$type is not registered!")
    }

    override fun typeFor(rid: Short): BlockTypeComponent {
        val filtered = typeRID.filter { it.value == rid }
        assert(filtered.size == 1 || filtered.isEmpty())
        return filtered.map { it.key }.firstOrNull() ?: throw IllegalArgumentException("$rid does not belong to any type!")
    }

    override fun allocateRuntimeIDFor(type: BlockTypeComponent): Short {
        TODO("Not yet implemented")
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
