package com.github.bedrockecs.comm.game

import com.github.bedrockecs.comm.game.action.Action
import com.github.bedrockecs.comm.game.update.Update
import com.github.bedrockecs.server.game.system.System

/**
 * central exchange point of [Action] and [Update],
 * [System] can send [Update] to client and receive [Action] from client
 */
interface ActionUpdateMailbox {
    fun listActions(): List<Action>

    fun listUpdates(): Collection<Update>

    fun addUpdate(update: Update)
}
