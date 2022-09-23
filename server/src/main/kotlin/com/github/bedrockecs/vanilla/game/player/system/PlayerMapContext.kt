package com.github.bedrockecs.vanilla.game.player.system

import com.github.bedrockecs.server.comm.server.ConnectionIdentifiers
import com.github.bedrockecs.server.game.db.entity.EntityID
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

/**
 * player map context, provides the UUID/displayName <-> EntityID lookup.
 */
@Component
class PlayerMapContext {

    private val uuid2entity = ConcurrentHashMap<UUID, EntityID>()

    private val displayName2entity = ConcurrentHashMap<String, EntityID>()

    // accessors //

    fun findPlayerByUUID(uuid: UUID): EntityID? {
        return uuid2entity[uuid]
    }

    fun findPlayerByDisplayName(name: String): EntityID? {
        return displayName2entity[name]
    }

    // listeners //

    fun onPlayerConnected(identifiers: ConnectionIdentifiers, entityID: EntityID) {
        uuid2entity[identifiers.playerUUID!!] = entityID
        displayName2entity[identifiers.displayName!!] = entityID
    }

    fun onPlayerDisconnected(identifiers: ConnectionIdentifiers) {
        uuid2entity.remove(identifiers.playerUUID!!)
        displayName2entity.remove(identifiers.displayName!!)
    }
}
