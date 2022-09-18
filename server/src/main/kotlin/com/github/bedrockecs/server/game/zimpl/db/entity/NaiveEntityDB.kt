package com.github.bedrockecs.server.game.zimpl.db.entity

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.github.bedrockecs.server.game.db.common.LoadType
import com.github.bedrockecs.server.game.db.common.MutateType
import com.github.bedrockecs.server.game.db.entity.EntityDB
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityComponent
import com.github.bedrockecs.server.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.server.game.db.entity.event.EntityCreatingEvent
import com.github.bedrockecs.server.game.db.entity.event.EntityLoadingEvent
import com.github.bedrockecs.server.game.db.entity.event.EntityMutationEvent
import com.github.bedrockecs.server.game.eventbus.EventBus
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class NaiveEntityDB(
    eventBus: EventBus
) : EntityDB {

    private val creatingEvent = eventBus.publishFor(
        EventBus.PublishConfig(
            eventType = EntityCreatingEvent::class.java,
            name = "entitydb"
        )
    )

    private val mutationEvent = eventBus.publishFor(
        EventBus.PublishConfig(
            eventType = EntityMutationEvent::class.java,
            name = "entitydb"
        )
    )

    private val loadingEvent = eventBus.publishFor(
        EventBus.PublishConfig(
            eventType = EntityLoadingEvent::class.java,
            name = "entitydb"
        )
    )

    private val idMapLock = ReentrantLock()

    private var id: Int = 0

    private val idMap: MutableMap<Int, Entity> = mutableMapOf()

    private val engine: Engine = Engine()

    override fun create(
        type: EntityTypeComponent,
        extras: MutableSet<EntityComponent>
    ): EntityID {
        val aid = idMapLock.withLock {
            id++
        }
        val entityID = EntityID(aid)

        creatingEvent.publish(type.entityType, EntityCreatingEvent(entityID, type, extras))

        val entity = engine.createEntity()
        entity.add(type)
        extras.forEach { entity.add(it) }

        idMapLock.withLock {
            idMap[aid] = entity
        }

        entity.components.forEach {
            val c = it as EntityComponent
            mutationEvent.publish(c.type, EntityMutationEvent(entityID, MutateType.ADD))
        }

        return entityID
    }

    override fun destroy(id: EntityID) {
        val entity = idMapLock.withLock {
            idMap[id.value]
        }
        if (entity != null) {
            entity.components.forEach {
                val c = it as EntityComponent
                mutationEvent.publish(c.type, EntityMutationEvent(id, MutateType.REMOVE))
            }
            engine.removeEntity(entity)
        }
    }

    override fun <T : EntityComponent> mutate(id: EntityID, clazz: Class<T>, func: (T?) -> T?) {
        val entity = idMapLock.withLock { idMap[id.value]!! }

        val comp: T? = entity.getComponent(clazz)
        val ret = func(comp)
        if (ret == null && comp == null) {
            // no-op
        } else if (ret == null && comp != null) {
            mutationEvent.publish(comp.type, EntityMutationEvent(id, MutateType.REMOVE))
            entity.remove(clazz)
        } else if (ret != null && comp == null) {
            entity.add(ret)
            mutationEvent.publish(ret.type, EntityMutationEvent(id, MutateType.ADD))
        } else /* both not null */ {
            entity.remove(clazz)
            entity.add(ret)
            mutationEvent.publish(ret!!.type, EntityMutationEvent(id, MutateType.UPDATE))
        }
    }

    override fun <T : EntityComponent> read(id: EntityID, clazz: Class<T>): T? {
        val entity = idMapLock.withLock { idMap[id.value] }
        return entity?.getComponent(clazz)
    }

    override fun list(id: EntityID): Collection<EntityComponent> {
        val entity = idMapLock.withLock { idMap[id.value]!! }
        return entity.components.toArray() as Collection<EntityComponent>
    }

    fun load(id: EntityID, components: Set<EntityComponent>) {
        val entity = engine.createEntity()
        components.forEach { entity.add(it) }
        idMapLock.withLock {
            // advance till this id is not going to be allocated
            while (this.id < id.value + 1) {
                this.id++
            }
            idMap[id.value] = entity
        }
        val type = entity.getComponent(EntityTypeComponent::class.java)
        loadingEvent.publish(type.entityType, EntityLoadingEvent(id, LoadType.LOAD))
    }

    fun unload(id: EntityID): Pair<EntityID, Set<EntityComponent>> {
        val entity = idMapLock.withLock {
            idMap[id.value]!!
        }

        val type = entity.getComponent(EntityTypeComponent::class.java)
        loadingEvent.publish(type.entityType, EntityLoadingEvent(id, LoadType.UNLOAD))

        idMapLock.withLock {
            idMap.remove(id.value)
        }
        return id to (entity.components.toSet() as Set<EntityComponent>)
    }
}
