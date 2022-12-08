package com.github.bedrockecs.comm.zimpl.game

import com.github.bedrockecs.comm.zimpl.exchange.PlayerConnectionExchange
import com.github.bedrockecs.game.data.FloatBlockPosition
import com.github.bedrockecs.game.db.common.LifecycleType
import com.github.bedrockecs.game.db.common.LoadType
import com.github.bedrockecs.game.db.entity.EntityDB
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.data.CommonEntityTypes
import com.github.bedrockecs.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.game.db.entity.data.PlayerIdentifiersComponent
import com.github.bedrockecs.game.db.entity.event.EntityLifecycleEvent
import com.github.bedrockecs.game.db.entity.event.EntityLoadingEvent
import com.github.bedrockecs.game.db.entity.scan
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.eventbus.listensFor
import com.github.bedrockecs.game.system.CommonTickOrders
import com.github.bedrockecs.game.system.ECSSystem
import com.github.bedrockecs.game.tick.TickComponent
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * corresponding system for [PlayerConnectionExchange]
 */
@Component
class NetworkPlayerConnectionSystem(
    private val db: EntityDB,
    private val eventBus: EventBus,
    private val exchange: PlayerConnectionExchange
) : ECSSystem {

    override val tickOrder: Int
        get() = CommonTickOrders.OUTPUT

    private val players = mutableMapOf<UUID, EntityID>()

    init {
        eventBus.listensFor<EntityLoadingEvent>("player-presence", CommonEntityTypes.PLAYER) {
            if (it.type == LoadType.UNLOAD) {
                val uuid = db.read(it.eid, PlayerIdentifiersComponent::class.java)!!.uuid
                players.remove(uuid)
            }
        }
        eventBus.listensFor<EntityLifecycleEvent>("player-presence", CommonEntityTypes.PLAYER) {
            if (it.type == LifecycleType.DESTROY) {
                val uuid = db.read(it.pos, PlayerIdentifiersComponent::class.java)!!.uuid
                players.remove(uuid)
            }
        }
    }

    override fun tick() {
        val tick = db.read(EntityID.GLOBAL, TickComponent::class.java)!!

        val playerPositions = mutableMapOf<UUID, FloatBlockPosition>()
        val createdPlayers = mutableMapOf<UUID, PlayerConnectionExchange.StartGamePacketData>()
        db.scan<EntityTypeComponent, PlayerIdentifiersComponent, EntityPositionComponent> { eid, type, pid, pos ->
            if (type == CommonEntityTypes.PLAYER) {
                if (!players.contains(pid.uuid)) {
                    players[pid.uuid] = eid
                    createdPlayers.put(
                        pid.uuid,
                        PlayerConnectionExchange.StartGamePacketData(eid, pos, tick.currentTick)
                    )
                }
            }
            playerPositions[pid.uuid] = pos.pos
        }
        exchange.notifyPlayerEntityCreated(createdPlayers)
    }
}
