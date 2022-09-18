package com.github.bedrockecs.server.comm.zimpl.handler

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.security.interfaces.ECPublicKey

class HandshakeEntry(
    val identityPublicKey: ECPublicKey,
    val clientData: JsonObject,
    val extraData: JsonObject,
    val isXboxAuthed: Boolean
) {

    val displayName: String
        get() = (extraData["displayName"] as JsonPrimitive).content
}
