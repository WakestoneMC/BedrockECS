package com.github.bedrockecs.server.comm.zimpl.handler

import com.nimbusds.jose.JOSEException
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSObject
import com.nimbusds.jose.Payload
import com.nimbusds.jose.jwk.Curve
import com.nimbusds.jwt.SignedJWT
import com.nukkitx.network.util.Preconditions
import com.nukkitx.protocol.bedrock.BedrockSession
import com.nukkitx.protocol.bedrock.packet.LoginPacket
import com.nukkitx.protocol.bedrock.packet.ServerToClientHandshakePacket
import com.nukkitx.protocol.bedrock.util.EncryptionUtils
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.net.URI
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PublicKey
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.util.Base64
import java.util.UUID

object HandshakeUtils {

    private val privateKeyPair: KeyPair

    init {
        try {
            val generator = KeyPairGenerator.getInstance("EC")
            generator.initialize(Curve.P_384.toECParameterSpec())
            privateKeyPair = generator.generateKeyPair()
        } catch (e: Exception) {
            throw RuntimeException("Unable to generate private keyPair!", e)
        }
    }

    fun validateChain(chainArray: JsonArray, strict: Boolean): Boolean {
        if (strict && chainArray.size > 3) {
            // We dont expect larger chain
            return false
        }
        var lastKey: ECPublicKey? = null
        var authed = false
        val iterator: Iterator<JsonElement> = chainArray.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            val jwt = SignedJWT.parse((element as JsonPrimitive).content)
            val x5u = jwt.header.x509CertURL ?: throw JOSEException("Key not found")
            val expectedKey = EncryptionUtils.generateKey(jwt.header.x509CertURL.toString())
            if (lastKey == null) {
                // First key is self signed
                lastKey = expectedKey
            } else require(!(strict && lastKey != expectedKey)) {
                // Make sure the previous key matches the header of the current
                "Key does not match"
            }
            if (!EncryptionUtils.verifyJwt(jwt, lastKey)) {
                if (strict) {
                    throw JOSEException("Login JWT was not valid")
                }
                return false
            }
            if (lastKey == EncryptionUtils.getMojangPublicKey()) {
                authed = true
            } else if (authed) {
                return !iterator.hasNext()
            }
            val payload = Json.decodeFromString<JsonObject>(jwt.payload.toString())
            Preconditions.checkArgument(
                payload.contains("identityPublicKey"),
                "IdentityPublicKey node is missing in chain!"
            )
            val ipkNode = payload["identityPublicKey"]
            lastKey = EncryptionUtils.generateKey((ipkNode as JsonPrimitive).content)
        }
        return authed
    }

    fun createExtraData(pair: KeyPair, extraData: JsonObject): JWSObject {
        val publicKeyBase64 = Base64.getEncoder().encodeToString(pair.public.encoded)
        val timestamp = System.currentTimeMillis() / 1000
        val dataChain = JsonObject(
            mapOf(
                "nbf" to JsonPrimitive(timestamp - 3600),
                "exp" to JsonPrimitive(timestamp + 24 * 3600),
                "iat" to JsonPrimitive(timestamp),
                "iss" to JsonPrimitive("self"),
                "certificateAuthority" to JsonPrimitive(true),
                "extraData" to extraData,
                "randomNonce" to JsonPrimitive(UUID.randomUUID().leastSignificantBits),
                "identityPublicKey" to JsonPrimitive(publicKeyBase64)
            )
        )

        return encodeJWT(pair, dataChain)
    }

    fun encodeJWT(pair: KeyPair, payload: JsonObject): JWSObject {
        val publicKeyBase64 = Base64.getEncoder().encodeToString(pair.public.encoded)
        val x5u = URI.create(publicKeyBase64)
        val header = JWSHeader.Builder(JWSAlgorithm.ES384).x509CertURL(x5u).build()
        val jwsObject = JWSObject(header, Payload(payload.toString()))
        try {
            EncryptionUtils.signJwt(jwsObject, pair.private as ECPrivateKey)
        } catch (e: JOSEException) {
            throw RuntimeException(e)
        }
        return jwsObject
    }

    fun processHandshake(
        packet: LoginPacket,
        certChain: JsonArray,
        strict: Boolean
    ): HandshakeEntry {
        // Cert chain should be signed by Mojang is client xbox authenticated
        var xboxAuth = validateChain(certChain, strict)
        val jwt = JWSObject.parse(certChain[certChain.size - 1].toString())
        val payload = Json.decodeFromString<JsonObject>(jwt.payload.toString())
        val extraData = parseExtraData(packet, payload)
        if (!payload.contains("identityPublicKey")) {
            throw RuntimeException("Identity Public Key was not found!")
        }
        val identityPublicKeyString = (payload["identityPublicKey"] as JsonPrimitive).content
        val identityPublicKey = EncryptionUtils.generateKey(identityPublicKeyString)
        val clientJwt = JWSObject.parse(packet.skinData.toString())
        if (!EncryptionUtils.verifyJwt(clientJwt, identityPublicKey) && strict) {
            xboxAuth = false
        }
        val clientData = parseClientData(clientJwt)
        return HandshakeEntry(identityPublicKey, clientData, extraData, xboxAuth)
    }

    fun parseClientData(
        clientJwt: JWSObject
    ): JsonObject {
        return Json.decodeFromString<JsonObject>(clientJwt.payload.toString())
    }

    fun parseExtraData(
        packet: LoginPacket?,
        payload: JsonObject
    ): JsonObject {
        val extraDataElement = payload["extraData"]
        check(extraDataElement is JsonObject) { "Invalid 'extraData'" }
        return extraDataElement
    }

    fun processEncryption(session: BedrockSession, key: PublicKey?) {
        val token = EncryptionUtils.generateRandomToken()
        val encryptionKey = EncryptionUtils.getSecretKey(privateKeyPair.private, key, token)
        session.enableEncryption(encryptionKey)
        val packet = ServerToClientHandshakePacket()
        packet.jwt = EncryptionUtils.createHandshakeJwt(privateKeyPair, token).serialize()
        session.sendPacketImmediately(packet)
    }
}
