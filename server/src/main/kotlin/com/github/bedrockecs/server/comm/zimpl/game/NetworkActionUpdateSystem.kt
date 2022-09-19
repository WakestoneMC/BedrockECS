package com.github.bedrockecs.server.comm.zimpl.game

import com.github.bedrockecs.server.comm.zimpl.exchange.ActionUpdateExchange
import com.github.bedrockecs.server.game.system.System
import org.springframework.stereotype.Component

@Component
class NetworkActionUpdateSystem(
    private val exchange: ActionUpdateExchange,
    private val mailboxImpl: ActionUpdateMailboxImpl
) : System {
    override val tickOrder: Int
        get() = Int.MIN_VALUE

    override fun tick() {
        val actions = exchange.collectActions()
        val updates = mailboxImpl.swap(actions)
        exchange.sendUpdates(updates)
    }
}
