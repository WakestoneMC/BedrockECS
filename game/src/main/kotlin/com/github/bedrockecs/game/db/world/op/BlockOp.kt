package com.github.bedrockecs.game.db.world.op

import com.github.bedrockecs.game.db.world.WorldDB

/**
 * represents a batch operation against the [WorldDB]
 */
sealed class BlockOp {
    /**
     * common interface for result of [BlockOp]
     */
    interface Result
}
