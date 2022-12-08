package com.github.bedrockecs.game.tick.zimpl.system

import com.github.bedrockecs.game.db.entity.EntityDB
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.system.ECSSystem
import com.github.bedrockecs.game.tick.TickComponent
import org.springframework.stereotype.Component

/**
 * updates the [TickComponent]
 */
@Component
class TickSystem(
    private val db: EntityDB
) : ECSSystem {
    override val tickOrder: Int = Int.MIN_VALUE

    override fun tick() {
        db.mutate(EntityID.GLOBAL, TickComponent::class.java) {
            if (it == null) {
                TickComponent()
            } else {
                it.currentTick += 1
                it
            }
        }
    }
}
