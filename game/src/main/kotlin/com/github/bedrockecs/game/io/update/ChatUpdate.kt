package com.github.bedrockecs.game.io.update

import java.util.UUID

data class ChatUpdate(
    val receiver: Receiver,
    val sender: String,
    val text: String
) : Update {
    sealed class Receiver {
        data class Players(val players: Set<UUID>) : Receiver()
        object Broadcast : Receiver()
    }
}
