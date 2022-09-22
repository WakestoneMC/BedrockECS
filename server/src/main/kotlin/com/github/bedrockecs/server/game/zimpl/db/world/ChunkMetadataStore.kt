package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.common.MutableComponentMap
import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.world.data.ChunkComponent
import com.github.bedrockecs.server.game.db.world.event.ChunkMutationEvent
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import com.github.bedrockecs.server.game.eventbus.publishFor
import com.github.bedrockecs.server.game.zimpl.eventbus.EventBusImpl
import java.lang.IllegalArgumentException
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

class ChunkMetadataStore(
    evb: EventBusImpl
) {

    private val mutationEvent = evb.publishFor<ChunkMutationEvent>("world-db")

    data class ChunkEntry(
        val lock: ReentrantReadWriteLock = ReentrantReadWriteLock(),
        val map: MutableComponentMap<ChunkComponent> = mutableMapOf()
    )

    private val chunksLock = ReentrantReadWriteLock()

    private val chunks = mutableMapOf<ChunkPosition, ChunkEntry>()

    fun listChunk(pos: ChunkPosition): Collection<ChunkComponent> {
        chunksLock.read {
            val entry = chunks[pos] ?: throw IllegalArgumentException("chunk $pos is not loaded!")
            entry.lock.read {
                return entry.map.values.toList()
            }
        }
    }

    fun <T : ChunkComponent> readChunk(pos: ChunkPosition, clazz: Class<T>): T? {
        chunksLock.read {
            val entry = chunks[pos] ?: throw IllegalArgumentException("chunk $pos is not loaded!")
            entry.lock.read {
                return entry.map[clazz] as T?
            }
        }
    }

    fun <T : ChunkComponent> mutateChunk(pos: ChunkPosition, clazz: Class<T>, func: (T?) -> T?) {
        val oldValue: T?
        val newValue: T?
        chunksLock.read {
            val entry = chunks[pos] ?: throw IllegalArgumentException("chunk $pos is not loaded!")
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

    fun load(pos: ChunkPosition, serial: SerialChunk) {
        chunksLock.write {
            if (chunks.contains(pos)) {
                throw IllegalArgumentException("chunk already loaded!")
            }

            chunks[pos] = ChunkEntry(
                map = serial.components.toMutableMap()
            )
        }
    }

    fun unload(pos: ChunkPosition): ComponentMap<ChunkComponent> {
        chunksLock.write {
            val ret = chunks.remove(pos) ?: throw IllegalArgumentException("chunk is not loaded!")
            return ret.map
        }
    }
}
