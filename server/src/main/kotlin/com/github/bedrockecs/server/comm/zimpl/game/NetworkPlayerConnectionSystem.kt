package com.github.bedrockecs.server.comm.zimpl.game

import com.github.bedrockecs.server.comm.zimpl.exchange.PlayerConnectionExchange
import com.github.bedrockecs.server.comm.zimpl.exchange.PlayerConnectionExchange.StartGamePacketData
import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.dimension.data.DimensionConstants.OVERWORLD_ID
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.server.game.db.entity.scan
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.system.CommonTickOrders
import com.github.bedrockecs.server.game.system.System
import com.github.bedrockecs.server.game.tick.TickComponent
import com.github.bedrockecs.vanilla.player.entity.PlayerEntityType
import com.github.bedrockecs.vanilla.player.entity.PlayerIdentifierComponent
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * corresponding system for [PlayerConnectionExchange]
 */
@Component
class NetworkPlayerConnectionSystem(
    private val db: GameDB,
    private val eventBus: EventBus,
    private val exchange: PlayerConnectionExchange
) : System {

    override val tickOrder: Int
        get() = CommonTickOrders.NETWORK_STORAGE_OUTPUT

    private val players = mutableMapOf<UUID, EntityID>()

    override fun tick() {
        val tick = db.dimensions.read(OVERWORLD_ID, TickComponent::class.java)!!

        val playerPositions = mutableMapOf<UUID, FloatBlockPosition>()
        val createdPlayers = mutableMapOf<UUID, StartGamePacketData>()
        db.entities.scan<EntityTypeComponent, PlayerIdentifierComponent, EntityPositionComponent> { eid, type, pid, pos ->
            if (type == PlayerEntityType.TYPE) {
                if (!players.contains(pid.uuid)) {
                    players[pid.uuid] = eid
                    createdPlayers.put(pid.uuid, StartGamePacketData(eid, pos, tick.currentTick))
                }
            }
            playerPositions[pid.uuid] = pos.pos
        }
        exchange.notifyPlayerEntityCreated(createdPlayers)
    }
}
