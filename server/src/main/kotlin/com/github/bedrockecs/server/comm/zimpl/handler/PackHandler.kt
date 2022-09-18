package com.github.bedrockecs.server.comm.zimpl.handler

import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.nukkitx.protocol.bedrock.packet.ResourcePackStackPacket
import com.nukkitx.protocol.bedrock.packet.ResourcePacksInfoPacket
import org.springframework.stereotype.Component

/**
 * deals with resource / behaviour packs
 */
@Component
class PackHandler {
    suspend fun handle(conn: NetworkConnection) {
        val packet = ResourcePacksInfoPacket()
        packet.setForcedToAccept(false)
        packet.setScriptingEnabled(false)
        packet.setForcingServerPacksEnabled(false)
        conn.sendPacket(packet, NetworkConnection.Latency.IMMEDIATELY)

        val packet1 = ResourcePackStackPacket()
        packet1.setGameVersion("")
        packet1.setForcedToAccept(false)
        conn.sendPacket(packet1, NetworkConnection.Latency.IMMEDIATELY)
    }
}
