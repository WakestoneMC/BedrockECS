package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.LayeredBlockPosition
import com.github.bedrockecs.server.game.data.SubChunkPosition
import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.common.LoadType
import com.github.bedrockecs.server.game.db.world.WorldDB
import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.server.game.db.world.data.ChunkComponent
import com.github.bedrockecs.server.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.server.game.db.world.event.ChunkLoadingEvent
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunk
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.eventbus.publishFor
import com.github.bedrockecs.server.game.registry.BlockRegistry

class WorldDBImpl(evb: EventBus, reg: BlockRegistry) : WorldDB {

    private val block: BlockStore = BlockStore(evb, reg)

    private val metadata: ChunkMetadataStore = ChunkMetadataStore(evb)

    private val subChunkMetadata = SubChunkMetadataStore(evb)

    private val loadingEvent = evb.publishFor<ChunkLoadingEvent>("world-db")

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
        return block.placeBlock(pos, type, extras)
    }

    override fun <T : BlockComponent> mutateBlock(pos: LayeredBlockPosition, clazz: Class<T>, func: (T?) -> T?) {
        return block.mutateBlock(pos, clazz, func)
    }

    override fun <T : BlockComponent> readBlock(pos: LayeredBlockPosition, clazz: Class<T>): T? {
        return block.readBlock(pos, clazz)
    }

    override fun listBlock(pos: LayeredBlockPosition): Collection<BlockComponent> {
        return block.listBlock(pos)
    }

    override fun serialize(pos: ChunkPosition, includeComponents: Boolean): SerialChunk {
        assert(!includeComponents) { "include components version are not supported yet!" }
        val (smallestY, layers) = block.serialize(pos)

        val subchunks = layers.map { SerialSubChunk(emptyMap(), it) }
        return SerialChunk(
            components = emptyMap(),
            subChunks = subchunks,
            subChunksInitialY = smallestY * 16
        )
    }

    fun isLoaded(pos: ChunkPosition): Boolean {
        return metadata.isLoaded(pos)
    }

    fun listLoadedChunks(): Collection<ChunkPosition> {
        return metadata.listLoadedChunks()
    }

    fun load(pos: ChunkPosition, serial: SerialChunk) {
        metadata.load(pos, serial)
        subChunkMetadata.load(pos, serial)
        block.load(pos, serial)
        loadingEvent.publish(null, ChunkLoadingEvent(pos, LoadType.LOAD))
    }

    fun unload(pos: ChunkPosition): SerialChunk {
        loadingEvent.publish(null, ChunkLoadingEvent(pos, LoadType.UNLOAD))
        val chunks = metadata.unload(pos)
        val subchunkComponents = subChunkMetadata.unload(pos)
        val (smallestY, layers) = block.unload(pos)

        val subchunks = subchunkComponents.zip(layers).map { (c, layers) -> SerialSubChunk(c, layers) }
        return SerialChunk(
            components = chunks,
            subChunks = subchunks,
            subChunksInitialY = smallestY * 16
        )
    }

    fun unloadAllChunksInDimension(dim: Short) {
        listLoadedChunks()
            .filter { it.dim == dim }
            .forEach { unload(it) }
    }
}
