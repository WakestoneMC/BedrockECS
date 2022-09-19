package com.github.bedrockecs.server.comm.game.action

import com.github.bedrockecs.server.comm.server.ConnectionIdentifiers

/**
 * represents a player has left the server
 */
data class PlayerDisconnectedAction(
    val identifiers: ConnectionIdentifiers
) : Action
