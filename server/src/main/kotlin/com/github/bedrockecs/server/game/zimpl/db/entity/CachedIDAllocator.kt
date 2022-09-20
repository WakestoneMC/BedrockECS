package com.github.bedrockecs.server.game.zimpl.db.entity

import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.EntityIDAllocator
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Semaphore

class CachedIDAllocator(private val allocator: EntityIDAllocator) {

    private val targetPoolSize: Int = 1024

    private val fetchFreeLimiter: Semaphore = Semaphore(1)

    private val idQueue = ConcurrentLinkedQueue<EntityID>()

    fun allocateID(): EntityID {
        while (true) {
            val id = idQueue.poll()
            if (id == null) {
                fetchFromAllocator()
            } else {
                return id
            }
        }
    }

    fun releaseID(eid: EntityID) {
        idQueue.add(eid)
        if (idQueue.size >= targetPoolSize * 1.5) {
            tryFreeToAllocator()
        }
    }

    private fun fetchFromAllocator() {
        fetchFreeLimiter.acquire()
        try {
            if (idQueue.isEmpty()) {
                val ids = allocator.allocateID()
                idQueue.addAll(ids)
            }
        } finally {
            fetchFreeLimiter.release()
        }
    }

    private fun tryFreeToAllocator() {
        val available = fetchFreeLimiter.tryAcquire()
        if (!available) {
            return
        }
        try {
            if (idQueue.size > targetPoolSize) {
                val toFree = ArrayList<EntityID>(targetPoolSize)
                for (i in 0 until targetPoolSize) {
                    val elem = idQueue.poll()
                    if (elem == null) {
                        break
                    } else {
                        toFree.add(elem)
                    }
                }
                if (toFree.isNotEmpty()) {
                    allocator.releaseID(toFree)
                }
            }
        } finally {
            fetchFreeLimiter.release()
        }
    }

    fun releaseCached() {
        fetchFreeLimiter.acquire()
        try {
            val ids = ArrayList<EntityID>(idQueue.size)
            while (true) {
                val elem = idQueue.poll()
                if (elem == null) {
                    break
                } else {
                    ids.add(elem)
                }
            }
            allocator.releaseID(ids)
        } finally {
            fetchFreeLimiter.release()
        }
    }
}
