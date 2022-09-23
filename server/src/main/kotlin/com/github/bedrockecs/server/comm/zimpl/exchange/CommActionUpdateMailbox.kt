package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.game.action.Action
import com.github.bedrockecs.server.comm.game.update.Update
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * called with-in tick loop to collect additional actions per tick
 */
typealias TickActionProvider = () -> List<Action>

typealias UpdateHandler = (List<Update>) -> Unit

/**
 * comm side of the action update mailbox
 */
@Component
class CommActionUpdateMailbox {

    private val actionsLock = ReentrantLock()

    private var actions = mutableListOf<Action>()

    private val actionProviders = ConcurrentHashMap<TickActionProvider, TickActionProvider>()

    private val updateListeners = ConcurrentHashMap<UpdateHandler, UpdateHandler>()

    // mailbox exchanges //

    fun addAction(action: Action) {
        actionsLock.withLock {
            actions.add(action)
        }
    }

    fun collectActions(): List<Action> {
        val tickActions = actionProviders.values.flatMap { it() }
        actionsLock.withLock {
            val oldActions = actions
            actions = mutableListOf()
            return oldActions + tickActions
        }
    }

    fun sendUpdates(actions: List<Update>) {
        updateListeners.forEach { it.value(actions) }
    }

    fun subscribeToUpdates(handler: UpdateHandler): AutoCloseable {
        updateListeners.put(handler, handler)
        return AutoCloseable { updateListeners.remove(handler) }
    }

    fun addTickActionProvider(provider: TickActionProvider): AutoCloseable {
        actionProviders.put(provider, provider)
        return AutoCloseable { actionProviders.remove(provider) }
    }
}
