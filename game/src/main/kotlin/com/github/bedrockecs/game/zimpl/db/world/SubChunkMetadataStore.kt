package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.data.SubChunkPosition
import com.github.bedrockecs.game.db.common.MutableComponentMap
import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.game.db.world.event.SubChunkMutationEvent
import com.github.bedrockecs.game.db.world.serial.SerialChunk
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.eventbus.publishFor
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

class SubChunkMetadataStore(
    evb: EventBus,
    private val installChunkCallback: (ChunkPosition) -> Unit
) {

    private val mutationEvent = evb.publishFor<SubChunkMutationEvent>("world-db")

    data class Entry(
        val lock: ReentrantReadWriteLock = ReentrantReadWriteLock(),
        val map: MutableComponentMap<SubChunkComponent> = mutableMapOf()
    )

    private val chunksLock = ReentrantReadWriteLock()

    private val chunks = mutableMapOf<SubChunkPosition, Entry>()

    private val chunkSubChunkMap = ConcurrentHashMap<ChunkPosition, List<SubChunkPosition>>()

    fun listSubChunk(pos: SubChunkPosition): Collection<SubChunkComponent> {
        // FIXME: this is technically speaking a race condition
        val contains = chunksLock.read { chunks.contains(pos) }
        if (!contains) {
            installChunkCallback(pos.toChunk())
        }
        chunksLock.read {
            val entry = chunks[pos]!!
            entry.lock.read {
                return entry.map.values.toList()
            }
        }
    }

    fun <T : SubChunkComponent> readSubChunk(pos: SubChunkPosition, clazz: Class<T>): T? {
        // FIXME: this is technically speaking a race condition
        val contains = chunksLock.read { chunks.contains(pos) }
        if (!contains) {
            installChunkCallback(pos.toChunk())
        }
        chunksLock.read {
            val entry = chunks[pos]!!
            entry.lock.read {
                return entry.map[clazz] as T?
            }
        }
    }

    fun <T : SubChunkComponent> mutateSubChunk(pos: SubChunkPosition, clazz: Class<T>, func: (T?) -> T?) {
        // FIXME: this is technically speaking a race condition
        val contains = chunksLock.read { chunks.contains(pos) }
        if (!contains) {
            installChunkCallback(pos.toChunk())
        }
        val oldValue: T?
        val newValue: T?
        chunksLock.read {
            val entry = chunks[pos]!!
            entry.lock.write {
                oldValue = entry.map[clazz] as T?
                newValue = func(oldValue)

                if (oldValue != null && newValue == null) {
                    mutationEvent.publish(oldValue.type, SubChunkMutationEvent(pos, MutateType.REMOVE))
                }

                if (newValue == null) {
                    entry.map.remove(clazz)
                } else {
                    entry.map[clazz] = newValue
                }
            }
        }
        if (oldValue == null && newValue != null) {
            mutationEvent.publish(newValue.type, SubChunkMutationEvent(pos, MutateType.ADD))
        } else if (oldValue != null && newValue != null) {
            mutationEvent.publish(newValue.type, SubChunkMutationEvent(pos, MutateType.UPDATE))
        }
    }

    fun install(pos: ChunkPosition, serial: SerialChunk) {
        val addedSubChunks = mutableListOf<SubChunkPosition>()
        chunksLock.write {
            if (chunks.contains(SubChunkPosition(pos.chunkX, 0, pos.chunkZ))) {
                throw IllegalArgumentException("chunk already loaded!")
            }
            serial.subChunks.forEachIndexed { index, subChunk ->
                val subpos = SubChunkPosition(pos.chunkX, index + serial.initialY / 16, pos.chunkZ)
                addedSubChunks.add(subpos)
                chunks[subpos] = Entry(
                    map = subChunk.components.toMutableMap()
                )
            }
        }
        chunkSubChunkMap[pos] = addedSubChunks
    }

    fun uninstall(pos: ChunkPosition): List<MutableComponentMap<SubChunkComponent>> {
        val removed = chunksLock.write {
            val subchunks = chunkSubChunkMap.remove(pos) ?: throw IllegalArgumentException("chunks is not loaded!")
            val removedSubChunks = mutableListOf<Entry>()
            subchunks.forEach { removedSubChunks.add(chunks.remove(it)!!) }
            removedSubChunks
        }
        return removed.map { it.map }
    }
}
