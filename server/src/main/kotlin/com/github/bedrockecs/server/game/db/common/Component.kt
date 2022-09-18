package com.github.bedrockecs.server.game.db.common

/**
 * represents an ECS component
 * should be a dumb data container without behaviors
 */
interface Component {
    /**
     * interface for its companion object
     */
    interface Companion {
        /**
         * type of the component
         */
        val TYPE: String
    }

    /**
     * type of the component, must be unique within its own subtype, such as block/item
     */
    val type: String

    /**
     * returns a deep copy of itself
     */
    fun clone(): Component
}
