package com.github.bedrockecs.server.game.db.world.serial

import com.github.bedrockecs.server.common.palette.PalettedStorage
import com.github.bedrockecs.server.game.data.LayeredBlockPosition
import com.github.bedrockecs.server.game.db.world.data.BlockComponent

/**
 * represents various serial form of one layer of a subchunk
 */
data class SerialSubChunkLayer(
    /**
     * palette storage of runtime IDs
     */
    val storage: PalettedStorage,
    /**
     * represents instance level non-default components
     */
    val overrides: Map<LayeredBlockPosition, Map<Class<out BlockComponent>, BlockComponent?>>
)
