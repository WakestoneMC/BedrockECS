package com.github.bedrockecs.vanilla.game.command.system

import com.github.bedrockecs.comm.game.ActionUpdateMailbox
import com.github.bedrockecs.comm.game.action.CommandAction
import com.github.bedrockecs.comm.game.update.ChatUpdate
import com.github.bedrockecs.server.game.system.CommonTickOrders
import com.github.bedrockecs.server.game.system.System
import com.nukkitx.protocol.bedrock.packet.TextPacket
import org.springframework.stereotype.Component

@Component
class TestCommandSystem(
    private val mailbox: ActionUpdateMailbox
) : System {

    override val tickOrder: Int = CommonTickOrders.PLAYER_INTERACTION

    override fun tick() {
        mailbox.listActions()
            .filterIsInstance<CommandAction>()
            .forEach { action ->
                if (action.cmd == "/hello") {
                    val sender = (action.sender as CommandAction.Sender.Player).uuid
                    mailbox.addUpdate(
                        ChatUpdate(
                            ChatUpdate.Receiver.Players(setOf(sender)),
                            TextPacket().apply {
                                sourceName = "server"
                                type = TextPacket.Type.ANNOUNCEMENT
                                message = "hello world!"
                                xuid = ""
                                platformChatId = ""
                            }
                        )
                    )
                }
            }
    }
}
