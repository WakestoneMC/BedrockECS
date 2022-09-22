package com.github.bedrockecs.server.game.zimpl.db.world

import com.github.bedrockecs.server.common.palette.PalettedStorage
import com.github.bedrockecs.server.game.data.BlockConstants.SUBCHUNK_SIZE
import com.github.bedrockecs.server.game.data.LayeredBlockPosition
import com.github.bedrockecs.server.game.data.LayeredSubChunkPosition
import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.common.MutableComponentMap
import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.world.data.BlockComponent
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.server.game.db.world.event.BlockMutationEvent
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunkLayer
import com.github.bedrockecs.server.game.eventbus.Publisher
import com.github.bedrockecs.server.game.registry.BlockRegistry
import com.github.bedrockecs.vanilla.blocks.world.AirBlockType
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class NaiveSubChunkTablet(
    private val pos: LayeredSubChunkPosition,
    private val registry: BlockRegistry,
    private val mutationEvent: Publisher<BlockMutationEvent.Single>,
    private val typeState: Array<BlockTypeComponent>,
    private val overrides: Array<MutableComponentMap<BlockComponent?>>
) {

    companion object {
        private const val SUBCHUNK_BLOCK_COUNT = SUBCHUNK_SIZE * SUBCHUNK_SIZE * SUBCHUNK_SIZE

        fun tabletIndexOf(pos: LayeredBlockPosition): Int {
            val (x, y, z) = pos.subchunkOffsets
            return x + (y * SUBCHUNK_SIZE) + (z * SUBCHUNK_SIZE * SUBCHUNK_SIZE)
        }

        fun positionOf(pos: LayeredSubChunkPosition, idx: Int): LayeredBlockPosition {
            val (x, y, z) = offsetOf(idx)
            return LayeredBlockPosition(
                pos.x * SUBCHUNK_SIZE + x,
                pos.y * SUBCHUNK_SIZE + y,
                pos.z * SUBCHUNK_SIZE + z,
                pos.dim,
                pos.layer
            )
        }

        fun offsetOf(idx: Int): Triple<Int, Int, Int> {
            val x = idx % SUBCHUNK_SIZE
            val y = idx / SUBCHUNK_SIZE % SUBCHUNK_SIZE
            val z = idx / (SUBCHUNK_SIZE * SUBCHUNK_SIZE) % SUBCHUNK_SIZE
            return Triple(x, y, z)
        }

        fun fromOffset(t3: Triple<Int, Int, Int>): Int {
            val (x, y, z) = t3
            return x + (y * SUBCHUNK_SIZE) + (z * SUBCHUNK_SIZE * SUBCHUNK_SIZE)
        }

        fun deserialize(
            registry: BlockRegistry,
            mutationEvent: Publisher<BlockMutationEvent.Single>,
            pos: LayeredSubChunkPosition,
            serial: SerialSubChunkLayer
        ): NaiveSubChunkTablet {
            val typeStates = Array<BlockTypeComponent>(4096) { AirBlockType.allInstances[0] }
            typeStates.mapIndexed { index, _ ->
                val idx = serial.storage.getBlock(index)
                registry.typeFor(idx.toShort())
            }

            val overrides = buildList {
                for (idx in 0 until SUBCHUNK_BLOCK_COUNT) {
                    val ord = serial.overrides[positionOf(pos, idx)]
                    if (ord == null) {
                        add(mutableMapOf())
                    } else {
                        add(ord.toMutableMap())
                    }
                }
            }

            return NaiveSubChunkTablet(pos, registry, mutationEvent, typeStates, overrides.toTypedArray())
        }
    }

    private val lock = ReentrantLock()

    fun place(
        pos: LayeredBlockPosition,
        type: BlockTypeComponent,
        extras: Map<Class<out BlockComponent>, BlockComponent?>
    ) {
        lock.withLock {
            val index = tabletIndexOf(pos)
            val newTypeState = type
            val newOverrides = extras.toMutableMap()

            val oldTypeState = typeState[index]
            val oldOverrides = overrides[index]

            val executeChange = {
                typeState[index] = newTypeState
                overrides[index] = newOverrides
            }

            emitBlockMutationEvents(newTypeState, newOverrides, oldTypeState, oldOverrides, pos, executeChange)
        }
    }

    fun <T : BlockComponent> mutate(pos: LayeredBlockPosition, clazz: Class<T>, func: (T?) -> T?) {
        lock.withLock {
            if (clazz == BlockTypeComponent::class.java) {
                val index = tabletIndexOf(pos)

                val oldTypeState = typeState[index]
                val oldOverrides = overrides[index]

                val newType = func(oldTypeState as T) as BlockTypeComponent
                assert(newType.blockType == oldTypeState.blockType) { "cannot change blockType in mutate, use place for it!" }
                val newOverrides = overrides[index]

                val executeChange = {
                    typeState[index] = newType
                    overrides[index] = newOverrides
                }

                emitBlockMutationEvents(newType, newOverrides, oldTypeState, oldOverrides, pos, executeChange)
            } else {
                val index = tabletIndexOf(pos)
                val oldMap = computeComponentMap(typeState[index], overrides[index])
                val oldValue = oldMap[clazz as Class<out BlockComponent>]

                val newValue = clazz.cast(func(oldValue as T?))

                if (newValue == null && oldValue != null) {
                    mutationEvent.publish(oldValue.type, BlockMutationEvent.Single(pos, MutateType.REMOVE))
                }

                overrides[index][clazz] = newValue

                if (newValue != null && oldValue == null) {
                    mutationEvent.publish(newValue.type, BlockMutationEvent.Single(pos, MutateType.ADD))
                } else if (newValue != null && oldValue != null) {
                    mutationEvent.publish(newValue.type, BlockMutationEvent.Single(pos, MutateType.UPDATE))
                }
            }
        }
    }

    fun <T : BlockComponent> read(pos: LayeredBlockPosition, clazz: Class<T>): T? {
        lock.withLock {
            val index = tabletIndexOf(pos)
            val oldMap = computeComponentMap(typeState[index], overrides[index])
            return oldMap[clazz as Class<out BlockComponent>]?.clone() as T?
        }
    }

    fun list(pos: LayeredBlockPosition): Collection<BlockComponent> {
        lock.withLock {
            val index = tabletIndexOf(pos)
            val oldMap = computeComponentMap(typeState[index], overrides[index])
            return oldMap.values
        }
    }

    fun serialize(): SerialSubChunkLayer {
        lock.withLock {
            val storage = PalettedStorage.createWithDefaultState(AirBlockType.allInstances[0].runtimeID.toInt())
            typeState.forEachIndexed { index, component ->
                val (x, y, z) = offsetOf(index)
                storage.setBlock(x, y, z, registry.runtimeIDFor(component)!!.toInt())
            }
            val serialOverrides = overrides
                .mapIndexedNotNull { idx, map ->
                    if (map.isEmpty()) {
                        null
                    } else {
                        Pair(positionOf(this.pos, idx), map.toMutableMap())
                    }
                }.toMap()
            return SerialSubChunkLayer(storage, serialOverrides)
        }
    }

    private fun emitBlockMutationEvents(
        newTypeState: BlockTypeComponent,
        newOverrides: MutableMap<Class<out BlockComponent>, BlockComponent?>,
        oldTypeState: BlockTypeComponent,
        oldOverrides: MutableMap<Class<out BlockComponent>, BlockComponent?>,
        pos: LayeredBlockPosition,
        executeChange: () -> Unit
    ) {
        val newMaps = computeComponentMap(newTypeState, newOverrides)
        val oldMaps = computeComponentMap(oldTypeState, oldOverrides)

        val addedComponents = newMaps.keys.subtract(oldMaps.keys)
        val changedComponents = newMaps.keys.intersect(oldMaps.keys)
        val removedComponents = oldMaps.keys.subtract(newMaps.keys)

        removedComponents.forEach {
            mutationEvent.publish(oldMaps[it]!!.type, BlockMutationEvent.Single(pos, MutateType.REMOVE))
        }

        executeChange()

        addedComponents.forEach {
            mutationEvent.publish(newMaps[it]!!.type, BlockMutationEvent.Single(pos, MutateType.ADD))
        }
        changedComponents.forEach {
            mutationEvent.publish(newMaps[it]!!.type, BlockMutationEvent.Single(pos, MutateType.UPDATE))
        }
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
