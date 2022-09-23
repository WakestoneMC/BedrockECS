package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.game.action.CommandAction
import com.github.bedrockecs.server.comm.game.update.ChatUpdate
import com.github.bedrockecs.server.comm.game.update.Update
import com.github.bedrockecs.server.comm.server.ConnectionClosedException
import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.packet.CommandRequestPacket
import com.nukkitx.protocol.bedrock.packet.TextPacket
import org.springframework.stereotype.Component

@Component
class CommandChatExchange(
    private val conns: PlayerConnectionExchange,
    private val mailbox: CommActionUpdateMailbox
) {

    init {
        mailbox.subscribeToUpdates(this::onUpdates)
    }

    // connection handling //

    suspend fun onConnection(connection: NetworkConnection) {
    }

    suspend fun onDisconnected(connection: NetworkConnection) {
    }

    suspend fun onPacket(connection: NetworkConnection, packet: BedrockPacket): ProcessResult {
        if (packet is CommandRequestPacket) {
            val sender = CommandAction.Sender.Player(connection.identifiers.playerUUID!!)
            mailbox.addAction(CommandAction(sender, packet.command))
            return ProcessResult.CONSUME
        }
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

    // action handling //

    private fun onUpdates(updates: List<Update>) {
        updates
            .filterIsInstance<ChatUpdate>()
            .forEach { update ->
                when (update.receiver) {
                    ChatUpdate.Receiver.Broadcast -> {
                        conns.listConnections().map { conn ->
                            try {
                                conn.sendPacket(update.packet)
                            } catch (ex: ConnectionClosedException) {
                                // no-op
                            }
                        }
                    }

                    is ChatUpdate.Receiver.Players -> {
                        update.receiver.players.forEach { player ->
                            try {
                                conns.connectionForUUID(player)?.sendPacket(update.packet)
                            } catch (ex: ConnectionClosedException) {
                                // no-op
                            }
                        }
                    }
                }
            }
    }
}
