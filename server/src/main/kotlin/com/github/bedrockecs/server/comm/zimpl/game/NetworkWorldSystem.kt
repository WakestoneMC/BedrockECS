package com.github.bedrockecs.server.comm.zimpl.game

import com.github.bedrockecs.server.comm.zimpl.exchange.GameWorldExchange
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.EntityScanConfig
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.server.game.db.world.data.BlockTypeComponent
import com.github.bedrockecs.server.game.db.world.event.BlockMutationEvent
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.system.System
import com.github.bedrockecs.vanilla.player.entity.PlayerEntityType
import com.github.bedrockecs.vanilla.player.entity.PlayerIdentifierComponent
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.ConcurrentSkipListSet

@Component
class NetworkWorldSystem(
    private val db: GameDB,
    private val eventBus: EventBus,
    private val exchange: GameWorldExchange
) : System {

    override val tickOrder: Int
        get() = Int.MAX_VALUE

    private val players = mutableMapOf<UUID, EntityID>()

    private val chunkChanges = ConcurrentSkipListSet<ChunkPosition>()

    init {
        eventBus.listensFor(
            EventBus.ListenConfig(
                name = "network.chunk-change-detection",
                eventType = BlockMutationEvent::class.java,
                dispatchToken = BlockTypeComponent::class.java,
                order = 0
            )
        ) {
            when (it) {
                is BlockMutationEvent.Batched -> chunkChanges.add(it.pos.toChunk())
                is BlockMutationEvent.Single -> chunkChanges.add(it.pos.toSubChunk().toChunk())
            }
        }
    }

    override fun tick() {
        val playerPositions = mutableMapOf<UUID, FloatBlockPosition>()
        db.entities.scan(
            EntityScanConfig(),
            arrayOf(EntityTypeComponent::class.java, PlayerIdentifierComponent::class.java, EntityPositionComponent::class.java)
        ) { eid, ec ->
            val type = ec[0] as EntityTypeComponent
            val pid = ec[1] as PlayerIdentifierComponent
            val epc = ec[2] as EntityPositionComponent
            if (type == PlayerEntityType.TYPE) {
                if (!players.contains(pid.uuid)) {
                    players[pid.uuid] = eid
                }
            }
            playerPositions[pid.uuid] = epc.pos
        }

        val changes = chunkChanges.toSet()
        chunkChanges.clear()
        exchange.checkWorldUpdate(db, changes, playerPositions)
    }
}