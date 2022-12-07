package com.github.bedrockecs.comm.server

import com.nukkitx.protocol.bedrock.BedrockPacket
import java.util.concurrent.CompletableFuture

/**
 * represents a player connection
 */
interface NetworkConnection {

    // queries //

    val state: ConnectionState

    val identifiers: ConnectionIdentifiers

    // actions //

    enum class Latency {
        NORMAL, IMMEDIATELY
    }

    /**
     * try to send a packet to client
     * @throws ConnectionClosedException if the connection is closed
     */
    fun sendPacket(packet: BedrockPacket, latency: Latency = Latency.NORMAL)

    /**
     * try to receive a packet from client
     * @throws ConnectionClosedException if the connection is closed
     */
    suspend fun receivePacket(): BedrockPacket

    /**
     * try to disconnect the client, no-op if the user is already disconnected
     */
    fun disconnect(reason: String? = null, hideReason: Boolean = true): CompletableFuture<Void>
}
