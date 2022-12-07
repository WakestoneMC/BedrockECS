package com.github.bedrockecs.comm.zimpl.game

import com.github.bedrockecs.comm.zimpl.exchange.GameWorldExchange
import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.data.FloatBlockPosition
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.data.CommonEntityTypes
import com.github.bedrockecs.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.game.db.entity.data.PlayerIdentifiersComponent
import com.github.bedrockecs.game.db.entity.scan
import com.github.bedrockecs.game.db.invitem.InvItemDB
import com.github.bedrockecs.game.db.world.WorldDB
import com.github.bedrockecs.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.game.db.world.event.BlockMutationEvent
import com.github.bedrockecs.game.db.world.event.BlockTarget
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.eventbus.listensFor
import com.github.bedrockecs.game.system.CommonTickOrders
import com.github.bedrockecs.game.system.ECSSystem
import com.github.bedrockecs.game.tick.TickComponent
import com.github.bedrockecs.game.zimpl.db.entity.EntityDBInternal
import org.springframework.stereotype.Component
import java.util.Collections.synchronizedSet
import java.util.UUID

@Component
class NetworkWorldSystem(
    private val db: WorldDB,
    private val idb: InvItemDB,
    private val edb: EntityDBInternal,
    private val eventBus: EventBus,
    private val exchange: GameWorldExchange
) : ECSSystem {
    override val tickOrder: Int
        get() = CommonTickOrders.OUTPUT

    private val players = mutableMapOf<UUID, EntityID>()

    private val chunkChanges = synchronizedSet(HashSet<ChunkPosition>())

    init {
        eventBus.listensFor<BlockMutationEvent>("network.chunk-changes", BlockTypeComponent.type) { event ->
            val target = event.target
            when (target) {
                is BlockTarget.Batched -> chunkChanges.add(target.pos.toChunk())
                is BlockTarget.Single -> chunkChanges.add(target.pos.toSubChunk().toChunk())
            }
        }
    }

    override fun tick() {
        handleTickUpdate()
        handleInventoryUpdate()
        handlePlayerPositionUpdate()
        handleWorldUpdate()
    }

    private fun handleTickUpdate() {
        val ct = edb.read(EntityID.GLOBAL, TickComponent::class.java)!!.currentTick
        exchange.handleTickUpdate(ct)
    }

    private fun handleInventoryUpdate() {
        exchange.handleInventoryUpdate(idb, edb, emptySet())
    }

    private fun handlePlayerPositionUpdate() {
        val playerPositions = mutableMapOf<UUID, FloatBlockPosition>()
        edb.scan<EntityTypeComponent, PlayerIdentifiersComponent, EntityPositionComponent> { eid, type, pid, epc ->
            if (type == CommonEntityTypes.PLAYER) {
                if (!players.contains(pid.uuid)) {
                    players[pid.uuid] = eid
                }
            }
            playerPositions[pid.uuid] = epc.pos
        }
        exchange.handlePlayerPositionUpdate(playerPositions)
    }

    private fun handleWorldUpdate() {
        val changes = chunkChanges.toSet()
        chunkChanges.clear()

        exchange.handleWorldUpdate(db, changes)
    }
}
