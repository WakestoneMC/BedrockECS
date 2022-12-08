package com.github.bedrockecs.comm.game.update

import com.nukkitx.protocol.bedrock.packet.TextPacket
import java.util.UUID

data class ChatUpdate(
    val receiver: Receiver,
    val packet: TextPacket // TODO: maybe decouple this from protocol details?
) : Update {
    sealed class Receiver {
        data class Players(val players: Set<UUID>) : Receiver()
        object Broadcast : Receiver()
    }
}
