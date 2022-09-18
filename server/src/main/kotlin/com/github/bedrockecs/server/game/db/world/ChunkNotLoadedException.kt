package com.github.bedrockecs.server.game.db.world

import com.github.bedrockecs.server.game.data.ChunkPosition

class ChunkNotLoadedException(
    val position: ChunkPosition,
) : IllegalArgumentException("chunk $position is not loaded!")
