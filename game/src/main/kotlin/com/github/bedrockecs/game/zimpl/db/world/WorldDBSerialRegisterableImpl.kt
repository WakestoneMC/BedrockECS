package com.github.bedrockecs.game.zimpl.db.world

import com.github.bedrockecs.game.db.world.WorldDBSerialConfigurator
import com.github.bedrockecs.game.db.world.WorldDBSerialRegisterable
import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import java.util.concurrent.ConcurrentHashMap

class WorldDBSerialRegisterableImpl(configurators: List<WorldDBSerialConfigurator>) : WorldDBSerialRegisterable {

    init {
        configurators.forEach { it.configure(this) }
    }

    private var airBlockType0: BlockTypeComponent? = null

    private val idTypeMap = ConcurrentHashMap<Short, BlockTypeComponent>()

    private val typeIdMap = ConcurrentHashMap<BlockTypeComponent, Short>()

    override fun registerAirBlock(type: BlockTypeComponent) {
        if (airBlockType0 == null) {
            airBlockType0 = type
        } else {
            throw IllegalStateException("air block type has already been registered!")
        }
    }

    override fun register(type: BlockTypeComponent, id: Short) {
        assert(idTypeMap[id] == null)
        idTypeMap[id] = type
        typeIdMap[type] = id
    }

    override val airBlockType: BlockTypeComponent
        get() = airBlockType0!!

    override fun blockTypeFor(id: Short): BlockTypeComponent {
        return idTypeMap.get(id)!!
    }

    override fun idFor(type: BlockTypeComponent): Short {
        return typeIdMap.get(type)!!
    }
}
