package com.github.bedrockecs.comm.game.action

import com.github.bedrockecs.comm.server.ConnectionIdentifiers

/**
 * represents a player has joined the server
 */
data class PlayerConnectedAction(
    val identifiers: ConnectionIdentifiers
) : Action
