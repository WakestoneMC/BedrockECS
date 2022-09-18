package com.github.bedrockecs.server.game.db.world

import com.github.bedrockecs.server.game.data.SubChunkPosition
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunk

/**
 * [WorldStore], but provides access to its underlying serialized form
 */
interface SerializableWorldStore : WorldStore {
    /**
     * get the serialized form of the subchunk
     * @throws ChunkNotLoadedException if the corresponding chunk is not loaded
     */
    fun serialize(pos: SubChunkPosition): SerialSubChunk
}
