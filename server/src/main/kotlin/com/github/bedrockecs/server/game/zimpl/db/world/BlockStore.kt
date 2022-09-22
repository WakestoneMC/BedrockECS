package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.common.palette.PalettedStorage
import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.LayeredBlockPosition
import com.github.bedrockecs.server.game.data.SubChunkPosition
import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.common.MutableComponentMap
import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.server.game.db.world.event.BlockMutationEvent
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunkLayer
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.eventbus.publishFor
import com.github.bedrockecs.server.game.registry.BlockRegistry
import com.github.bedrockecs.vanilla.blocks.world.AirBlockType
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.IllegalArgumentException
import kotlin.concurrent.read
import kotlin.concurrent.write

class BlockStore(evb: EventBus, private val registry: BlockRegistry) {

    private val mutationEvent = evb.publishFor<BlockMutationEvent>("blockdb")

    private data class Entry(
        val lock: ReentrantReadWriteLock = ReentrantReadWriteLock(),
        val layers: MutableList<PalettedStorage> = mutableListOf(),
        val overrides: MutableMap<LayeredBlockPosition, MutableComponentMap<BlockComponent?>> = mutableMapOf()
    )

    private val entryMapLock = ReentrantReadWriteLock()

    private val entries: MutableMap<SubChunkPosition, Entry> = mutableMapOf()

    private val chunkSubChunkMap = ConcurrentHashMap<ChunkPosition, List<SubChunkPosition>>()

    fun placeBlock(pos: LayeredBlockPosition, type: BlockTypeComponent, extras: ComponentMap<BlockComponent?>) {
        entryMapLock.read {
            val entry = entries[pos.toSubChunk()] ?: throw IllegalArgumentException("chunk ${pos.toSubChunk().toChunk()} is not loaded!")
            entry.lock.write {
                while (true) {
                    if (entry.layers.size <= pos.layer) {
                        // TODO: decouple air block type from this, perhaps blockRegistry.defaultBlockRID()
                        entry.layers.add(PalettedStorage.createWithDefaultState(AirBlockType.primary.runtimeID.toInt()))
                    } else {
                        break
                    }
                }

                val (x, y, z) = pos.subchunkOffsets
                val oldRID = entry.layers[pos.layer.toInt()].getBlock(x, y, z)
                val oldType = registry.typeFor(oldRID.toShort())

                computeComponentMap(oldType, entry.overrides[pos] ?: emptyMap()).forEach {
                    mutationEvent.publish(it.value.type, BlockMutationEvent.Single(pos, MutateType.REMOVE))
                }

                entry.layers[pos.layer.toInt()].setBlock(x, y, z, registry.runtimeIDFor(type).toInt())
                entry.overrides[pos] = extras.toMutableMap()
            }
            computeComponentMap(type, entry.overrides[pos] ?: emptyMap()).forEach {
                mutationEvent.publish(it.value.type, BlockMutationEvent.Single(pos, MutateType.ADD))
            }
        }
    }

