package com.github.bedrockecs.comm.zimpl.handler

import com.github.bedrockecs.comm.config.NetworkConfig
import com.nukkitx.protocol.bedrock.BedrockPacketCodec
import com.nukkitx.protocol.bedrock.BedrockPong
import org.springframework.stereotype.Component
import java.net.InetSocketAddress

@Component
class PingHandler(
    private val codec: BedrockPacketCodec,
    private val config: NetworkConfig
) {
    fun handle(source: InetSocketAddress): BedrockPong {
        val pong = BedrockPong()
        pong.edition = "MCPE"
        pong.motd = "§fBedrock§bE§aC§cS§f"
        pong.subMotd = "MC:BE Server, reimagined"
        pong.gameType = "Creative"
        pong.maximumPlayerCount = config.maxPlayerCount
        pong.playerCount = 0
        pong.ipv4Port = config.bindAddress.port
        pong.ipv6Port = config.bindAddress.port + 1
        pong.protocolVersion = codec.protocolVersion
        pong.version = codec.minecraftVersion
        pong.isNintendoLimited = false
        return pong
    }
}
