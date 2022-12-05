package com.github.bedrockecs.game.db.world

import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.game.zimpl.db.world.AutoAssigningWorldDBSerial

/**
 * serialization format backend for [WorldDB]
 */
interface WorldDBSerial {
    companion object {
        /**
         * mock serial that assigns a new id for every unseen block type
         */
        fun createAutoAssigning(airBlockType: BlockTypeComponent): WorldDBSerial {
            return AutoAssigningWorldDBSerial(airBlockType)
        }
    }

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