    fun <T : BlockComponent> mutateBlock(pos: LayeredBlockPosition, clazz: Class<T>, func: (T?) -> T?) {
        entryMapLock.read {
            val entry = entries[pos.toSubChunk()] ?: throw IllegalArgumentException(
                "chunk ${
                pos.toSubChunk().toChunk()
                } is not loaded!"
            )
            entry.lock.write {
                if (clazz == BlockTypeComponent::class.java) {
                    val (x, y, z) = pos.subchunkOffsets
                    val oldValue = registry.typeFor(entry.layers[pos.layer.toInt()].getBlock(x, y, z).toShort())
                    val newValue = func(oldValue as T?) as BlockTypeComponent
                    assert(oldValue.blockType == newValue.blockType) {
                        "blockType cannot be changed in mutateBlock(), use placeBlock() instead!"
                    }
                    val newRid = registry.runtimeIDFor(newValue)
                    entry.layers[pos.layer.toInt()].setBlock(x, y, z, newRid.toInt())
                    mutationEvent.publish(BlockTypeComponent.TYPE, BlockMutationEvent.Single(pos, MutateType.UPDATE))
                } else {
                    val (x, y, z) = pos.subchunkOffsets
                    val type = registry.typeFor(entry.layers[pos.layer.toInt()].getBlock(x, y, z).toShort())

                    val oldValue: T? = if (entry.overrides[pos] != null && entry.overrides[pos]!!.contains(clazz)) {
                        entry.overrides[pos]!![clazz] as T?
                    } else {
                        registry.defaultComponentOf(type, clazz)
                    }

                    val newValue = func(oldValue)

                    if (oldValue != null && newValue == null) {
                        mutationEvent.publish(oldValue.type, BlockMutationEvent.Single(pos, MutateType.REMOVE))
                    }

                    entry.overrides.getOrPut(pos) { mutableMapOf() }.put(clazz, newValue)

                    if (oldValue == null && newValue != null) {
                        mutationEvent.publish(newValue.type, BlockMutationEvent.Single(pos, MutateType.ADD))
                    }
                    if (oldValue != null && newValue != null) {
                        mutationEvent.publish(newValue.type, BlockMutationEvent.Single(pos, MutateType.UPDATE))
                    }
                }
            }
        }
    }

    fun <T : BlockComponent> readBlock(pos: LayeredBlockPosition, clazz: Class<T>): T? {
        entryMapLock.read {
            val entry = entries[pos.toSubChunk()] ?: throw IllegalArgumentException("chunk ${pos.toSubChunk().toChunk()} is not loaded!")
            entry.lock.read {
                val (x, y, z) = pos.subchunkOffsets
                val type = registry.typeFor(entry.layers[pos.layer.toInt()].getBlock(x, y, z).toShort())
                if (clazz == BlockTypeComponent::class.java) {
                    return type as T?
                } else {
                    if (entry.overrides.contains(pos) && entry.overrides[pos]!!.contains(clazz)) {
                        return entry.overrides[pos]!![clazz] as T?
                    } else {
                        return registry.defaultComponentOf(type, clazz)
                    }
                }
            }
        }
    }

    fun listBlock(pos: LayeredBlockPosition): Collection<BlockComponent> {
        entryMapLock.read {
            val entry = entries[pos.toSubChunk()] ?: throw IllegalArgumentException("chunk ${pos.toSubChunk().toChunk()} is not loaded!")
            entry.lock.read {
                val rid: Short
                try {
                    val (x, y, z) = pos.subchunkOffsets
                    rid = entry.layers[pos.layer.toInt()].getBlock(x, y, z).toShort()
                } catch (ex: ArrayIndexOutOfBoundsException) {
                    throw IllegalArgumentException("layer ${pos.layer} does not exists")
                }
                val type = registry.typeFor(rid)
                return computeComponentMap(type, entry.overrides[pos] ?: emptyMap()).values
            }
        }
    }

    fun load(pos: ChunkPosition, serial: SerialChunk) {
        val subchunks = mutableListOf<SubChunkPosition>()
        val entries = serial.subChunks.mapIndexed { idx, subChunk ->
            val y = idx + serial.subChunksInitialY / SUBCHUNK_SIZE
            val pos = SubChunkPosition(pos.x, y, pos.z, pos.dim)
            val layers = subChunk.layers.map { it.storage }.toMutableList()
            val overrides = mutableMapOf<LayeredBlockPosition, MutableComponentMap<BlockComponent?>>()
            subChunk.layers.forEach { layer -> overrides.putAll(layer.overrides.mapValues { it.value.toMutableMap() }) }

            subchunks.add(pos)
            pos to Entry(
                ReentrantReadWriteLock(),
                layers = layers,
                overrides = overrides
            )
        }
        entryMapLock.write {
            this.entries.putAll(entries)
        }
        this.chunkSubChunkMap[pos] = subchunks
    }

    fun unload(pos: ChunkPosition): Pair<Int, List<List<SerialSubChunkLayer>>> {
        val subchunks = chunkSubChunkMap.remove(pos) ?: throw IllegalArgumentException("chunk $pos is not loaded!")
        val minY = subchunks.minOf { it.y }
        val unloadedEntries = entryMapLock.write {
            val unloaded = mutableListOf<Entry>()
            subchunks.forEach {
                unloaded.add(this.entries.remove(it)!!)
            }
            unloaded
        }
        val ret = unloadedEntries.map {
            val layers = it.layers.mapIndexed { index, storage ->
                val overrides = it.overrides.filter { it.key.layer == index.toShort() }
                SerialSubChunkLayer(storage, overrides)
            }
            layers
        }
        return minY to ret
    }

    fun serialize(pos: ChunkPosition): Pair<Int, List<List<SerialSubChunkLayer>>> {
        val subchunks = chunkSubChunkMap.get(pos) ?: throw IllegalArgumentException("chunk $pos is not loaded!")
        val minY = subchunks.minOf { it.y }
        val unloadedEntries = entryMapLock.read {
            val unloaded = mutableListOf<Entry>()
            subchunks.forEach {
                unloaded.add(this.entries.get(it)!!)
            }
            unloaded
        }
        val ret = unloadedEntries.map {
            val layers = it.layers.mapIndexed { index, storage ->
                val overrides = it.overrides.filter { it.key.layer == index.toShort() }
                SerialSubChunkLayer(storage, overrides)
            }
            layers
        }
        return minY to ret
    }

    private fun computeComponentMap(
        type: BlockTypeComponent,
        overrides: ComponentMap<BlockComponent?>
    ): MutableComponentMap<BlockComponent> {
        val components = registry.defaultComponentsOf(type).toMutableMap()
        overrides.forEach { (key, value) ->
            if (value == null) {
                components.remove(key)
            } else {
                components[key] = value
            }
        }
        components[type.javaClass] = type
        return components
    }
}
