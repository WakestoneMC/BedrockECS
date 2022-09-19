package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.LayeredBlockPosition
import com.github.bedrockecs.server.game.data.SubChunkPosition
import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.common.LoadType
import com.github.bedrockecs.server.game.db.common.MutableComponentMap
import com.github.bedrockecs.server.game.db.world.ChunkNotLoadedException
import com.github.bedrockecs.server.game.db.world.WorldDB
import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.server.game.db.world.data.ChunkComponent
import com.github.bedrockecs.server.game.db.world.data.SubChunkComponent
import com.github.bedrockecs.server.game.db.world.event.BlockMutationEvent
import com.github.bedrockecs.server.game.db.world.event.ChunkLoadingEvent
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunk
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.eventbus.Publisher
import com.github.bedrockecs.server.game.registry.BlockRegistry
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * a laughably naive implementation of [BlockDB]
 * used to get everything up & running, this is OK because our API is clean
 */
class NaiveWorldDB(
    eventBus: EventBus,
    private val registry: BlockRegistry
) : WorldDB {

    private val mutationEvent: Publisher<BlockMutationEvent.Single> = eventBus.publishFor(
        EventBus.PublishConfig(
            name = "blockdb",
            eventType = BlockMutationEvent.Single::class.java
        )
    )

    private val loadingEvent = eventBus.publishFor(
        EventBus.PublishConfig(
            name = "blockdb",
            eventType = ChunkLoadingEvent::class.java
        )
    )

    private val dbLock = ReentrantLock()

    /**
     * array of map of extras+{type,state}
     */
    private val db: MutableMap<SubChunkPosition, List<NaiveSubChunkTablet>> = mutableMapOf()

    private val subchunkDB: MutableMap<SubChunkPosition, MutableComponentMap<SubChunkComponent>> = mutableMapOf()

    private val chunkDB: MutableMap<ChunkPosition, MutableComponentMap<ChunkComponent>> = mutableMapOf()

    override fun placeBlock(pos: LayeredBlockPosition, type: BlockTypeComponent, extras: ComponentMap<BlockComponent>) {
        dbLock.withLock {
            val chunk = db[pos.toSubChunk()] ?: throw ChunkNotLoadedException(pos.toSubChunk().toChunk())
            chunk[pos.layer.toInt()].place(pos, type, extras)
        }
    }

    override fun <T : BlockComponent> mutateBlock(pos: LayeredBlockPosition, clazz: Class<T>, func: (T?) -> T?) {
        dbLock.withLock {
            val chunk = db[pos.toSubChunk()] ?: throw ChunkNotLoadedException(pos.toSubChunk().toChunk())
            chunk[pos.layer.toInt()].mutate(pos, clazz, func)
        }
    }

    override fun <T : BlockComponent> readBlock(pos: LayeredBlockPosition, clazz: Class<T>): T? {
        dbLock.withLock {
            val chunk = db[pos.toSubChunk()] ?: throw ChunkNotLoadedException(pos.toSubChunk().toChunk())
            return chunk[pos.layer.toInt()].read(pos, clazz)
        }
    }

    override fun listBlock(pos: LayeredBlockPosition): Collection<BlockComponent> {
        dbLock.withLock {
            val chunk = db[pos.toSubChunk()] ?: throw ChunkNotLoadedException(pos.toSubChunk().toChunk())
            return chunk[pos.layer.toInt()].list(pos)
        }
    }

    override fun <T : ChunkComponent> mutateChunk(pos: ChunkPosition, clazz: Class<T>, func: (T?) -> T?) {
        dbLock.withLock {
            val chunk = chunkDB[pos] ?: throw ChunkNotLoadedException(pos)
            val ret = func(chunk[clazz] as T?)
            if (ret != null) {
                chunkDB[pos]!![clazz] = ret
            }
        }
    }

    override fun <T : SubChunkComponent> mutateSubChunk(pos: SubChunkPosition, clazz: Class<T>, func: (T?) -> T?) {
        dbLock.withLock {
            val chunk = subchunkDB[pos] ?: throw ChunkNotLoadedException(pos.toChunk())
            val ret = func(chunk[clazz] as T?)
            if (ret != null) {
                subchunkDB[pos]!![clazz] = ret
            }
        }
    }

    override fun <T : ChunkComponent> readChunk(pos: ChunkPosition, clazz: Class<T>): T? {
        return dbLock.withLock {
            val map = chunkDB[pos]
            if (map == null) {
                throw ChunkNotLoadedException(pos)
            } else {
                map[clazz] as T
            }
        }
    }

    override fun listChunk(pos: ChunkPosition): Collection<ChunkComponent> {
        return dbLock.withLock {
            val map = chunkDB[pos]
            map?.values?.toList() ?: throw ChunkNotLoadedException(pos)
        }
    }

    override fun <T : SubChunkComponent> readSubChunk(pos: SubChunkPosition, clazz: Class<T>): T? {
        return dbLock.withLock {
            val map = subchunkDB[pos]
            if (map == null) {
                throw ChunkNotLoadedException(pos.toChunk())
            } else {
                map[clazz] as T
            }
        }
    }

    override fun listSubChunk(pos: SubChunkPosition): Collection<SubChunkComponent> {
        return dbLock.withLock {
            val map = subchunkDB[pos]
            map?.values?.toList() ?: throw ChunkNotLoadedException(pos.toChunk())
        }
    }

    override fun serialize(pos: ChunkPosition): SerialChunk {
        val (chunk, tablets) = dbLock.withLock {
            val subchunks = db.keys.filter { it.toChunk() == pos }.toSet()
            if (subchunks.isEmpty()) {
                throw IllegalStateException("chunk $pos is not loaded!")
            }
            loadingEvent.publish(null, ChunkLoadingEvent(pos, LoadType.UNLOAD))

            val tablets = subchunks.map {
                Triple(it, db.remove(it)!!, subchunkDB.remove(it)!!)
            }
            chunkDB.get(pos)!! to tablets
        }
        val serials = tablets
            .map {
                it.first to SerialSubChunk(it.third, it.second.map { it.serialize() })
            }
            .sortedBy { it.first.y }
            .map { it.second }
            .toList()
        return SerialChunk(chunk, serials)
    }

    fun load(pos: ChunkPosition, serials: SerialChunk) {
        val deserialized = serials.subChunks.mapIndexed { index, subChunk ->
            val subChunkPos = SubChunkPosition(pos.x, index, pos.z, pos.dim)
            val tablets = subChunk
                .layers.mapIndexed { index, layer ->
                    NaiveSubChunkTablet.deserialize(
                        registry,
                        mutationEvent,
                        subChunkPos.toLayered(index.toShort()),
                        layer
                    )
                }.toList()
            subChunkPos to tablets
        }
        dbLock.withLock {
            if (db[deserialized[0].first] != null) {
                throw IllegalStateException("chunk $pos already loaded!")
            }
            deserialized.forEach { (subChunkPos, layers) ->
                db[subChunkPos] = layers
            }
        }
        loadingEvent.publish(null, ChunkLoadingEvent(pos, LoadType.LOAD))
    }

    fun unload(pos: ChunkPosition): SerialChunk {
        val (chunk, tablets) = dbLock.withLock {
            val subchunks = db.keys.filter { it.toChunk() == pos }.toSet()
            if (subchunks.isEmpty()) {
                throw IllegalStateException("chunk $pos is not loaded!")
            }
            loadingEvent.publish(null, ChunkLoadingEvent(pos, LoadType.UNLOAD))

            val tablets = subchunks.map {
                Triple(it, db.remove(it)!!, subchunkDB.remove(it)!!)
            }
            chunkDB.remove(pos)!! to tablets
        }
        val serials = tablets
            .map {
                it.first to SerialSubChunk(it.third, it.second.map { it.serialize() })
            }
            .sortedBy { it.first.y }
            .map { it.second }
            .toList()
        return SerialChunk(chunk, serials)
    }
}
