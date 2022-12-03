package com.github.bedrockecs.game.db.world.op

import com.github.bedrockecs.game.db.world.WorldDB

/**
 * represents a mutating batch operation against the [WorldDB]
 */
sealed class MutateBlockOp : BlockOp()
