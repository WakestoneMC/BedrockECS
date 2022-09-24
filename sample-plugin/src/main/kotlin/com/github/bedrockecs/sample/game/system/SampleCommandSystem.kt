package com.github.bedrockecs.sample.game.system

import com.github.bedrockecs.server.comm.game.ActionUpdateMailbox
import com.github.bedrockecs.server.comm.game.action.CommandAction
import com.github.bedrockecs.server.comm.game.update.ChatUpdate
import com.github.bedrockecs.server.comm.game.update.ChatUpdate.Receiver
import com.github.bedrockecs.server.game.system.CommonTickOrders
import com.github.bedrockecs.server.game.system.System
import com.nukkitx.protocol.bedrock.packet.TextPacket
import org.springframework.stereotype.Component

@Component
class SampleCommandSystem(
    private val mailbox: ActionUpdateMailbox
) : System {
    override val tickOrder: Int = CommonTickOrders.PLAYER_INTERACTION

    override fun tick() {
        mailbox
            .listActions()
            .filterIsInstance<CommandAction>()
            .forEach {
                val sender = it.sender as CommandAction.Sender.Player
                if (it.cmd == "/sample") {
                    mailbox.addUpdate(
                        ChatUpdate(
                            receiver = Receiver.Players(players = setOf(sender.uuid)),
                            packet = TextPacket().apply {
                                sourceName = "server"
                                type = TextPacket.Type.ANNOUNCEMENT
                                message = "hello from sample plugin!"
                                xuid = ""
                                platformChatId = ""
                            }
                        )
                    )
                }
            }
    }
}
