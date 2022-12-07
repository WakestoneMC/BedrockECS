package com.github.bedrockecs.comm.zimpl.handler

import org.springframework.stereotype.Component
import java.net.InetSocketAddress

/**
 * handles pre-connection request
 */
@Component
class ConnectionRequestHandler {
    fun handle(address: InetSocketAddress, realAddress: InetSocketAddress): Boolean {
        return true
    }
}
