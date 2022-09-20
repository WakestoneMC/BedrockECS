package com.github.bedrockecs.server.game.db.dimension

import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent

/**
 * used to synchronously listen to changes related to dimensions, KERNEL USE ONLY!
 */
interface DimensionChangeListener {
    fun onCreatingDimension(id: Short, components: Set<DimensionComponent>)

    fun onUpdatingDimensionComponent(id: Short, from: DimensionComponent?, to: DimensionComponent?)

    fun onDestroyedDimension(id: Short, components: Set<DimensionComponent>)
}
