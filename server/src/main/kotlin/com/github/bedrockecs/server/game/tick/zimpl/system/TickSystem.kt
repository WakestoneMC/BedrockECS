package com.github.bedrockecs.server.game.tick.zimpl.system

import com.github.bedrockecs.server.game.db.GameDB
import com.github.bedrockecs.server.game.system.System
import com.github.bedrockecs.server.game.tick.TickComponent
import org.springframework.stereotype.Component

/**
 * updates the [TickComponent]
 */
@Component
class TickSystem(
    private val db: GameDB
) : System {
    override val tickOrder: Int = Int.MIN_VALUE

    override fun tick() {
        db.dimensions.mutate(0, TickComponent::class.java) {
            if (it == null) {
                TickComponent()
            } else {
                it.currentTick += 1
                it
            }
        }
    }
}
