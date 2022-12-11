package com.github.bedrockecs.comm.zimpl.exchange

import com.github.bedrockecs.comm.server.NetworkConnection
import com.nukkitx.math.vector.Vector3i
import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.data.inventory.ContainerType
import com.nukkitx.protocol.bedrock.packet.ContainerClosePacket
import com.nukkitx.protocol.bedrock.packet.ContainerOpenPacket
import com.nukkitx.protocol.bedrock.packet.InteractPacket
import org.springframework.stereotype.Component

/**
 * in charge of dealing with inventory/container/furnace/crafting table UIs
 */
@Component
class UIExchange(
    private val exchange: GameWorldExchange
) {

    // WorldHandler side //

    suspend fun onConnection(connection: NetworkConnection) {
    }

    suspend fun onDisconnected(connection: NetworkConnection) {
    }

    suspend fun onPacket(connection: NetworkConnection, packet: BedrockPacket): ProcessResult {
        return when (packet) {
            is InteractPacket -> onContainerInteractPacket(packet, connection)
            is ContainerClosePacket -> onContainerClosePacket(packet, connection)
            else -> ProcessResult.CONTINUE
        }
    }

    // action interpreter //

    private fun onContainerInteractPacket(
        packet: InteractPacket,
        connection: NetworkConnection
    ) = if (packet.action == InteractPacket.Action.OPEN_INVENTORY) {
        val pos = exchange.lastPositionForPlayer(connection.identifiers.playerUUID!!)!!
        val packet = ContainerOpenPacket().apply {
            id = 2
            blockPosition = Vector3i.from(pos.x.toInt(), pos.y.toInt(), pos.z.toInt())
            type = ContainerType.INVENTORY
            uniqueEntityId = -1
        }
        connection.sendPacket(packet)
        ProcessResult.CONSUME
    } else {
        ProcessResult.CONTINUE
    }

    private fun onContainerClosePacket(
        packet: ContainerClosePacket,
        connection: NetworkConnection
    ): ProcessResult {
        val p = ContainerClosePacket()
        p.id = p.id
        p.isUnknownBool0 = packet.isUnknownBool0
        connection.sendPacket(p)
        return ProcessResult.CONSUME
    }
}
