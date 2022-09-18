package com.github.bedrockecs.server.comm.zimpl.handler

import com.github.bedrockecs.server.comm.config.NetworkConfig
import com.github.bedrockecs.server.comm.server.ConnectionIdentifiers
import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.comm.server.util.discardingWait
import com.github.bedrockecs.server.comm.zimpl.server.NetworkConnectionImpl
import com.nukkitx.protocol.bedrock.BedrockPacketCodec
import com.nukkitx.protocol.bedrock.packet.ClientToServerHandshakePacket
import com.nukkitx.protocol.bedrock.packet.LoginPacket
import com.nukkitx.protocol.bedrock.packet.PlayStatusPacket
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.UUID

/**
 * deals with login part of protocol
 */
@Component
class LoginHandler(
    private val config: NetworkConfig,
    private val codec: BedrockPacketCodec
) {

    suspend fun handle(conn: NetworkConnectionImpl): ConnectionIdentifiers? {
        // process login
        val packet = conn.discardingWait<LoginPacket>()

        // setup codec
        val protocolVersion = packet.protocolVersion
        if (protocolVersion != codec.protocolVersion) {
            val status =
                if (protocolVersion > codec.protocolVersion) {
                    PlayStatusPacket.Status.LOGIN_FAILED_SERVER_OLD
                } else {
                    PlayStatusPacket.Status.LOGIN_FAILED_CLIENT_OLD
                }
            failWithReason(conn, status, "protocol version mismatch")
            return null
        }
        conn.session.packetCodec = codec

        // verify xbox & process encryption
        var xboxAuth = false
        val handshakeEntry: HandshakeEntry
        try {
            val node = Json.decodeFromString<JsonObject>(packet.chainData.toByteArray().toString(StandardCharsets.UTF_8))
            check(!(!node.contains("chain") || node["chain"] !is JsonArray)) { "Certificate data is not valid" }
            val certChain = node["chain"] as JsonArray
            val strictAuth = config.onlineMode
            handshakeEntry = HandshakeUtils.processHandshake(
                packet,
                certChain,
                strictAuth
            )
            if (!handshakeEntry.isXboxAuthed.also { xboxAuth = it } && strictAuth) {
                failWithReason(conn, "disconnectionScreen.notAuthenticated")
                return null
            }
            HandshakeUtils.processEncryption(conn.session, handshakeEntry.identityPublicKey)
        } catch (e: Exception) {
            failWithReason(conn, "exception in login process: " + e.message)
            return null
        }

        // process handshake
        conn.discardingWait<ClientToServerHandshakePacket>()
        val status = PlayStatusPacket()
        status.status = PlayStatusPacket.Status.LOGIN_SUCCESS
        conn.sendPacket(status, NetworkConnection.Latency.IMMEDIATELY)

        // complete identifier
        return conn.identifiers.copy(
            displayName = handshakeEntry.displayName,
            playerXUID = (handshakeEntry.extraData["XUID"] as JsonPrimitive).content,
            playerUUID = UUID.fromString((handshakeEntry.extraData["identity"] as JsonPrimitive).content)
        )
    }

    private fun failWithReason(conn: NetworkConnection, status: PlayStatusPacket.Status, reason: String) {
        val statusPacket = PlayStatusPacket()
        statusPacket.status = status
        conn.sendPacket(statusPacket, NetworkConnection.Latency.IMMEDIATELY)
        return failWithReason(conn, reason)
    }

    private fun failWithReason(conn: NetworkConnection, reason: String) {
        conn.disconnect(reason)
    }
}
