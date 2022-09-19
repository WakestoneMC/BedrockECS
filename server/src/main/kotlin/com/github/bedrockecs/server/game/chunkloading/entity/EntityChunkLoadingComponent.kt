package com.github.bedrockecs.server.game.chunkloading.entity

import com.github.bedrockecs.server.game.db.entity.data.EntityComponent

/**
 * chunk loading component, used to keep chunks around the entity loaded
 */
data class EntityChunkLoadingComponent(
    /**
     * radius of chunks to load, 0=>1*1, 1=>3*3, 2=>5*5, etc......
     */
    var radius: Int
) : EntityComponent {
    override val type: String
        get() = "becs:chunk_loading"

    override fun clone(): EntityChunkLoadingComponent {
        return copy()
    }
}
