package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.game.action.Action
import com.github.bedrockecs.server.comm.game.update.DisconnectPlayerUpdate
import com.github.bedrockecs.server.comm.game.update.Update
import com.github.bedrockecs.server.comm.zimpl.log
import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Component
class ActionUpdateExchange(
    private val connections: PlayerConnectionExchange
) {

    private val actionsLock = ReentrantLock()

    private var actions = mutableListOf<Action>()

    fun addAction(action: Action) {
        actionsLock.withLock {
            actions.add(action)
        }
    }

    fun collectActions(): List<Action> {
        actionsLock.withLock {
            val oldActions = actions
            actions = mutableListOf()
            return oldActions
        }
    }

    fun sendUpdates(actions: List<Update>) {
        actions.forEach { sendUpdate(it) }
    }

    fun sendUpdate(update: Update) {
        when (update) {
            is DisconnectPlayerUpdate -> connections.connectionForUUID(update.uuid)?.disconnect()
            else -> log.warn("ignoring unknown update [update={}]", update)
        }
    }
}
