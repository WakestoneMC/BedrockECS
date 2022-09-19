package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.game.action.Action
import com.github.bedrockecs.server.comm.game.update.Update
import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Component
class ActionUpdateExchange {

    private val actionsLock = ReentrantLock()

    private var actions = mutableListOf<Action>()

    fun addAction(action: Action) {
        actionsLock.withLock {
            actions.add(action)
        }
    }

    fun sendUpdates(actions: List<Update>) {
        // no-op for now //
    }

    fun collectActions(): List<Action> {
        actionsLock.withLock {
            val oldActions = actions
            actions = mutableListOf()
            return oldActions
        }
    }
}
