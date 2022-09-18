package com.github.bedrockecs.server.game.db.world.op

import com.github.bedrockecs.server.game.db.world.MutableWorldStore

/**
 * represents a mutating batch operation against the [MutableWorldStore]
 */
sealed class MutateBlockOp : BlockOp()
