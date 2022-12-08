package com.github.bedrockecs.comm.game

import com.github.bedrockecs.comm.game.action.Action
import com.github.bedrockecs.comm.game.update.Update
import com.github.bedrockecs.game.system.ECSSystem

/**
 * central exchange point of [Action] and [Update],
 * [ECSSystem] can send [Update] to client and receive [Action] from client
 */
interface ActionUpdateMailbox {
    fun listActions(): List<Action>

    fun listUpdates(): Collection<Update>

    fun addUpdate(update: Update)
}
