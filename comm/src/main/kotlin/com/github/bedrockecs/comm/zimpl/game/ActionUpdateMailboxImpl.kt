package com.github.bedrockecs.comm.zimpl.game

import com.github.bedrockecs.game.io.ActionUpdateMailbox
import com.github.bedrockecs.game.io.action.Action
import com.github.bedrockecs.game.io.update.Update
import org.springframework.stereotype.Component
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Component
class ActionUpdateMailboxImpl : ActionUpdateMailbox {

    private val lock: ReentrantLock = ReentrantLock()

    private var actions: List<Action> = listOf()

    private var updates: MutableList<Update> = mutableListOf()

    override fun listActions(): List<Action> {
        return actions
    }

    override fun listUpdates(): Collection<Update> {
        lock.withLock {
            return updates.toList()
        }
    }

    override fun addUpdate(update: Update) {
        lock.withLock {
            updates.add(update)
        }
    }

    fun swap(actions: List<Action>): List<Update> {
        lock.withLock {
            val oldUpdate = updates
            this.actions = actions
            updates = mutableListOf()
            return oldUpdate
        }
    }
}
