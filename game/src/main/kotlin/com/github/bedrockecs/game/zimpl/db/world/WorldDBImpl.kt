package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.data.LayeredBlockPosition
import com.github.bedrockecs.game.data.SubChunkPosition
import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.common.LoadType
import com.github.bedrockecs.game.db.world.BlockRegistry
import com.github.bedrockecs.game.db.world.WorldDBSerial
import com.github.bedrockecs.game.db.world.WorldDBStorage
import com.github.bedrockecs.game.db.world.data.BlockComponent
import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.game.db.world.data.ChunkComponent
import com.github.bedrockecs.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.game.db.world.event.ChunkLoadingEvent
import com.github.bedrockecs.game.db.world.serial.SerialChunk
import com.github.bedrockecs.game.db.world.serial.SerialSubChunk
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.eventbus.publishFor
import java.util.concurrent.CompletableFuture
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class WorldDBImpl(
    evb: EventBus,
    registry: BlockRegistry,
    serial: WorldDBSerial,
    private val storage: WorldDBStorage
) : WorldDBInternal {
    
    private val chunksLock = ReentrantLock()
    
    private val loadedChunks = mutableSetOf<ChunkPosition>()
    
    private val block: BlockStore = BlockStore(evb, serial, registry, this::installChunkCallback)

    private val metadata: ChunkMetadataStore = ChunkMetadataStore(evb, this::installChunkCallback)

    private val subChunkMetadata = SubChunkMetadataStore(evb, this::installChunkCallback)

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
            initialY = smallestY * 16
        )
    }

    override fun isLoaded(pos: ChunkPosition): Boolean {
        chunksLock.withLock {
            return loadedChunks.contains(pos)
        }
    }

    override fun listLoadedChunks(): Collection<ChunkPosition> {
        return chunksLock.withLock { HashSet(loadedChunks) }
    }

    override fun loadChunk(pos: ChunkPosition): CompletableFuture<Void> {
        installChunkCallback(pos)
        chunksLock.withLock {
            loadedChunks.add(pos)
        }
        loadingEvent.publish(null, ChunkLoadingEvent(pos, LoadType.LOAD))
        return CompletableFuture.completedFuture(null)
    }

    override fun unloadChunk(pos: ChunkPosition): CompletableFuture<Void> {
        loadingEvent.publish(null, ChunkLoadingEvent(pos, LoadType.UNLOAD))
        chunksLock.withLock {
            loadedChunks.remove(pos)
        }
        return CompletableFuture.completedFuture(null)
    }

    override fun tick() {
        val loadedChunks = listLoadedChunks().toSet()
        val presentChunks = metadata.listPresentChunks().toSet()
        val toUninstall = presentChunks - loadedChunks
        toUninstall.forEach { uninstall(it) }
    }

    private fun installChunkCallback(pos: ChunkPosition) {
        if (!metadata.isPresent(pos)) {
            val read = storage.readChunk(pos).join()
            install(pos, read)
        }
    }

    private fun install(pos: ChunkPosition, serial: SerialChunk) {
        metadata.install(pos, serial)
        subChunkMetadata.install(pos, serial)
        block.install(pos, serial)
    }

    private fun uninstall(pos: ChunkPosition): SerialChunk {
        val chunks = metadata.uninstall(pos)
        val subchunkComponents = subChunkMetadata.uninstall(pos)
        val (smallestY, layers) = block.uninstall(pos)

        val subchunks = subchunkComponents.zip(layers).map { (c, layers) -> SerialSubChunk(c, layers) }
        return SerialChunk(
            components = chunks,
            subChunks = subchunks,
            initialY = smallestY * 16
        )
    }
}
