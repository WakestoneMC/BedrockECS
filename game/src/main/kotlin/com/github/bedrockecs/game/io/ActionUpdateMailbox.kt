package com.github.bedrockecs.game.io

import com.github.bedrockecs.game.io.action.Action
import com.github.bedrockecs.game.io.update.Update
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
