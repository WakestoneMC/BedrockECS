package com.github.bedrockecs.server.comm.zimpl.game

import com.github.bedrockecs.server.comm.zimpl.exchange.GameTickLoopExchange
import com.github.bedrockecs.server.game.system.System
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class NetworkTickLoopSystem(
    private val tickLoopExchange: GameTickLoopExchange
) : System {
    override val tickOrder: Int
        get() = Int.MAX_VALUE

    override fun tick() {
        tickLoopExchange.onServerTick()
    }

    @EventListener
    fun onClose(e: ContextClosedEvent) {
        tickLoopExchange.onServerStop()
    }
}
