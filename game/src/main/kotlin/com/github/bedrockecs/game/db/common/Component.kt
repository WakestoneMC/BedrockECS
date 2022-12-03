package com.github.bedrockecs.game.db.common

/**
 * represents an ECS component
 * should be a dumb data container without behaviors
 */
interface Component {
    /**
     * interface for its companion object
     * @apiNote: we need the I prefix to prevent clashing name with companion object
     */
    interface ICompanion {
        /**
         * type of the component
         */
        val type: String
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
