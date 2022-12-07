package com.github.bedrockecs.comm.config

import java.net.InetSocketAddress

/**
 * configuration object for network subsystem
 */
data class NetworkConfig(
    val bindAddress: InetSocketAddress = InetSocketAddress("127.0.0.1", 19132),
    val onlineMode: Boolean = true,
    val maxPlayerCount: Int = 20
)
