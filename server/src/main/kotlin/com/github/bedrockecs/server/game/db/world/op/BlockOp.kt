package com.github.bedrockecs.server.game.db.world.op

import com.github.bedrockecs.server.game.db.world.WorldStore

/**
 * represents a batch operation against the [WorldStore]
 */
sealed class BlockOp {
    /**
     * common interface for result of [BlockOp]
     */
    interface Result
}
