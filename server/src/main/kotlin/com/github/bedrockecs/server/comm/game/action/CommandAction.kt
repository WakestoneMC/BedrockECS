package com.github.bedrockecs.server.comm.game.action

import java.util.UUID

/**
 * emitted whenever a command is invoked, either by player or by server
 */
data class CommandAction(
    val sender: Sender,
    val cmd: String
) : Action {
    sealed class Sender {
        data class Player(val uuid: UUID) : Sender()
        object Server : Sender()
    }
}
