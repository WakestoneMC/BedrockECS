package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.game.action.PlayerMoveAction
import com.github.bedrockecs.server.comm.game.update.DisconnectPlayerUpdate
import com.github.bedrockecs.server.comm.game.update.Update
import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.comm.zimpl.log
import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.packet.MovePlayerPacket
import org.springframework.stereotype.Component

@Component
class ActionUpdateExchange(
    private val mailbox: CommActionUpdateMailbox,
    private val connections: PlayerConnectionExchange
) {

    // connection handling //

    suspend fun onConnection(connection: NetworkConnection) {
    }

    suspend fun onDisconnected(connection: NetworkConnection) {
    }

    suspend fun onPacket(connection: NetworkConnection, packet: BedrockPacket): ProcessResult {
        return when (packet) {
            is MovePlayerPacket -> {
                handleMovePlayer(connection, packet)
                ProcessResult.CONSUME
            }
            else -> {
                ProcessResult.CONTINUE
            }
        }
    }

    // action interpreter //

    private suspend fun handleMovePlayer(connection: NetworkConnection, packet: MovePlayerPacket) {
        mailbox.addAction(
            PlayerMoveAction(
                playerUUID = connection.identifiers.playerUUID!!,
                packet.position,
                packet.rotation
            )
        )
    }

    // update sender //

    fun sendUpdate(update: Update) {
        when (update) {
            is DisconnectPlayerUpdate -> connections.connectionForUUID(update.uuid)?.disconnect()
            else -> log.warn("ignoring unknown update [update={}]", update)
        }
    }
}
