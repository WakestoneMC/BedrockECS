package com.github.bedrockecs.game.db.world

import com.github.bedrockecs.game.db.world.data.BlockTypeComponent

/**
 * serialization format backend for [WorldDB]
 */
interface WorldDBSerial {
    /**
     * type for air block
     */
    val airBlockType: BlockTypeComponent

    /**
     * from id to component
     */
    fun blockTypeFor(id: Short): BlockTypeComponent

    /**
     * from component to id
     */
    fun idFor(type: BlockTypeComponent): Short
}
