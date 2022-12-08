package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.common.MutableComponentMap
import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.world.data.ChunkComponent
import com.github.bedrockecs.game.db.world.event.ChunkMutationEvent
import com.github.bedrockecs.game.db.world.serial.SerialChunk
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.eventbus.publishFor
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

class ChunkMetadataStore(
    evb: EventBus,
    private val installChunkCallback: (ChunkPosition) -> Unit
) {

    private val mutationEvent = evb.publishFor<ChunkMutationEvent>("world-db")

    data class ChunkEntry(
        val lock: ReentrantReadWriteLock = ReentrantReadWriteLock(),
        val map: MutableComponentMap<ChunkComponent> = mutableMapOf()
    )

    private val chunksLock = ReentrantReadWriteLock()

    private val chunks = mutableMapOf<ChunkPosition, ChunkEntry>()

    fun listChunk(pos: ChunkPosition): Collection<ChunkComponent> {
        // FIXME: this is technically speaking a race condition
        val contains = chunksLock.read { chunks.contains(pos) }
        if (!contains) {
            installChunkCallback(pos)
        }
        chunksLock.read {
            val entry = chunks[pos]!!
            entry.lock.read {
                return entry.map.values.toList()
            }
        }
    }

    fun <T : ChunkComponent> readChunk(pos: ChunkPosition, clazz: Class<T>): T? {
        // FIXME: this is technically speaking a race condition
        val contains = chunksLock.read { chunks.contains(pos) }
        if (!contains) {
            installChunkCallback(pos)
        }
        chunksLock.read {
            val entry = chunks[pos]!!
            entry.lock.read {
                return entry.map[clazz] as T?
            }
        }
    }

    fun <T : ChunkComponent> mutateChunk(pos: ChunkPosition, clazz: Class<T>, func: (T?) -> T?) {
        // FIXME: this is technically speaking a race condition
        val contains = chunksLock.read { chunks.contains(pos) }
        if (!contains) {
            installChunkCallback(pos)
        }
        val oldValue: T?
        val newValue: T?
        chunksLock.read {
            val entry = chunks[pos]!!
            entry.lock.write {
                oldValue = entry.map[clazz] as T?
                newValue = func(oldValue)

                if (oldValue != null && newValue == null) {
                    mutationEvent.publish(oldValue.type, ChunkMutationEvent(pos, MutateType.REMOVE))
                }

                if (newValue == null) {
                    entry.map.remove(clazz)
                } else {
                    entry.map[clazz] = newValue
                }
            }
        }
        if (oldValue == null && newValue != null) {
            mutationEvent.publish(newValue.type, ChunkMutationEvent(pos, MutateType.ADD))
        } else if (oldValue != null && newValue != null) {
            mutationEvent.publish(newValue.type, ChunkMutationEvent(pos, MutateType.UPDATE))
        }
    }

    fun isPresent(pos: ChunkPosition): Boolean {
        chunksLock.read {
            return chunks.contains(pos)
        }
    }

    fun listPresentChunks(): Collection<ChunkPosition> {
        chunksLock.read {
            return chunks.keys.toList()
        }
    }

    fun install(pos: ChunkPosition, serial: SerialChunk) {
        chunksLock.write {
            if (chunks.contains(pos)) {
                throw IllegalArgumentException("chunk already present!")
            }

            chunks[pos] = ChunkEntry(
                map = serial.components.toMutableMap()
            )
        }
    }

    fun uninstall(pos: ChunkPosition): ComponentMap<ChunkComponent> {
        chunksLock.write {
            val ret = chunks.remove(pos) ?: throw IllegalArgumentException("chunk is not present!")
            return ret.map
        }
    }
}
