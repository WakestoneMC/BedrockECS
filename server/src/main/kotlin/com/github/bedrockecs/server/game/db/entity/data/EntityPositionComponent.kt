package com.github.bedrockecs.server.game.db.entity.data

import com.github.bedrockecs.server.game.data.FloatBlockPosition

/**
 * represents an entity with position
 * @apiNote this also means it takes part in chunk loading and gets loaded / unloaded as well!
 */
data class EntityPositionComponent(
    var pos: FloatBlockPosition
) : EntityComponent {
    override val type: String
        get() = "becs:position"

    override fun clone(): EntityPositionComponent {
        return copy()
    }
}
