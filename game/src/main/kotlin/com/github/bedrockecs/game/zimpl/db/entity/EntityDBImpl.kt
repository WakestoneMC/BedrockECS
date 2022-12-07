package com.github.bedrockecs.game.zimpl.db.entity

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.common.LifecycleType
import com.github.bedrockecs.game.db.common.LoadType
import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.entity.EntityDBStorage
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.EntityScanConfig
import com.github.bedrockecs.game.db.entity.data.EntityComponent
import com.github.bedrockecs.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.game.db.entity.data.PlayerIdentityComponent
import com.github.bedrockecs.game.db.entity.event.EntityCreatingEvent
import com.github.bedrockecs.game.db.entity.event.EntityLifecycleEvent
import com.github.bedrockecs.game.db.entity.event.EntityLoadingEvent
import com.github.bedrockecs.game.db.entity.event.EntityMutationEvent
import com.github.bedrockecs.game.db.entity.scan
import com.github.bedrockecs.game.db.entity.serial.SerialEntity
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.eventbus.publishFor
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

class EntityDBImpl(
    eventBus: EventBus,
    private val storage: EntityDBStorage
) : EntityDBInternal {

    private val creatingEvent = eventBus.publishFor<EntityCreatingEvent>("entitydb")

    private val lifecycleEvent = eventBus.publishFor<EntityLifecycleEvent>("entitydb")

    private val mutationEvent = eventBus.publishFor<EntityMutationEvent>("entitydb")

    private val loadingEvent = eventBus.publishFor<EntityLoadingEvent>("entitydb")

    /**
     * write on adding/removing entity
     * read on reading/modifying existing entity
     */
    private val entityCollectionLock = ReentrantReadWriteLock()

    private val idMap: MutableMap<Int, Entity> = mutableMapOf()

    private val idReverseMap: MutableMap<Entity, Int> = mutableMapOf()

    /**
     * lock component, used to perform locking on the entity:
     * write on adding/removing component
     * read on reading/modifying existing component
     */
    data class EntityLockComponent(
        val lock: ReentrantReadWriteLock = ReentrantReadWriteLock()
    ) : Component

    private val engine: Engine = Engine()

    override fun create(type: EntityTypeComponent, extras: Set<EntityComponent>): EntityID {
        if (extras.any { it is EntityTypeComponent }) {
            throw IllegalArgumentException("extras cannot contain EntityTypeComponent!")
        }

        val eid = storage.allocateEntity().join()

        val event = EntityCreatingEvent(eid, type, extras.toMutableSet())
        creatingEvent.publish(type.entityType, event)

        val entity = engine.createEntity()
        entity.add(EntityLockComponent())
        entity.add(type)
        event.extras.forEach { entity.add(it) }

        entityCollectionLock.write {
            engine.addEntity(entity)
            idMap[eid.value] = entity
            idReverseMap[entity] = eid.value
        }

        lifecycleEvent.publish(type.entityType, EntityLifecycleEvent(eid, LifecycleType.CREATE))

        event.extras.forEach {
            mutationEvent.publish(it.type, EntityMutationEvent(eid, MutateType.ADD))
        }

        return eid
    }

    override fun destroy(id: EntityID) {
        entityCollectionLock.write {
            val entity = idMap.get(id.value)
            if (entity != null) {
                // TODO: move event delivery out of the lock
                entity.components
                    .mapNotNull {
                        if (it is EntityLockComponent) {
                            null
                        } else {
                            it as EntityComponent
                        }
                    }
                    .forEach {
                        mutationEvent.publish(it.type, EntityMutationEvent(id, MutateType.REMOVE))
                    }
                val entityType = entity.getComponent(EntityTypeComponent::class.java).entityType
                lifecycleEvent.publish(entityType, EntityLifecycleEvent(id, LifecycleType.DESTROY))

                idMap.remove(id.value)
                idReverseMap.remove(entity)

                engine.removeEntity(entity)

                storage.removeEntity(id)
            }
        }
    }

    override fun <T : EntityComponent> mutate(id: EntityID, clazz: Class<T>, func: (T?) -> T?) {
        val oldValue: T?
        val newValue: T?
        entityCollectionLock.read {
            val entity = idMap[id.value]
            if (entity != null) {
                val lock = entity.getComponent(EntityLockComponent::class.java)!!.lock
                lock.write {
                    oldValue = entity.getComponent(clazz)
                    newValue = func(oldValue)

                    if (oldValue != null && newValue == null) {
                        mutationEvent.publish(oldValue.type, EntityMutationEvent(id, MutateType.REMOVE))
                    }

                    if (newValue == null) {
                        entity.remove(clazz)
                    } else {
                        entity.add(newValue)
                    }
                }
            } else {
                val serial = storage.readEntity(id).join() ?: throw IllegalArgumentException("entity $id does not exists!")
                oldValue = serial.components[clazz] as T?
                newValue = func(oldValue)
                if (oldValue != null && newValue == null) {
                    mutationEvent.publish(oldValue.type, EntityMutationEvent(id, MutateType.REMOVE))
                }

                val newComponents = if (newValue == null) {
                    serial.components - clazz
                } else {
                    serial.components + mapOf(clazz to newValue)
                }

                storage.writeEntity(SerialEntity(serial.id, newComponents))
            }
        }
        if (oldValue == null && newValue != null) {
            mutationEvent.publish(newValue.type, EntityMutationEvent(id, MutateType.ADD))
        } else if (oldValue != null && newValue != null) {
            mutationEvent.publish(newValue.type, EntityMutationEvent(id, MutateType.UPDATE))
        }
    }

    override fun <T : EntityComponent> read(id: EntityID, clazz: Class<T>): T? {
        val entity: Entity?
        entityCollectionLock.read {
            entity = idMap[id.value]
            if (entity != null) {
                val lock = entity.getComponent(EntityLockComponent::class.java)!!
                lock.lock.read {
                    return entity.getComponent(clazz)
                }
            }
        }
        val serial = storage.readEntity(id).join() ?: throw IllegalArgumentException("entity $id does not exists!")
        return serial.components[clazz] as T?
    }

    override fun list(id: EntityID): Collection<EntityComponent> {
        var entity: Entity? = null
        entityCollectionLock.read {
            entity = idMap[id.value]
            if (entity != null) {
                val lock = entity!!.getComponent(EntityLockComponent::class.java)!!
                lock.lock.read {
                    return entity!!.components.mapNotNull {
                        if (it is EntityLockComponent) {
                            null
                        } else {
                            it as EntityComponent
                        }
                    }
                }
            }
        }
        val serial = storage.readEntity(id).join()
        if (serial == null) {
            throw IllegalArgumentException("entity $id does not exists!")
        } else {
            return serial.components.values
        }
    }

    override fun scan(
        config: EntityScanConfig,
        components: Array<Class<out EntityComponent>>,
        callback: (EntityID, Array<EntityComponent>) -> Unit
    ) {
        val entities = engine.getEntitiesFor(Family.all(*components).get())
        entities.forEach { entity ->
            val eid = idReverseMap[entity]!!
            val lock = entity.getComponent(EntityLockComponent::class.java).lock
            lock.read {
                val arr = components.map { entity.getComponent(it) }.toTypedArray()
                callback(EntityID(eid), arr)
            }
        }
    }
    override fun mutatingScan(
        config: EntityScanConfig,
        components: Array<Class<out EntityComponent>>,
        callback: (EntityID, Array<EntityComponent>) -> Array<EntityComponent?>
    ) {
        entityCollectionLock.read {
            val entities = engine.getEntitiesFor(Family.all(*components).get()).toList()
            entities.forEach { entity ->
                val eid = idReverseMap[entity]!!
                val lock = entity.getComponent(EntityLockComponent::class.java).lock
                lock.write {
                    val arr = components.map { entity.getComponent(it) }.toTypedArray()
                    val ret = callback(EntityID(eid), arr)
                    for ((type, ret) in components.zip(ret)) {
                        assert(ret == null || type.isInstance(ret))
                        if (ret != null) {
                            entity.add(ret)
                        } else {
                            entity.remove(type)
                        }
                    }
                }
            }
        }
    }

    // loading

    override fun isLoaded(eid: EntityID): Boolean {
        return entityCollectionLock.read {
            idMap.contains(eid.value)
        }
    }

    override fun listLoadedEntities(): Collection<EntityID> {
        return entityCollectionLock.read {
            idMap.keys.map { EntityID(it) }.toSet()
        }
    }

    override fun loadEntity(eid: EntityID): CompletableFuture<Void> {
        val loaded = entityCollectionLock.read { idMap.containsKey(eid.value) }
        if (!loaded) {
            val serial = storage.readEntity(eid).join() ?: throw IllegalArgumentException("entity $eid does not exists!")
            load(serial)
        }
        return CompletableFuture.completedFuture(null)
    }

    override fun unloadEntity(eid: EntityID): CompletableFuture<Void> {
        val serial = unload(eid)
        if (serial != null) {
            storage.writeEntity(serial)
        }
        return CompletableFuture.completedFuture(null)
    }

    override fun listEntitiesInChunk(pos: ChunkPosition): CompletableFuture<Set<EntityID>> {
        val storage = storage.listEntitiesInChunk(pos).join()
        val live = entityCollectionLock.read {
            val poses = ConcurrentHashMap<EntityID, EntityID>()
            scan<EntityPositionComponent> { eid, posc ->
                if (posc.pos.toChunk() == pos) {
                    poses[eid] = eid
                }
            }
            poses.values
        }
        val ret = storage + live
        return CompletableFuture.completedFuture(ret)
    }

    override fun tick() {
        TODO("Not yet implemented")
    }

    private fun load(serial: SerialEntity) {
        entityCollectionLock.write {
            val contains = idMap.contains(serial.id.value)
            if (contains) {
                throw IllegalArgumentException("entity of id ${serial.id} is already loaded!")
            }

            val entity = engine.createEntity()
            entity.add(EntityLockComponent())
            serial.components.values.forEach { entity.add(it) }
            engine.addEntity(entity)

            idMap[serial.id.value] = entity
            idReverseMap[entity] = serial.id.value
        }
        val et = (serial.components[EntityTypeComponent::class.java] as EntityTypeComponent).entityType
        loadingEvent.publish(et, EntityLoadingEvent(serial.id, LoadType.LOAD))
    }

    private fun unload(eid: EntityID): SerialEntity? {
        entityCollectionLock.write {
            val entity = idMap[eid.value] ?: return null

            val et = entity.getComponent(EntityTypeComponent::class.java).entityType
            loadingEvent.publish(et, EntityLoadingEvent(eid, LoadType.UNLOAD))

            val returnComponents = entity.components
                .mapNotNull {
                    if (it is EntityLockComponent) {
                        null
                    } else {
                        it as EntityComponent
                    }
                }
                .associateBy({ it::class.java }, { it })

            idMap.remove(eid.value)
            idReverseMap.remove(entity)

            engine.removeEntity(entity)

            return SerialEntity(eid, returnComponents)
        }
    }

    override fun findEntityByPlayerName(name: String): EntityID? {
        val inMemoryVer = entityCollectionLock.read {
            engine.entities
                .filter {
                    val component = it.getComponent(PlayerIdentityComponent::class.java)
                    component != null && component.name == name
                }
                .map { idReverseMap[it]!! }
                .firstOrNull()
        }
        return if (inMemoryVer != null) {
            EntityID(inMemoryVer)
        } else {
            storage.findEntityByPlayerName(name).join()
        }
    }

    override fun findEntityByPlayerUUID(uuid: UUID): EntityID? {
        val inMemoryVer = entityCollectionLock.read {
            engine.entities
                .filter {
                    val component = it.getComponent(PlayerIdentityComponent::class.java)
                    component != null && component.uuid == uuid
                }
                .map { idReverseMap[it]!! }
                .firstOrNull()
        }
        return if (inMemoryVer != null) {
            EntityID(inMemoryVer)
        } else {
            storage.findEntityByPlayerUUID(uuid).join()
        }
    }
}
