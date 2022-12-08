package com.github.bedrockecs.comm.zimpl.game

import com.github.bedrockecs.comm.zimpl.exchange.CommActionUpdateMailbox
import com.github.bedrockecs.game.system.CommonTickOrders
import com.github.bedrockecs.game.system.ECSSystem
import org.springframework.stereotype.Component

@Component
class NetworkActionUpdateSystem(
    private val commMailbox: CommActionUpdateMailbox,
    private val mailboxImpl: ActionUpdateMailboxImpl
) : ECSSystem {
    override val tickOrder: Int
        get() = CommonTickOrders.INPUT

    override fun tick() {
        val actions = commMailbox.collectActions()
        val updates = mailboxImpl.swap(actions)
        commMailbox.sendUpdates(updates)
    }
}
