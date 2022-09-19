package com.github.bedrockecs.server.comm.game.action

import com.github.bedrockecs.server.comm.server.ConnectionIdentifiers

/**
 * represents a player has joined the server
 */
data class PlayerConnectedAction(
    val identifiers: ConnectionIdentifiers
) : Action
