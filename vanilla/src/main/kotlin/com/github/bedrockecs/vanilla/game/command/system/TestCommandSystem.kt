package com.github.bedrockecs.vanilla.game.command.system

import com.github.bedrockecs.game.io.ActionUpdateMailbox
import com.github.bedrockecs.game.io.action.CommandAction
import com.github.bedrockecs.game.io.update.ChatUpdate
import com.github.bedrockecs.game.system.CommonTickOrders
import com.github.bedrockecs.game.system.ECSSystem
import org.springframework.stereotype.Component

@Component
class TestCommandSystem(
    private val mailbox: ActionUpdateMailbox
) : ECSSystem {

    override val tickOrder: Int = CommonTickOrders.INTERACTION

    override fun tick() {
        mailbox.listActions()
            .filterIsInstance<CommandAction>()
            .forEach { action ->
                if (action.cmd == "/hello") {
                    val sender = (action.sender as CommandAction.Sender.Player).uuid
                    mailbox.addUpdate(
                        ChatUpdate(
                            ChatUpdate.Receiver.Players(setOf(sender)),
                            sender = "server",
                            text = "hello world!"
                        )
                    )
                }
            }
    }
}
