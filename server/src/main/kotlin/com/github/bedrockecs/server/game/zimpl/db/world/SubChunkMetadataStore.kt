package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.SubChunkPosition
import com.github.bedrockecs.server.game.db.common.MutableComponentMap
import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.server.game.db.world.event.SubChunkMutationEvent
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import com.github.bedrockecs.server.game.eventbus.publishFor
import com.github.bedrockecs.server.game.zimpl.eventbus.EventBusImpl
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

class SubChunkMetadataStore(
    evb: EventBusImpl
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
        chunksLock.read {
            val entry = chunks[pos] ?: throw IllegalArgumentException("chunk ${pos.toChunk()} is not loaded!")
            entry.lock.read {
                return entry.map.values.toList()
            }
        }
    }

    fun <T : SubChunkComponent> readSubChunk(pos: SubChunkPosition, clazz: Class<T>): T? {
        chunksLock.read {
            val entry = chunks[pos] ?: throw IllegalArgumentException("chunk ${pos.toChunk()} is not loaded!")
            entry.lock.read {
                return entry.map[clazz] as T?
            }
        }
    }

    fun <T : SubChunkComponent> mutateSubChunk(pos: SubChunkPosition, clazz: Class<T>, func: (T?) -> T?) {
        val oldValue: T?
        val newValue: T?
        chunksLock.read {
            val entry = chunks[pos] ?: throw IllegalArgumentException("chunk ${pos.toChunk()} is not loaded!")
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

    fun load(pos: ChunkPosition, serial: SerialChunk) {
        val addedSubChunks = mutableListOf<SubChunkPosition>()
        chunksLock.write {
            if (chunks.contains(SubChunkPosition(pos.x, 0, pos.z, pos.dim))) {
                throw IllegalArgumentException("chunk already loaded!")
            }
            serial.subChunks.forEachIndexed { index, subChunk ->
                val subpos = SubChunkPosition(pos.x, index + serial.subChunksInitialY / 16, pos.z, pos.dim)
                addedSubChunks.add(subpos)
                chunks[subpos] = Entry(
                    map = subChunk.components.toMutableMap()
                )
            }
        }
        chunkSubChunkMap[pos] = addedSubChunks
    }

    fun unload(pos: ChunkPosition): List<MutableComponentMap<SubChunkComponent>> {
        val removed = chunksLock.write {
            val subchunks = chunkSubChunkMap.remove(pos) ?: throw IllegalArgumentException("chunks is not loaded!")
            val removedSubChunks = mutableListOf<Entry>()
            subchunks.forEach { removedSubChunks.add(chunks.remove(it)!!) }
            removedSubChunks
        }
        return removed.map { it.map }
    }
}
