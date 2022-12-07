package com.github.bedrockecs.comm.game.action

import com.github.bedrockecs.comm.server.ConnectionIdentifiers

/**
 * represents a player has left the server
 */
data class PlayerDisconnectedAction(
    val identifiers: ConnectionIdentifiers
) : Action
