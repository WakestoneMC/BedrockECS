package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.LayeredBlockPosition
import com.github.bedrockecs.server.game.data.SubChunkPosition
import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.world.WorldDB
import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.server.game.db.world.data.ChunkComponent
import com.github.bedrockecs.server.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import com.github.bedrockecs.server.game.zimpl.eventbus.EventBusImpl

class WorldDBImpl(evb: EventBusImpl) : WorldDB {

    private val metadata: ChunkMetadataStore = ChunkMetadataStore(evb)

    private val subChunkMetadata = SubChunkMetadataStore(evb)

    override fun <T : ChunkComponent> mutateChunk(pos: ChunkPosition, clazz: Class<T>, func: (T?) -> T?) {
        metadata.mutateChunk(pos, clazz, func)
    }

    override fun <T : ChunkComponent> readChunk(pos: ChunkPosition, clazz: Class<T>): T? {
        return metadata.readChunk(pos, clazz)
    }

    override fun listChunk(pos: ChunkPosition): Collection<ChunkComponent> {
        return metadata.listChunk(pos)
    }

    override fun <T : SubChunkComponent> mutateSubChunk(pos: SubChunkPosition, clazz: Class<T>, func: (T?) -> T?) {
        return subChunkMetadata.mutateSubChunk(pos, clazz, func)
    }

    override fun <T : SubChunkComponent> readSubChunk(pos: SubChunkPosition, clazz: Class<T>): T? {
        return subChunkMetadata.readSubChunk(pos, clazz)
    }

    override fun listSubChunk(pos: SubChunkPosition): Collection<SubChunkComponent> {
        return subChunkMetadata.listSubChunk(pos)
    }

    override fun placeBlock(pos: LayeredBlockPosition, type: BlockTypeComponent, extras: ComponentMap<BlockComponent>) {
        TODO("Not yet implemented")
    }

    override fun <T : BlockComponent> mutateBlock(pos: LayeredBlockPosition, clazz: Class<T>, func: (T?) -> T?) {
        TODO("Not yet implemented")
    }

    override fun <T : BlockComponent> readBlock(pos: LayeredBlockPosition, clazz: Class<T>): T? {
        TODO("Not yet implemented")
    }

    override fun listBlock(pos: LayeredBlockPosition): Collection<BlockComponent> {
        TODO("Not yet implemented")
    }

    override fun serialize(pos: ChunkPosition): SerialChunk {
        TODO("Not yet implemented")
    }

    fun load(pos: ChunkPosition, serial: SerialChunk) {
        metadata.load(pos, serial)
        subChunkMetadata.load(pos, serial)
    }

    fun unload(pos: ChunkPosition): ChunkPosition {
        TODO("Not yet implemented")
    }
}
