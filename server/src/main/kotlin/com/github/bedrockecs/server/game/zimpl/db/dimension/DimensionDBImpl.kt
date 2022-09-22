package com.github.bedrockecs.server.game.zimpl.db.dimension

import com.github.bedrockecs.server.game.db.common.LifecycleType
import com.github.bedrockecs.server.game.db.common.MutableComponentMap
import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.dimension.DimensionDB
import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent
import com.github.bedrockecs.server.game.db.dimension.event.DimensionLifecycleEvent
import com.github.bedrockecs.server.game.db.dimension.event.DimensionMutationEvent
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.eventbus.publishFor
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

typealias PreCreateCallback = (eid: Short, components: Set<DimensionComponent>) -> Unit

typealias PreUpdateCallback = (eid: Short, from: DimensionComponent?, to: DimensionComponent?) -> Unit

typealias PostDestroyCallback = (eid: Short, components: Set<DimensionComponent>) -> Unit

class DimensionDBImpl(
    evb: EventBus,
    private val preCreate: PreCreateCallback = { p0, p1 -> },
    private val preUpdate: PreUpdateCallback = { a0, a1, a2 -> },
    private val postDestroy: PostDestroyCallback = { a0, a1 -> }
) : DimensionDB, AutoCloseable {

    private val lifecyclePub = evb.publishFor<DimensionLifecycleEvent>("dimension-db-lifecycle")

    private val mutatePub = evb.publishFor<DimensionMutationEvent>("dimension-db-mutation")

    data class DimensionEntry(
        val lock: ReentrantReadWriteLock = ReentrantReadWriteLock(),
        val map: MutableComponentMap<DimensionComponent> = mutableMapOf()
    )

    private val dimensionsLock = ReentrantReadWriteLock()

    private val dimensions: MutableList<DimensionEntry?> = mutableListOf()

    /**
     * note: we assume dimensionsLock is already taken!
     */
    private fun getDimension(id: Short): DimensionEntry {
        val entry = if (id >= dimensions.size) {
            null
        } else {
            dimensions[id.toInt()]
        }
        if (entry == null) {
            throw IllegalArgumentException("dimension $id does not exists!")
        }
        return entry
    }

    override fun <T : DimensionComponent> read(id: Short, clazz: Class<T>): T? {
        dimensionsLock.read {
            val dimension = getDimension(id)
            dimension.lock.read {
                return dimension.map[clazz]?.clone() as T?
            }
        }
    }

    override fun list(id: Short): Collection<DimensionComponent> {
        dimensionsLock.read {
            val entry = getDimension(id)
            entry.lock.read {
                return entry.map.values
            }
        }
    }

    fun initialize(dimComponents: Map<Short, Set<DimensionComponent>>) {
        dimensionsLock.write {
            val maxId = dimComponents.maxOf { it.key }
            (0..maxId).forEach { dimensions.add(null) }
            dimComponents.forEach { (did, components) ->
                val componentMap = components.associateBy({ it::class.java }, { it }).toMutableMap()
                dimensions[did.toInt()] = DimensionEntry(map = componentMap)
            }
        }
    }

    override fun create(components: Set<DimensionComponent>): Short {
        val componentMap = components.associateBy({ it::class.java }, { it }).toMutableMap()
        val dimensionID = dimensionsLock.write {
            var targetIdx = dimensions.indexOfFirst { it == null }
            if (targetIdx == -1) {
                targetIdx = dimensions.size
                dimensions.add(null)
            }

            preCreate(targetIdx.toShort(), components)

            dimensions[targetIdx] = DimensionEntry(map = componentMap)

            targetIdx.toShort()
        }
        lifecyclePub.publish(null, DimensionLifecycleEvent(dimensionID, LifecycleType.CREATE))
        components.forEach {
            mutatePub.publish(it.type, DimensionMutationEvent(dimensionID, MutateType.ADD))
        }
        return dimensionID
    }

    override fun <T : DimensionComponent> mutate(pos: Short, clazz: Class<T>, func: (T?) -> T?) {
        val oldValue: T?
        val newValue: T?
        dimensionsLock.read {
            val entry = getDimension(pos)
            entry.lock.write {
                oldValue = entry.map[clazz] as T?
                newValue = func(oldValue)

                preUpdate(pos, oldValue, newValue)

                // TODO: move this out of the lock as this could be very slow
                if (oldValue != null && newValue == null) {
                    mutatePub.publish(oldValue.type, DimensionMutationEvent(pos, MutateType.REMOVE))
                }

                if (newValue == null) {
                    entry.map.remove(clazz)
                } else {
                    entry.map[clazz] = newValue
                }
            }
        }
        if (oldValue == null && newValue != null) {
            mutatePub.publish(newValue.type, DimensionMutationEvent(pos, MutateType.ADD))
        } else if (oldValue != null && newValue != null) {
            mutatePub.publish(newValue.type, DimensionMutationEvent(pos, MutateType.UPDATE))
        }
    }

    override fun destroy(pos: Short) {
        var executed = false
        val components: Set<DimensionComponent>
        dimensionsLock.write {
            if (0 <= pos && pos < dimensions.size) {
                val entry = dimensions[pos.toInt()]
                if (entry != null) {
                    executed = true
                    components = entry.map.values.toSet()

                    // TODO: move this out of the lock as this could be very slow
                    components.forEach {
                        mutatePub.publish(it.type, DimensionMutationEvent(pos, MutateType.REMOVE))
                    }

                    dimensions[pos.toInt()] = null
                    try {
                        postDestroy(pos, components)
                    } catch (ex: Throwable) {
                        dimensions[pos.toInt()] = entry
                        throw ex
                    }
                }
            }
        }
        if (executed) {
            lifecyclePub.publish(null, DimensionLifecycleEvent(pos, LifecycleType.DESTROY))
        }
    }

    override fun close() {
        lifecyclePub.close()
    }
}
