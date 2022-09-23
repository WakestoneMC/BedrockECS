package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.server.ConnectionClosedException
import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.packet.TextPacket
import org.springframework.stereotype.Component

@Component
class CommandChatExchange(
    private val conns: PlayerConnectionExchange
) {

    // connection handling //

    suspend fun onConnection(connection: NetworkConnection) {
    }

    suspend fun onDisconnected(connection: NetworkConnection) {
    }

    suspend fun onPacket(connection: NetworkConnection, packet: BedrockPacket): ProcessResult {
        if (packet is TextPacket) {
            conns.listConnections().map { conn ->
                try {
                    conn.sendPacket(packet)
                } catch (ex: ConnectionClosedException) {
                    // no-op
                }
            }
            return ProcessResult.CONSUME
        }
        return ProcessResult.CONTINUE
    }
}
