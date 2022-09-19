package com.github.bedrockecs.server.game.db.world

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk

/**
 * [WorldStore], but provides access to its underlying serialized form
 */
interface SerializableWorldStore : WorldStore {
    /**
     * get the serialized form of the chunk
     * @throws ChunkNotLoadedException if the corresponding chunk is not loaded
     */
    fun serialize(pos: ChunkPosition): SerialChunk
}
