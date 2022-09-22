package com.github.bedrockecs.server.comm.zimpl.game

import com.github.bedrockecs.server.comm.zimpl.exchange.CommActionUpdateMailbox
import com.github.bedrockecs.server.game.system.CommonTickOrders
import com.github.bedrockecs.server.game.system.System
import org.springframework.stereotype.Component

@Component
class NetworkActionUpdateSystem(
    private val commMailbox: CommActionUpdateMailbox,
    private val mailboxImpl: ActionUpdateMailboxImpl
) : System {
    override val tickOrder: Int
        get() = CommonTickOrders.NETWORK_INPUT

    override fun tick() {
        val actions = commMailbox.collectActions()
        val updates = mailboxImpl.swap(actions)
        commMailbox.sendUpdates(updates)
    }
}
