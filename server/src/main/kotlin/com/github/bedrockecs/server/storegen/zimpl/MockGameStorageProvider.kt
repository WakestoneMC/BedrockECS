package com.github.bedrockecs.server.storegen.zimpl

import com.github.bedrockecs.server.game.db.GameDBInitializer
import com.github.bedrockecs.server.game.db.GameStorageContext
import com.github.bedrockecs.server.game.db.GameStorageProvider
import com.github.bedrockecs.server.game.db.dimension.DimensionChangeListener
import com.github.bedrockecs.server.game.db.dimension.data.DimensionComponent
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.EntityIDAllocator
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class MockGameStorageProvider : GameStorageProvider {
    class Allocator : EntityIDAllocator {
        private val lock = ReentrantLock()

        private var counter: Int = 0

        override fun allocateID(): List<EntityID> {
            lock.withLock {
                val value = counter
                counter += 1024
                return (value..value + 1024).map { EntityID(it) }
            }
        }

        override fun releaseID(ids: List<EntityID>) {
            // no-op
        }
    }

    class Initializer : GameDBInitializer {
        override fun initialLoad(): GameDBInitializer.InitialLoad {
            return GameDBInitializer.InitialLoad(
                dimensions = mapOf((0.toShort()) to emptySet())
            )
        }
    }

    class ChangeListener : DimensionChangeListener {
        override fun onCreatingDimension(id: Short, components: Set<DimensionComponent>) {
            // no-op
        }

        override fun onUpdatingDimensionComponent(id: Short, from: DimensionComponent?, to: DimensionComponent?) {
            // no-op
        }

        override fun onDestroyedDimension(id: Short, components: Set<DimensionComponent>) {
            // no-op
        }
    }

    override val initializer: GameDBInitializer = Initializer()

    override val changeListener: DimensionChangeListener = ChangeListener()

    override val allocator: EntityIDAllocator = Allocator()

    override val context: GameStorageContext = MockGameStorageContext()
}
