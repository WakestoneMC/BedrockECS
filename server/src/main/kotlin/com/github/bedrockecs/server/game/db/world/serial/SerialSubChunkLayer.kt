package com.github.bedrockecs.server.game.db.world.serial

import com.github.bedrockecs.server.game.data.LayeredBlockPosition
import com.github.bedrockecs.server.game.db.world.data.BlockComponent

/**
 * represents various serial form of one layer of a subchunk
 */
sealed class SerialSubChunkLayer {
    /**
     * represents instance level non-default components
     */
    abstract val overrides: Map<LayeredBlockPosition, Map<Class<out BlockComponent>, BlockComponent?>>

    /**
     * naively stores runtime id as short array
     */
    data class UnPalettedShort(
        val ids: ShortArray,
        override val overrides: Map<LayeredBlockPosition, Map<Class<out BlockComponent>, BlockComponent?>>
    ) : SerialSubChunkLayer()
}
