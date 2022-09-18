package com.github.bedrockecs.server.comm.server

import java.net.InetSocketAddress
import java.util.*

/**
 * identification information of this connection, could be used to identify the player
 */
data class ConnectionIdentifiers(
    val displayName: String?,
    val playerXUID: String?,
    val playerUUID: UUID?,
    val sourceAddress: InetSocketAddress,
    val realSourceAddress: InetSocketAddress
)
