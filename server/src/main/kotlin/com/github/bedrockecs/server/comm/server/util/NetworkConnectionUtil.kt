package com.github.bedrockecs.server.comm.server.util

import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.comm.zimpl.log
import com.github.bedrockecs.server.comm.zimpl.withConnectionDebugLog
import com.nukkitx.protocol.bedrock.BedrockPacket

suspend inline fun <reified T : BedrockPacket> NetworkConnection.discardingWait(): T {
    while (true) {
        val received = receivePacket()
        if (T::class.java.isInstance(received)) {
            return received as T
        } else {
            withConnectionDebugLog(this) {
                log.debug("discarded received packet in discardingWait() [type={}]", received::class.java.simpleName)
            }
        }
    }
}
