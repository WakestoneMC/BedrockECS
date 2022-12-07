package com.github.bedrockecs.comm.server

/**
 * the player connection has 5 states:
 * LOGIN: dealing with handshaking and login, to SETUP after done
 * SETUP: dealing with stuff like resourcepack, to ACTIVE after done
 * ACTIVE: when the connection is established, user should start by sending StartGamePacket
 * DISCONNECTED: when disconnected
 */
enum class ConnectionState {
    LOGIN, SETUP, ACTIVE, DISCONNECTED
}
