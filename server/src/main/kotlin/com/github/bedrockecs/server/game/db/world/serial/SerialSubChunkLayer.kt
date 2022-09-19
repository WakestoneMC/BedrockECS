package com.github.bedrockecs.server.game.db.world.serial

import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE
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
    ) : SerialSubChunkLayer() {
        fun idAtOffset(x: Int, y: Int, z: Int): Short {
            val idx = x + (y * SUBCHUNK_SIZE) + (z * SUBCHUNK_SIZE * SUBCHUNK_SIZE)
            return ids[idx]
        }
    }
}
