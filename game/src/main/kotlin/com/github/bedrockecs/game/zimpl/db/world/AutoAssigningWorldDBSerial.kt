package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.db.world.WorldDBSerial
import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class AutoAssigningWorldDBSerial(
    override val airBlockType: BlockTypeComponent
) : WorldDBSerial {

    private val lock = ReentrantLock()

    private var id: Short = 1

    private val typeIdMap = mutableMapOf(airBlockType to 0.toShort())

    private val idTypeMap = mutableMapOf(0.toShort() to airBlockType)

    override fun blockTypeFor(id: Short): BlockTypeComponent {
        return lock.withLock {
            idTypeMap[id] ?: throw IllegalArgumentException("no block type found for id $id!")
        }
    }

    override fun idFor(type: BlockTypeComponent): Short {
        return lock.withLock {
            if (!typeIdMap.containsKey(type)) {
                val id = this.id++
                typeIdMap[type] = id
                idTypeMap[id] = type
            }
            typeIdMap[type]!!
        }
    }
}
