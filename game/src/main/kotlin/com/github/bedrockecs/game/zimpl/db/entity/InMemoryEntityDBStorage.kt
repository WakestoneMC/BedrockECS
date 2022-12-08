package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.entity.EntityDBStorage
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.game.db.entity.data.PlayerIdentifiersComponent
import com.github.bedrockecs.game.db.entity.serial.SerialEntity
import java.lang.IllegalArgumentException
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class InMemoryEntityDBStorage : EntityDBStorage {

    private val idAllocator = AtomicInteger(0)

    private val entityMap = ConcurrentHashMap<EntityID, SerialEntity>()

    init {
        val globalSerial = SerialEntity(EntityID.GLOBAL, emptyMap())
        entityMap[globalSerial.id] = globalSerial
    }

    override fun allocateEntity(): CompletableFuture<EntityID> {
        val id = idAllocator.incrementAndGet()
        val serial = SerialEntity(EntityID(id), emptyMap())
        entityMap[serial.id] = serial
        return CompletableFuture.completedFuture(EntityID(id))
    }

    override fun writeEntity(entity: SerialEntity): CompletableFuture<Void> {
        if (!entityMap.containsKey(entity.id)) {
            throw IllegalArgumentException("entity ${entity.id} does not exist!")
        }
        entityMap[entity.id] = entity
        return CompletableFuture.completedFuture(null)
    }

    override fun readEntity(id: EntityID): CompletableFuture<SerialEntity?> {
        val ret = entityMap[id]
        return CompletableFuture.completedFuture(ret)
    }

    override fun removeEntity(id: EntityID): CompletableFuture<Void> {
        entityMap.remove(id)
        return CompletableFuture.completedFuture(null)
    }

    override fun listEntitiesInChunk(pos: ChunkPosition): CompletableFuture<Set<EntityID>> {
        val ret = entityMap
            .values
            .filter {
                val posComponent = it.components[EntityPositionComponent::class.java] as EntityPositionComponent?
                posComponent != null && posComponent.pos.toChunk() == pos
            }
            .map { it.id }
        return CompletableFuture.completedFuture(ret.toSet())
    }

    override fun findEntityByPlayerName(name: String): CompletableFuture<EntityID?> {
        val ret = entityMap
            .values
            .filter {
                val component = it.components[PlayerIdentifiersComponent::class.java] as PlayerIdentifiersComponent?
                component != null && component.name == name
            }
            .map { it.id }
            .firstOrNull()
        return CompletableFuture.completedFuture(ret)
    }

    override fun findEntityByPlayerUUID(uuid: UUID): CompletableFuture<EntityID?> {
        val ret = entityMap
            .values
            .filter {
                val component = it.components[PlayerIdentifiersComponent::class.java] as PlayerIdentifiersComponent?
                component != null && component.uuid == uuid
            }
            .map { it.id }
            .firstOrNull()
        return CompletableFuture.completedFuture(ret)
    }
}
