package com.github.bedrockecs.server.comm.zimpl.game

import com.github.bedrockecs.server.comm.zimpl.exchange.PlayerConnectionExchange
import com.github.bedrockecs.server.comm.zimpl.exchange.PlayerConnectionExchange.SpawnedPlayer
import com.github.bedrockecs.server.game.data.FloatBlockPosition
import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.db.entity.EntityID
import com.github.bedrockecs.server.game.db.entity.EntityScanConfig
import com.github.bedrockecs.server.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.server.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.system.System
import com.github.bedrockecs.vanilla.player.entity.PlayerEntityType
import com.github.bedrockecs.vanilla.player.entity.PlayerIdentifierComponent
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class NetworkPlayerConnectionSystem(
    private val db: GameDB,
    private val eventBus: EventBus,
    private val exchange: PlayerConnectionExchange
) : System {

    override val tickOrder: Int
        get() = Int.MAX_VALUE

    private val players = mutableMapOf<UUID, EntityID>()

    override fun tick() {
        val playerPositions = mutableMapOf<UUID, FloatBlockPosition>()
        val resolvedPendingPlayers = mutableMapOf<UUID, SpawnedPlayer>()
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
                    resolvedPendingPlayers.put(pid.uuid, SpawnedPlayer(eid, epc))
                }
            }
            playerPositions[pid.uuid] = epc.pos
        }
        exchange.resolvePendingPlayers(resolvedPendingPlayers)
    }
}
