package com.github.bedrockecs.server.game.db.entity.data

import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.nukkitx.math.vector.Vector3f

/**
 * represents an entity with position
 * @apiNote this also means it takes part in chunk loading and gets loaded / unloaded as well!
 */
data class EntityPositionComponent(
    var pos: FloatBlockPosition,
    var direction: Vector3f
) : EntityComponent {
    override val type: String
        get() = "becs:position"

    override fun clone(): EntityPositionComponent {
        return copy()
    }
}
