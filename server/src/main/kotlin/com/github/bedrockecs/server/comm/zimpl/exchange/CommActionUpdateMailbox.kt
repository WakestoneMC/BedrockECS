package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.game.action.Action
import com.github.bedrockecs.server.comm.game.update.Update
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

typealias UpdateHandler = (List<Update>) -> Unit

/**
 * comm side of the action update mailbox
 */
@Component
class CommActionUpdateMailbox {

    private val actionsLock = ReentrantLock()

    private var actions = mutableListOf<Action>()

    private val updateListeners = ConcurrentHashMap<UpdateHandler, UpdateHandler>()

    // mailbox exchanges //

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
        updateListeners.forEach { it.value(actions) }
    }

    fun subscribeToUpdates(handler: UpdateHandler): AutoCloseable {
        updateListeners.put(handler, handler)
        return AutoCloseable { updateListeners.remove(handler) }
    }
}
